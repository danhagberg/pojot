package net.digitaltsunami.pojot;

import java.beans.BeanInfo;

/**
 * Base class for test runners providing the class under test and bean information.
 * Concrete classes must provide the {@link TestRunner#runTests()} method.
 */
public abstract class AbstractTestRunner<T> implements TestRunner<T> {

    protected final Class<T> clazz;
    protected final BeanInfo beanInfo;

    /**
     * Create a test runner for the provided class.
     * @param clazz Class that is the subject of the tests.  Non-null.
     * @param beanInfo Information on properties and methods for class under test.  Non-null.
     */
    public AbstractTestRunner(Class<T> clazz, BeanInfo beanInfo) {
        this.clazz = clazz;
        this.beanInfo = beanInfo;
    }

    /**
     * Return the name of the class under test.  This name will not include the package name.
     * @return Name of class under test.
     */
    protected String getClassName() {
        return clazz.getName();
    }

    /**
     * Return the @BeanInfo for the class under test.
     * @return BeanInfo for class under test.  Will be non-null.
     */
    protected BeanInfo getBeanInfo() {
        return beanInfo;
    }
}
