package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.SetterGetterForNonPropClass;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by dhagberg on 3/9/16.
 */
public class TestNonPropSettersAndGetters {
    @Test
    public void testIgnoresNonPropMethods() throws IntrospectionException {
        List<String> errors = new TestAid<SetterGetterForNonPropClass>(SetterGetterForNonPropClass.class)
                .includeAllInEquals()
                .validate();
        assertTrue(errors.isEmpty(), errors.toString());
    }
}
