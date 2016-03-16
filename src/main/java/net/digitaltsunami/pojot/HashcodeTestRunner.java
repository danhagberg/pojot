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
public class HashcodeTestRunner<T> extends AbstractEqualityTestRunner<T> {

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
        super(clazz, beanInfo, includeInHashcode);
    }

    /**
     * Test if two instances of the same class have the same hash code if fields are not provided for equality testing.
     * If no fields were provided, then hash code is equal to another instance IFF they are the same instance (==).
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     */
    protected List<String> runSimpleTest() {
        final List<String> errors = new ArrayList<>();

        try {
            final T instanceOne = clazz.newInstance();
            final T instanceTwo = clazz.newInstance();
            final int hashCodeOne = instanceOne.hashCode();
            final int hashCodeTwo = instanceTwo.hashCode();

            if (hashCodeOne == hashCodeTwo) {
                errors.add(String.format("Two different instances of %s returned the same hash code when using default hashCode method.",
                        getClassName(), hashCodeOne, hashCodeTwo));
            }
        } catch (InstantiationException
                | IllegalAccessException e) {
            throw new TestAidException("Unable to complete HashCode test due to reflection error.", e);
        }
        return errors;
    }

    /**
     * Run combinations on all values included in the hashcode as specified by includedInHashcode.  If an error is
     * found, test will return with that error and will not continue to check for other errors.
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     */
    protected List<String> runCombinationTest() {

        final List<String> errors = new ArrayList<>();
        try {
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
            for (String fieldName : includeInEquals) {

                PropertyDescriptor property = descriptorMap.get(fieldName);
                if (property == null) {
                    continue;  // No property for this field name. Skip processing.
                }
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
                    Object alternateTestValue = val.getLargeValue();
                    // If the test value and alternate test value are the same, bypass test as this type can return
                    // only one value and can't be used in testing.
                    if (testVal != alternateTestValue) {
                        setFieldVal(instanceTwo, property, field, alternateTestValue);
                        if (instanceOne.equals(instanceTwo)) {
                            errors.add(String.format("Hashcode method test failed for %s.  Non-equal values in %s did not cause different hashcode for instances.",
                                    getClassName(), property.getDisplayName()));
                            break;
                        }
                        // Reset instance two value as it passed the test.
                        setFieldVal(instanceTwo, property, field, testVal);
                    }
                }
            }

        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException
                | NoSuchFieldException e) {
            throw new TestAidException("Unable to complete HashCode test due to reflection error.", e);
        }

        return errors;
    }
}
