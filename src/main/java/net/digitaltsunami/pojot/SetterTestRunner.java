package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.property.PropertyValue;
import net.digitaltsunami.pojot.property.PropertyValues;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Performs basic validation on default setters. Default meaning that there is no special logic and the setter
 * simply sets the value in the property.
 * Basic validation includes:
 * <ul>
 *     <li>Setting the property to null if non-primitive</li>
 *     <li>Setting the property to an arbitrary value.</li>
 *     <li>Setting the property to a small value.  For numbers, this value is the minimum value</li>
 *     <li>Setting the property to a large value.  For numbers, this value is the maximum value</li>
 * </ul>
 */
public class SetterTestRunner<T> extends AbstractTestRunner<T> {

    private final Set<PropertyDescriptor> methodsUnderTest;
    private final Map<String,Field> declaredFields;

    /**
     * Create a test runner for the class under test.
     * @param clazz Class under test.
     * @param beanInfo Info on methods and properties of class under test.
     */
    public SetterTestRunner(Class<T> clazz, BeanInfo beanInfo) {
        this(clazz, beanInfo, TestAid.getFieldsToCheck(beanInfo, Collections.emptySet(), Collections.emptySet()));
    }

    /**
     * Create a test runner for the class under test and limit the setters to be tested.
     * @param clazz Class under test.
     * @param beanInfo Info on methods and properties of class under test.
     * @param methodsUnderTest set used to limit properties being tested.
     */
    public SetterTestRunner(Class<T> clazz, BeanInfo beanInfo, Set<PropertyDescriptor> methodsUnderTest) {
        super(clazz, beanInfo);
        this.methodsUnderTest = methodsUnderTest;
        this.declaredFields =
                Arrays.stream(clazz.getDeclaredFields())
                        .collect(toMap(field -> field.getName(), identity()));

    }


    @Override
    public List<String> runTests() {
        final List<String> errors = new ArrayList<>();
        try {
            final T instance = (T) clazz.newInstance();
            methodsUnderTest.forEach(field -> testSetter(instance, field)
                    .ifPresent(value -> errors.add(value)));
        } catch (IllegalAccessException | InstantiationException e) {
            throw new TestAidException("Setter test failed to run", e);
        }

        return errors;
    }


    /**
     * Test the setter method for the provided property. This is a simple test to ensure that the setter sets
     * the value in the field.
     *
     * @param instance Instance of class to operate on.
     * @param property Property to validate.
     * @return Optional with message if error occurred, otherwise empty.
     */
    private Optional<String> testSetter(T instance, PropertyDescriptor property) {
        if (property.getWriteMethod() == null) {
            return Optional.empty();
        }

        try {
            Field field = declaredFields.get(property.getName());
            if(field == null) {
                // This is a setter without a property.  Nothing to test.
                return Optional.empty();
            }
            Class<?> fieldType = field.getType();
            field.setAccessible(true);

            PropertyValue val = PropertyValues.fromString(fieldType.getCanonicalName());

            Optional<String> message = testSetterWithVal(instance, property, field, val.getValue());
            if (message.isPresent()) return message;

            message = testSetterWithVal(instance, property, field, val.getSmallValue());
            if (message.isPresent()) return message;

            message = testSetterWithVal(instance, property, field, val.getLargeValue());
            if (message.isPresent()) return message;

            if (!fieldType.isPrimitive()) {
                message = testSetterWithVal(instance, property, field, val.getDefaultValue());
                if (message.isPresent()) return message;
            }
            if (message.isPresent()) return message;
        } catch (Exception e) {
            throw new TestAidException(
                    String.format("Exception during setter test: %s.%s", getClassName(), property.getWriteMethod().getName()), e);
        }
        return Optional.empty();
    }

    /**
     * Perform one setter operation with the provided value.
     * @param instance Instance of class under test to operate on.
     * @param property Property being tested
     * @param field Field being tested
     * @param testVal Test value to use.
     * @return Optional that will contain an error message if the test failed, otherwise empty.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Optional<String> testSetterWithVal(T instance, PropertyDescriptor property, Field field, Object
            testVal) throws InvocationTargetException, IllegalAccessException {

        Objects.requireNonNull(instance, "Instance of target class required.");
        boolean status = true;
        property.getWriteMethod()
                .invoke(instance, testVal);

        Object expected, actual;
        if (property.getPropertyType().isPrimitive()) {
            expected = String.valueOf(testVal);
            actual = String.valueOf(field.get(instance));
            status = expected.equals(actual);
        } else {

            expected = testVal;
            actual = field.get(instance);

            if (expected == null) {
                status = actual == null;
            } else {
                status = expected.equals(actual);
            }
        }
        if (status == false) {
            return Optional.of(String.format("Failed during setter test: %s.  Expected: %s  Actual: %s", property.getWriteMethod(), expected, actual));
        }
        return Optional.empty();
    }
}
