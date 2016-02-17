package net.digitaltsunami.pojot;

import net.digitaltunami.pojot.testsubject.BadToStringClass;
import net.digitaltunami.pojot.testsubject.SimpleClass;
import org.testng.annotations.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

/**
 *
 */
public class ToStringTestRunnerTest {
    @Test
    public void testRunOfToString() throws Exception {
        Class clazz = SimpleClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        ToStringTestRunner<SimpleClass> tester = new ToStringTestRunner<>(clazz, beanInfo);
            assertTrue(tester.runTests().size() == 0, "toString should not have thrown an error.");
    }

    @Test
    public void testRunOfToStringWithNPE() throws Exception {
        Class clazz = BadToStringClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        ToStringTestRunner<BadToStringClass> tester = new ToStringTestRunner<>(clazz, beanInfo);
        try {
            assertTrue(tester.runTests().size() > 0, "toString should have returned an error.");
        } catch (NullPointerException e) {
            fail("Null pointer exception should have been handled.");

        }
    }

}