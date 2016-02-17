package net.digitaltsunami.pojot;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple test to ensure that toString will not error when running default values.
 */
public class ToStringTestRunner<T> extends AbstractTestRunner<T> {
    /**
     * Create a test runner for the provided class.
     *
     * @param clazz    Class that is the subject of the tests.  Non-null.
     * @param beanInfo Information on properties and methods for class under test.  Non-null.
     */
    public ToStringTestRunner(Class<T> clazz, BeanInfo beanInfo) {
        super(clazz, beanInfo);
    }

    @Override
    public List<String> runTests() throws TestAidException {
        final List<String> errors = new ArrayList<>();
        final T instanceOne;
        try {
            instanceOne = (T) clazz.newInstance();
            instanceOne.toString();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new TestAidException("ToString test failed", e);
        } catch (NullPointerException e) {
            errors.add(String.format("toString method test failed for %s.",
                    getClassName()));
        }
        return errors;
    }
}
