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
public class EqualsTestRunner<T> extends AbstractEqualityTestRunner<T> {

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
        super(clazz, beanInfo, includeInEquals);
    }

    /**
     * Test if two instances of the same class are equal if fields are not provided for equality testing. If no
     * field were provided, then an instance is equal to another instance IFF they are the same instance (==).
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     * @throws TestAidException if unable to properly execute the task.
     */
    protected List<String> runSimpleTest() {
        final List<String> errors = new ArrayList<>();
        final T instanceOne;
        try {
            instanceOne = (T) clazz.newInstance();
            final T instanceTwo = (T) clazz.newInstance();
            if (instanceOne.equals(instanceTwo)) {
                errors.add(String.format("Equals method test failed for %s.  No fields provided, so default equals (==) expected, but equals method depended on fields.", getClassName()));
            }
        } catch (InstantiationException
                | IllegalAccessException e) {
            throw new TestAidException("Unable to complete Equals test due to reflection error.", e);
        }
        return errors;
    }

    /**
     * Run combinations on all properties included in the equals as specified by includedInEquals.  If an error is
     * found, test will return with that error and will not continue to check for other errors.
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     */
    protected List<String> runCombinationTest() {

        final List<String> errors = new ArrayList<>();
        try {
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
                            getClassName(), fieldName));
                    break;
                } else {
                    Object alternateTestValue = val.getLargeValue();
                    // If the test value and alternate test value are the same, bypass test as this type can return
                    // only one value and can't be used in testing.
                    if (testVal != alternateTestValue) {
                        setFieldVal(instanceTwo, property, field, alternateTestValue);
                        if (instanceOne.equals(instanceTwo)) {
                            errors.add(String.format("Equals method test failed for %s.  Non-equal values in %s did not cause inequality for instances.",
                                    getClassName(), fieldName));
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
                | NoSuchFieldException e) {
            throw new TestAidException("Unable to complete Equals test due to reflection error.", e);
        }


        return errors;
    }

}
