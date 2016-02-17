package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.property.PropertyValue;
import net.digitaltsunami.pojot.property.PropertyValues;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Test to ensure that stated fields have an effect on equals method and null values do not cause a NPE.
 * Tests include:
 * <ul>
 * <li>Test of default equals method (no properties provided).  Ensures that equals is same IFF instances are same (==) </li>
 * <li>Test to ensure that all provided properties affect the equals method.</li>
 * </ul>
 * Currently, there is no check to ensure that the list of fields provided is all inclusive.  If a field not provided
 * has an effect on the equals method, then this will not be found.
 */
public class EqualsTestRunner<T> extends AbstractTestRunner<T> {

    private final Set<String> includeInEquals;

    /**
     * Create a test runner for the class under test that includes no special logic to determine equality.
     *
     * @param clazz    Class under test.
     * @param beanInfo Info on methods and properties of class under test.
     */
    public EqualsTestRunner(Class clazz, BeanInfo beanInfo) {
        this(clazz, beanInfo, Collections.emptySet());
    }

    /**
     * Create a test runner for the class under test and provide the names of the properties that are
     * included in the equality calculation.
     *
     * @param clazz           Class under test.
     * @param beanInfo        Info on methods and properties of class under test.
     * @param includeInEquals names of properties that are used to compare equality.
     */
    public EqualsTestRunner(Class clazz, BeanInfo beanInfo, Set<String> includeInEquals) {
        super(clazz, beanInfo);
        this.includeInEquals = includeInEquals;
    }

    @Override
    public List<String> runTests() {
        final List<String> errors = new ArrayList<>();
        try {
            if (includeInEquals.isEmpty()) {
                errors.addAll(runSimpleTest());
            } else {
                errors.addAll(runCombinationTest());
            }
        } catch (InstantiationException
                | NoSuchFieldException
                | NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException e) {
            throw new TestAidException("Equals test failed", e);
        }
        return errors;
    }

    /**
     * Test if two instances of the same class are equal if fields are not provided for equality testing. If no
     * field were provided, then an instance is equal to another instance IFF they are the same instance (==).
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private List<String> runSimpleTest() throws IllegalAccessException, InstantiationException {
        final List<String> errors = new ArrayList<>();
        final T instanceOne = (T) clazz.newInstance();
        final T instanceTwo = (T) clazz.newInstance();
        if (instanceOne.equals(instanceTwo)) {
            errors.add(String.format("Equals method test failed for %s.  Expected %s but was %s",
                    getClassName(), Boolean.FALSE, Boolean.TRUE));
        }
        return errors;
    }

    /**
     * Run combinations on all properties included in the equals as specified by includedInEquals.  If an error is
     * found, test will return with that error and will not continue to check for other errors.
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     */
    private List<String> runCombinationTest()
            throws IllegalAccessException, InstantiationException, NoSuchFieldException,
            InvocationTargetException, NoSuchMethodException {

        final List<String> errors = new ArrayList<>();
        final T instanceOne = (T) clazz.newInstance();
        final T instanceTwo = (T) clazz.newInstance();

        // First test with default values in all properties
        if (!instanceOne.equals(instanceTwo)) {
            errors.add(String.format("Equals method test failed for %s.  Two instances with default values should be equal.",
                    getClassName()));
        }

        Map<String, PropertyDescriptor> descriptorMap =
                Arrays.stream(beanInfo.getPropertyDescriptors())
                        .collect(Collectors.toMap(pd -> pd.getName(), Function.identity()));
        for (String fieldName : includeInEquals) {
            PropertyDescriptor property = descriptorMap.get(fieldName);
            Field field = clazz.getDeclaredField(fieldName);
            Class<?> fieldType = field.getType();

            PropertyValue val = PropertyValues.fromString(fieldType.getCanonicalName());
            Object testVal = val.getValue();
            // Set both instances to have the same value.
            setFieldVal(instanceOne, property, field, testVal);
            setFieldVal(instanceTwo, property, field, testVal);

            if (!instanceOne.equals(instanceTwo)) {
                errors.add(String.format("Equals method test failed for %s. Equal values in %s caused the instances to no longer be equal.",
                        getClassName(), property.getDisplayName()));
                break;
            } else {
                setFieldVal(instanceTwo, property, field, val.getLargeValue());
                if (instanceOne.equals(instanceTwo)) {
                    errors.add(String.format("Equals method test failed for %s.  Non-equal values in %s did not cause inequality for instances.",
                            getClassName(), property.getDisplayName()));
                    break;
                }
                // Reset instance two value as it passed the test.
                setFieldVal(instanceTwo, property, field, testVal);
            }
        }

        return errors;
    }

    /**
     * Set the value using the setter if present, otherwise directly.
     *
     * @param instance Instance of class under test to operate on.
     * @param property Property being tested
     * @param field    Field being tested
     * @param testVal  Test value to use.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setFieldVal(T instance, PropertyDescriptor property, Field field, Object
            testVal) throws InvocationTargetException, IllegalAccessException {

        Method setter = (property == null) ? null : property.getWriteMethod();
        if (setter != null) {
            setter.invoke(instance, testVal);
        } else {
            field.setAccessible(true);
            field.set(instance, testVal);
        }
    }
}
