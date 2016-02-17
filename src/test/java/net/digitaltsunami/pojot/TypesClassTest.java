package net.digitaltsunami.pojot;

import net.digitaltunami.pojot.testsubject.TypesClass;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by dhagberg on 1/17/16.
 */
public class TypesClassTest {
    @Test
    public void validateGetters() throws IntrospectionException {
        TestAid ta = new TestAid(TypesClass.class);
        List<String> errors = ta.runGetterTests();
        assertTrue(errors.size() == 0, "TypesClass did not pass getter validation. " + errors );
    }
    @Test
    public void validateSetters() throws IntrospectionException {
        TestAid ta = new TestAid(TypesClass.class);
        List<String> errors = ta.runSetterTests();
        assertTrue(errors.size() == 0, "TypesClass did not pass setter validation. " + errors );
    }
    @Test
    public void validatePojo() throws IntrospectionException {
        TestAid ta = new TestAid(TypesClass.class);
        List errors = ta.validate();
        assertTrue(errors.size() == 0, "TypesClass did not pass basic validation.  \nErrors: " + errors);
    }
}