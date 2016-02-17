package net.digitaltsunami.pojot;

import net.digitaltunami.pojot.testsubject.BadGetterSetterClass;
import net.digitaltunami.pojot.testsubject.BasicClass;
import net.digitaltunami.pojot.testsubject.ModifiedGetterSetterClass;
import net.digitaltunami.pojot.testsubject.SimpleClass;
import org.testng.annotations.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.List;

import static org.testng.Assert.*;

/**
 *
 */
public class GetterTestRunnerTest {

    @Test
    public void testRunTests() throws Exception {
        Class<SimpleClass> clazz = SimpleClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        GetterTestRunner<SimpleClass> testRunner = new GetterTestRunner<>(SimpleClass.class, beanInfo);
        List<String> errors = testRunner.runTests();
        assertEquals(0, errors.size());
    }
    @Test
    public void testRunTestsClassPropertyWithoutGetter() throws Exception {
        Class<BasicClass> clazz = BasicClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        GetterTestRunner<BasicClass> testRunner = new GetterTestRunner<>(BasicClass.class, beanInfo);
        List<String> errors = testRunner.runTests();
        assertEquals(0, errors.size());
    }

    @Test
    public void testRunTestsWithModifiedGetter() throws Exception {
        Class<ModifiedGetterSetterClass> clazz = ModifiedGetterSetterClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        GetterTestRunner<ModifiedGetterSetterClass> testRunner =
                new GetterTestRunner<>(ModifiedGetterSetterClass.class, beanInfo);
        testRunner.runTests();
        List<String> errors = testRunner.runTests();
        assertTrue(errors.size() > 0, "Run test should have returned an error.");
    }

    @Test(expectedExceptions = TestAidException.class)
    public void testRunTestsWithError() throws Exception {
        Class<SimpleClass> clazz = SimpleClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        GetterTestRunner<BadGetterSetterClass> testRunner =
                new GetterTestRunner<>(BadGetterSetterClass.class, beanInfo);
        testRunner.runTests();
        fail("An exception should have been thrown");
    }
}