package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.FluentSetterClass;
import net.digitaltsunami.pojot.testsubject.ModifiedGetterSetterClass;
import net.digitaltsunami.pojot.testsubject.SimpleClass;
import org.testng.annotations.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.List;

import static org.testng.Assert.*;

/**
 *
 */
public class SetterTestRunnerTest {

    @Test
    public void testRunTests() throws Exception {
        Class<SimpleClass> clazz = SimpleClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        SetterTestRunner<SimpleClass> testRunner = new SetterTestRunner<>(SimpleClass.class, beanInfo);
        List<String> errors = testRunner.runTests();
        assertEquals(0, errors.size());
    }

    @Test
    public void testRunTestsWithModifiedSetter() throws Exception {
        Class<ModifiedGetterSetterClass> clazz = ModifiedGetterSetterClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        SetterTestRunner<ModifiedGetterSetterClass> testRunner =
                new SetterTestRunner<>(ModifiedGetterSetterClass.class, beanInfo);
        List<String> errors = testRunner.runTests();
        assertTrue(errors.size() > 0, "Run test should have returned an error.");
    }

    @Test
    public void testRunFluentTestsWithModifiedSetter() throws Exception {
        Class<FluentSetterClass> clazz = FluentSetterClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        SetterTestRunner<FluentSetterClass> testRunner =
                new SetterTestRunner<>(FluentSetterClass.class, beanInfo);
        testRunner.setFluentStyle(true);
        List<String> errors = testRunner.runTests();
        assertEquals(errors.size(), 1, "Run test should have returned exactly one error.");
    }
}