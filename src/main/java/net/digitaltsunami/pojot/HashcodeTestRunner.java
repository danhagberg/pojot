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
 * Test to ensure that stated fields have an effect on hash code and null values do not cause a NPE.
 * Tests include:
 * <ul>
 * <li>Test of default hashCode method (no properties provided).  Ensures that hashCode is same IFF instances are same (==) </li>
 * <li>Test to ensure that all provided properties affect the hash code.</li>
 * </ul>
 * Currently, there is no check to ensure that the list of fields provided is all inclusive.  If a field not provided
 * has an effect on the hash code, then this will not be found.
 */
public class HashcodeTestRunner<T> extends AbstractTestRunner<T> {

    private final Set<String> includeInHashcode;

    /**
     * Create a test runner for the class under test that includes no special calculation of the hash code.
     *
     * @param clazz    Class under test.
     * @param beanInfo Info on methods and properties of class under test.
     */
    public HashcodeTestRunner(Class clazz, BeanInfo beanInfo) {
        this(clazz, beanInfo, Collections.emptySet());
    }

    /**
     * Create a test runner for the class under test and provide the names of the properties that are
     * included in the hash code calculation.
     *
     * @param clazz             Class under test.
     * @param beanInfo          Info on methods and properties of class under test.
     * @param includeInHashcode names of properties that are used to build the hashcode.
     */
    public HashcodeTestRunner(Class clazz, BeanInfo beanInfo, Set<String> includeInHashcode) {
        super(clazz, beanInfo);
        this.includeInHashcode = includeInHashcode;
    }

    @Override
    public List<String> runTests() {
        final List<String> errors = new ArrayList<>();
        try {
            if (includeInHashcode.isEmpty()) {
                errors.addAll(runSimpleTest());
            } else {
                errors.addAll(runCombinationTest());
            }
        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException
                | NoSuchFieldException e) {
            throw new TestAidException("Error during hashCode test", e);
        }
        return errors;
    }

    /**
     * Test if two instances of the same class have the same hash code if fields are not provided for equality testing.
     * If no fields were provided, then hash code is equal to another instance IFF they are the same instance (==).
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private List<String> runSimpleTest() throws IllegalAccessException, InstantiationException {
        final List<String> errors = new ArrayList<>();

        final T instanceOne = (T) clazz.newInstance();
        final T instanceTwo = (T) clazz.newInstance();
        final int hashCodeOne = instanceOne.hashCode();
        final int hashCodeTwo = instanceTwo.hashCode();

        if (hashCodeOne == hashCodeTwo) {
            errors.add(String.format("Two different instances of %s returned the same hash code when using default hashCode method.",
                    getClassName(), hashCodeOne, hashCodeTwo));
        }
        return errors;
    }

    /**
     * Run combinations on all values included in the hashcode as specified by includedInHashcode.  If an error is
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

        Method hashCodeMethod = clazz.getDeclaredMethod("hashCode");
        if (hashCodeMethod == null) {
            return errors;
        }

        // First test with default values in all properties
        if (instanceOne.hashCode() != instanceTwo.hashCode()) {
            errors.add(String.format("Hashcode method test failed for %s.  Two instances with default values should return the same hashcode.",
                    getClassName()));
        }
        Set<Integer> hashCodes = new HashSet<>();

        Map<String, PropertyDescriptor> descriptorMap =
                Arrays.stream(beanInfo.getPropertyDescriptors())
                        .collect(Collectors.toMap(pd -> pd.getName(), Function.identity()));
        for (String fieldName : includeInHashcode) {

            PropertyDescriptor property = descriptorMap.get(fieldName);
            Field field = clazz.getDeclaredField(fieldName);
            Class<?> fieldType = field.getType();

            PropertyValue val = PropertyValues.fromString(fieldType.getCanonicalName());
            Object testVal = val.getValue();
            setFieldVal(instanceOne, property, field, testVal);

            // If the newly added hashcode is already in the set, then changing the current property had no effect.
            if (!hashCodes.add((Integer) hashCodeMethod.invoke(instanceOne))) {
                errors.add(String.format("Hashcode test failed for %s.  Change in value for %s did not affect hashcode.",
                        getClassName(), property.getDisplayName()));
                break;
            } else {
                setFieldVal(instanceTwo, property, field, val.getLargeValue());
                if (instanceOne.equals(instanceTwo)) {
                    errors.add(String.format("Hashcode method test failed for %s.  Non-equal values in %s did not cause different hashcode for instances.",
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
