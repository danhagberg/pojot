package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.EqualsHashAllFieldsTestClass;
import net.digitaltsunami.pojot.testsubject.EqualsHashTestClass;
import net.digitaltsunami.pojot.testsubject.FluentSetterClass;
import net.digitaltsunami.pojot.testsubject.SimpleClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by dhagberg on 1/16/16.
 */
public class TestAidTest {
    public void testx() throws IntrospectionException {

        List<String> errors = new TestAid<SimpleClass>(SimpleClass.class)
                .excludeFields("specialProp1", "specialProp2")
                .excludeSetters("propWithSetterLogic")
                .validate();
    }

    @Test
    public void testGetFieldsToCheckAllFields() throws Exception {
        TestAid ta = new TestAid(SimpleClass.class);
        Set<PropertyDescriptor> fields = ta.getFieldsToCheck();
        assertEquals(fields.size(), SimpleClass.getNumberOfFields());
    }

    @Test
    public void testGetFieldsToCheckInclusionList() throws Exception {
        List<String> names = SimpleClass.getFieldNames();
        String[] included = names.subList(0, 2).toArray(new String[]{});

        TestAid ta = new TestAid(SimpleClass.class).includeFields(included);
        Set<PropertyDescriptor> fields = ta.getFieldsToCheck();
        assertEquals(fields.size(), 2);
    }

    @Test
    public void testGetFieldsToCheckExclusionList() throws Exception {
        List<String> names = SimpleClass.getFieldNames();
        String[] excluded = names.subList(0, 2).toArray(new String[]{});
        TestAid ta = new TestAid(SimpleClass.class).excludeFields(excluded);
        Set<PropertyDescriptor> fields = ta.getFieldsToCheck();
        assertEquals(fields.size(), names.size() - 2);
    }

    @Test
    public void testGetFieldsToCheckInclusionAndExclusionList() throws Exception {
        List<String> names = SimpleClass.getFieldNames();
        String[] included = names.subList(0, 2).toArray(new String[]{});
        String[] excluded = names.subList(0, 2).toArray(new String[]{});
        TestAid ta = new TestAid(SimpleClass.class);
        ta.includeFields(included);
        ta.excludeFields(excluded);
        Set<PropertyDescriptor> fields = ta.getFieldsToCheck();
        // include and exclude cancel each other out.
        assertEquals(fields.size(), 0);
    }

    @Test
    public void testGetGettersToCheckInclusionList() throws Exception {
        TestAid ta = new TestAid(SimpleClass.class).includeGetters("startDate");
        Set<PropertyDescriptor> fields = ta.getGettersToCheck();
        assertEquals(fields.size(), 1);
    }

    @Test
    public void testGetGettersToCheckExclusionList() throws Exception {
        TestAid ta = new TestAid(SimpleClass.class).excludeGetters("startDate");
        Set<PropertyDescriptor> fields = ta.getGettersToCheck();
        assertEquals(fields.size(), 4);
    }
    @Test
    public void testGetGettersToCheckInclusionAndExclusionList() throws Exception {
        TestAid ta = new TestAid(SimpleClass.class).includeGetters("startDate");
        ta.excludeGetters("startDate");
        Set<PropertyDescriptor> fields = ta.getGettersToCheck();
        assertEquals(fields.size(), 0);
    }
    @Test
    public void testGetSettersToCheckInclusionList() throws Exception {
        TestAid ta = new TestAid(SimpleClass.class).includeSetters("startDate");
        Set<PropertyDescriptor> fields = ta.getSettersToCheck();
        assertEquals(fields.size(), 1);
    }

    @Test
    public void testGetSettersToCheckExclusionList() throws Exception {
        TestAid ta = new TestAid(SimpleClass.class).excludeSetters("startDate");
        Set<PropertyDescriptor> fields = ta.getSettersToCheck();
        assertEquals(fields.size(), 4);
    }
    @Test
    public void testGetSettersToCheckInclusionAndExclusionList() throws Exception {
        TestAid ta = new TestAid(SimpleClass.class).includeSetters("startDate");
        ta.excludeSetters("startDate");
        Set<PropertyDescriptor> fields = ta.getSettersToCheck();
        assertEquals(fields.size(), 0);
    }

    @Test
    public void testSetIncludeInEquals() throws Exception {
        TestAid ta = new TestAid(EqualsHashTestClass.class);
        ta.includeInEquals(EqualsHashTestClass.getFieldsInEquals().toArray(new String[]{}));
        List<String> errors = ta.runEqualsTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testSetIncludeAllInEquals() throws Exception {
        TestAid ta = new TestAid(EqualsHashAllFieldsTestClass.class);
        ta.includeAllInEquals();
        List<String> errors = ta.runEqualsTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testSetExcludeFromEquals() throws Exception {
        TestAid ta = new TestAid(EqualsHashTestClass.class);
        ta.excludeFromEquals(EqualsHashTestClass.getFieldsNotInEquals().toArray(new String[]{}));
        List<String> errors = ta.runEqualsTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testSetIncludeInEqualsButExcluded() throws Exception {
        TestAid ta = new TestAid(EqualsHashTestClass.class);
        String[] fields = EqualsHashTestClass.getFieldsInEquals().toArray(new String[]{});
        ta.excludeFields(fields);
        ta.includeInEquals(fields);
        List<String> errors = ta.runEqualsTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testSetIncludeInEqualsNegativeTest() throws Exception {
        TestAid ta = new TestAid(EqualsHashTestClass.class);
        ta.includeInEquals(EqualsHashTestClass.getFieldsNotInEquals().toArray(new String[]{}));
        List<String> errors = ta.runEqualsTests();
        assertTrue(errors.size() > 0, errors.toString());
    }

    @Test
    public void testSetIncludeInEqualsHashCode() throws Exception {
        TestAid ta = new TestAid(EqualsHashTestClass.class);
        ta.includeInEquals(EqualsHashTestClass.getFieldsInEquals().toArray(new String[]{}));
        List<String> errors = ta.runHashTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testSetIncludeAllInEqualsHashCode() throws Exception {
        TestAid ta = new TestAid(EqualsHashAllFieldsTestClass.class);
        ta.includeAllInEquals();
        List<String> errors = ta.runHashTests();
        assertTrue(errors.size() == 0, errors.toString());
    }

    @Test
    public void testSetExcludeFromEqualsHashCode() throws Exception {
        TestAid ta = new TestAid(EqualsHashTestClass.class);
        ta.excludeFromEquals(EqualsHashTestClass.getFieldsNotInEquals().toArray(new String[]{}));
        List<String> errors = ta.runHashTests();
        assertTrue(errors.size() == 0, errors.toString());
    }
    @Test
    public void testSetIncludeInEqualsHashCodeButExcluded() throws Exception {
        TestAid ta = new TestAid(EqualsHashTestClass.class);
        String[] fields = EqualsHashTestClass.getFieldsInEquals().toArray(new String[]{});
        ta.excludeFields(fields);
        ta.includeInEquals(fields);
        List<String> errors = ta.runHashTests();
        assertTrue(errors.size() == 0, errors.toString());
    }
    @Test
    public void testSetIncludeInEqualsHashCodeNegativeTest() throws Exception {
        TestAid ta = new TestAid(EqualsHashTestClass.class);
        ta.includeInEquals(EqualsHashTestClass.getFieldsNotInEquals().toArray(new String[]{}));
        List<String> errors = ta.runHashTests();
        assertTrue(errors.size() > 0, errors.toString());
    }

    @Test
    public void testSettersUseFluentStyle() throws IntrospectionException {
        List<String> errors = new TestAid(FluentSetterClass.class)
                .setClassUsesFluentSetters(true)
                .validate();
        // Fluent class has a setter that should cause an error. Only way to ensure fluent setters are tested.
        assertTrue(errors.size() > 0, errors.toString());
    }

}