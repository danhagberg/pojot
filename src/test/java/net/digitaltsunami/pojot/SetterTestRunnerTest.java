package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.BadGetterSetterClass;
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
        testRunner.runTests();
        List<String> errors = testRunner.runTests();
        assertTrue(errors.size() > 0, "Run test should have returned an error.");
    }

}