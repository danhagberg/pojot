package net.digitaltsunami.pojot;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by dhagberg on 3/5/16.
 */
public abstract class AbstractEqualityTestRunner<T> extends AbstractTestRunner<T> {
    protected final Set<String> includeInEquals;

    /**
     * Create a test runner for the provided class.
     *
     * @param clazz    Class that is the subject of the tests.  Non-null.
     * @param beanInfo Information on properties and methods for class under test.  Non-null.
     * @param includeInEquals Set of field names to include in equality testing. Non-null. Must be empty
     *                        set if no fields should be included.
     */
    public AbstractEqualityTestRunner(Class<T> clazz, BeanInfo beanInfo, Set<String> includeInEquals) {
        super(clazz, beanInfo);
        this.includeInEquals = includeInEquals;
    }

    @Override
    public List<String> runTests() {
        final List<String> errors = new ArrayList<>();
        if (includeInEquals.isEmpty()) {
            errors.addAll(runSimpleTest());
        } else {
            errors.addAll(runCombinationTest());
        }
        return errors;
    }

    /**
     * Run combinations on all properties included in the method as specified by includedInEquals.
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     * @throws TestAidException if reflection error occurs during testing.
     */
    protected abstract Collection<String> runCombinationTest();

    /**
     * Perform equality test when no fields are provided.
     *
     * @return List containing an error message if the test failed or empty if the test passed.
     * @throws TestAidException if reflection error occurs during testing.
     */
    protected abstract Collection<String> runSimpleTest();

    /**
     * Set the value using the setter if present, otherwise directly.
     *
     * @param instance Instance of class under test to operate on.
     * @param property Property being tested
     * @param field    Field being tested
     * @param testVal  Test value to use.
     * @throws InvocationTargetException if could not invoke the setter method.
     * @throws IllegalAccessException if unable to set the field.
     */
    protected void setFieldVal(T instance, PropertyDescriptor property, Field field, Object
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
