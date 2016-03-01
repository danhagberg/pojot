package net.digitaltsunami.pojot;

import net.digitaltunami.pojot.testsubject.ClassWithBoolean;
import net.digitaltunami.pojot.testsubject.SimpleClass;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by dhagberg on 1/17/16.
 */
public class SimpleBooleanClassTest {
    @Test
    public void validateGetters() throws IntrospectionException {
        TestAid ta = new TestAid(ClassWithBoolean.class);
        List<String> errors = ta.runGetterTests();
        assertTrue(errors.size() == 0, "SimpleClass did not pass getter validation. " + errors );
    }
    @Test
    public void validateSetters() throws IntrospectionException {
        TestAid ta = new TestAid(ClassWithBoolean.class);
        List<String> errors = ta.runSetterTests();
        assertTrue(errors.size() == 0, "SimpleClass did not pass setter validation. " + errors );
    }
    @Test
    public void validatePojo() throws IntrospectionException {
        List<String> errors = new TestAid(ClassWithBoolean.class)
                .includeInEquals("booleanPrimitive", "booleanObject", "name")
                .validate();
        assertTrue(errors.isEmpty(), errors.toString());
    }

}