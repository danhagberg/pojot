package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.*;
import org.testng.annotations.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created on 2/6/16.
 */
public class EqualsTestRunnerTest {

    @Test
    public void testRunOfDefaultEquals() throws Exception {
        Class clazz = SimpleClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        EqualsTestRunner<SimpleClass> tester = new EqualsTestRunner<>(clazz, beanInfo);
        assertTrue(tester.runTests().size() == 0, "Hashcode should succeed when class does not provide a hashcode");
    }

    @Test
    public void testRunOfSpecializedEquals() throws Exception {
        Class clazz = EqualsHashTestClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        Set<String> fields = EqualsHashTestClass.getFieldsInHashcode();
        EqualsTestRunner<EqualsHashTestClass> tester = new EqualsTestRunner<>(clazz, beanInfo, fields);
        List<String> errors = tester.runTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testRunOfSpecializedEqualsCodeNoFields() throws Exception {
        Class clazz = BadHashcodeClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        EqualsTestRunner<BadHashcodeClass> tester = new EqualsTestRunner<>(clazz, beanInfo);
        List<String> errors = tester.runTests();
        assertTrue(errors.size() > 0, "Two different instances returned as equal");
    }
    @Test
    public void testRunOfSpecializedEqualsFieldNoSetterOrGetter() throws Exception {
        Class clazz = BadGetterSetterClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        EqualsTestRunner<BadGetterSetterClass> tester = new EqualsTestRunner<>(clazz, beanInfo, BadGetterSetterClass.getFieldsInHashCode());
        List<String> errors = tester.runTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testRunOfSpecializedEqualsBadDefaultFields() throws Exception {
        Class clazz = EqualsReturningNotEqualsDefaultValuesClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        Set<String> fields = EqualsReturningNotEqualsDefaultValuesClass.getFieldsInEqualsCode();

        EqualsTestRunner<EqualsReturningNotEqualsDefaultValuesClass> tester = new EqualsTestRunner<>(clazz, beanInfo, fields);
        List<String> errors = tester.runTests();
        assertTrue(errors.size() > 0, "Should have returned an error");
    }
    @Test
    public void testRunOfSpecializedEqualsBadSetFields() throws Exception {
        Class clazz = EqualsReturningNotEqualsClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        Set<String> fields = EqualsReturningNotEqualsClass.getFieldsInEqualsCode();

        EqualsTestRunner<EqualsReturningNotEqualsClass> tester = new EqualsTestRunner<>(clazz, beanInfo, fields);
        List<String> errors = tester.runTests();
        assertTrue(errors.size() > 0, "Should have returned an error");
    }

    @Test
    public void testRunOfSpecializedEqualsFailure() throws Exception {
        Class clazz = EqualsHashTestClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        Set<String> fields = EqualsHashTestClass.getFieldsNotInHashcode();
        EqualsTestRunner<EqualsHashTestClass> tester = new EqualsTestRunner<>(clazz, beanInfo, fields);
        List<String> errors = tester.runTests();
        assertTrue(errors.size() > 0, "Should have returned an error");
    }
}
