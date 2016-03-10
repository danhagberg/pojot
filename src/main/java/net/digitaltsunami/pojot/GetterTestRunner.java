package net.digitaltsunami.pojot;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Performs basic validation on default getters. Default meaning that there is no special logic and the getter
 * simply returns the value in the property.
 */
public class GetterTestRunner<T> extends AbstractTestRunner<T> {

    private final Set<PropertyDescriptor> methodsUnderTest;
    private final Map<String, Field> declaredFields;

    /**
     * Create a test runner for the class under test.
     *
     * @param clazz    Class under test.
     * @param beanInfo Info on methods and properties of class under test.
     */
    public GetterTestRunner(Class<T> clazz, BeanInfo beanInfo) {
        this(clazz, beanInfo, TestAid.getFieldsToCheck(beanInfo, Collections.emptySet(), Collections.emptySet()));
    }

    /**
     * Create a test runner for the class under test and limit the getters to be tested.
     *
     * @param clazz            Class under test.
     * @param beanInfo         Info on methods and properties of class under test.
     * @param methodsUnderTest set used to specify which methods should be tested.
     */
    public GetterTestRunner(Class<T> clazz, BeanInfo beanInfo, Set<PropertyDescriptor> methodsUnderTest) {
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
            methodsUnderTest.forEach(field -> testGetter(instance, field)
                    .ifPresent(value -> errors.add(value)));
        } catch (IllegalAccessException | InstantiationException e) {
            throw new TestAidException("Error during Getter testing", e);
        }

        return errors;
    }

    /**
     * Test the getter method for the provided property. This is a simple test to ensure that the getter returns
     * the value that is currently in the field. If non-primitive, the checks will include null and non-null values.
     *
     * @param instance Instance of class to operate on.
     * @param property Property to validate.
     * @return Optional with message if error occurred, otherwise empty.
     */
    private Optional<String> testGetter(T instance, PropertyDescriptor property) {
        if (property.getReadMethod() == null) {
            return Optional.empty();
        }

        boolean status;
        Object expected, actual;
        try {
            Field field = declaredFields.get(property.getName());
            if(field == null) {
                // This is a setter without a property.  Nothing to test.
                return Optional.empty();
            }
            field.setAccessible(true);

            if (property.getPropertyType().isPrimitive()) {
                expected = String.valueOf(field.get(instance));
                actual = String.valueOf(property.getReadMethod().invoke(instance));
                status = expected.equals(actual);
            } else {

                expected = field.get(instance);
                actual = property.getReadMethod().invoke(instance);

                if (expected == null) {
                    status = actual == null;
                } else {
                    status = expected.equals(actual);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new TestAidException(
                    String.format("Exception during getter test: %s.%s", getClassName(), property.getReadMethod().getName()), e);
        }
        if (status == false) {
            return Optional.of(String.format("Failed during getter test: %s.  Expected: %s  Actual: %s", property.getReadMethod(), expected, actual));
        }
        return Optional.empty();
    }

}
