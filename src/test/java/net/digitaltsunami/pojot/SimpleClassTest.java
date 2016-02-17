package net.digitaltsunami.pojot;

import net.digitaltunami.pojot.testsubject.SimpleClass;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by dhagberg on 1/17/16.
 */
public class SimpleClassTest {
    @Test
    public void validateGetters() throws IntrospectionException {
        TestAid ta = new TestAid(SimpleClass.class);
        List<String> errors = ta.runGetterTests();
        assertTrue(errors.size() == 0, "SimpleClass did not pass getter validation. " + errors );
    }
    @Test
    public void validateSetters() throws IntrospectionException {
        TestAid ta = new TestAid(SimpleClass.class);
        List<String> errors = ta.runSetterTests();
        assertTrue(errors.size() == 0, "SimpleClass did not pass setter validation. " + errors );
    }
    @Test(enabled = false)
    public void validatePojo() throws IntrospectionException {
        TestAid ta = new TestAid(SimpleClass.class);
        assertTrue(ta.validate().size() == 0, "SimpleClass did not pass basic validation");
    }

}