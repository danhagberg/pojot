package net.digitaltsunami.pojot;

import net.digitaltunami.pojot.testsubject.*;
import org.testng.annotations.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created on 2/6/16.
 */
public class HashcodeTestRunnerTest {

    @Test
    public void testRunOfDefaultHashCode() throws Exception {
        Class clazz = SimpleClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        HashcodeTestRunner<SimpleClass> tester = new HashcodeTestRunner<>(clazz, beanInfo);
        assertTrue(tester.runTests().size() == 0, "Hashcode should succeed when class does not provide a hashcode");
    }

    @Test
    public void testRunOfSpecializedHashCode() throws Exception {
        Class clazz = EqualsHashTestClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        Set<String> fields = EqualsHashTestClass.getFieldsInHashcode();
        HashcodeTestRunner<EqualsHashTestClass> tester = new HashcodeTestRunner<>(clazz, beanInfo, fields);
        List<String> errors = tester.runTests();
        assertTrue( errors.size() == 0, errors.toString());
    }

    @Test
    public void testRunOfSpecializedHashCodeFieldDontChangeCode() throws Exception {
        Class clazz = EqualsHashTestClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        Set<String> fields = EqualsHashTestClass.getFieldsNotInHashcode();
        HashcodeTestRunner<EqualsHashTestClass> tester = new HashcodeTestRunner<>(clazz, beanInfo, fields);
        List<String> errors = tester.runTests();
        assertTrue( errors.size() > 0, errors.toString());
    }
    @Test
    public void testRunOfSpecializedHashCodeNoFields() throws Exception {
        Class clazz = BadHashcodeClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        HashcodeTestRunner<BadHashcodeClass> tester = new HashcodeTestRunner<>(clazz, beanInfo);
        List<String> errors = tester.runTests();
        assertTrue(errors.size() > 0, "Two different instances returned same hash code");
    }

    @Test
    public void testRunOfSpecializedHashCodeFieldNoSetterOrGetter() throws Exception {
        Class clazz = BadGetterSetterClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        HashcodeTestRunner<BadGetterSetterClass> tester = new HashcodeTestRunner<>(clazz, beanInfo, BadGetterSetterClass.getFieldsInHashCode());
        List<String> errors = tester.runTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testRunOfSpecializedHashcodeBadDefaultFields() throws Exception {
        Class clazz = EqualsReturningNotEqualsDefaultValuesClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
        Set<String> fields = EqualsReturningNotEqualsDefaultValuesClass.getFieldsInEqualsCode();

        HashcodeTestRunner<EqualsReturningNotEqualsDefaultValuesClass> tester = new HashcodeTestRunner<>(clazz, beanInfo, fields);
        List<String> errors = tester.runTests();
        assertTrue(errors.size() > 0, "Should have returned an error");
    }

    @Test
    public void testRunOfSpecializedHashCodeFailure() throws Exception {
        Class clazz = EqualsHashTestClass.class;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());

        Set<String> fields = EqualsHashTestClass.getFieldsNotInHashcode();
        HashcodeTestRunner<EqualsHashTestClass> tester = new HashcodeTestRunner<>(clazz, beanInfo, fields);
        List<String> errors = tester.runTests();
        assertTrue(errors.size() > 0, "Should have returned an error");
    }
}
