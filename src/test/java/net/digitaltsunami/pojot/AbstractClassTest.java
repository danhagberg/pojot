package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.AbstractBaseClass;
import net.digitaltsunami.pojot.testsubject.DerivedClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by dhagberg on 5/7/16.
 */
public class AbstractClassTest {
    @BeforeMethod
    public void setup() {
        AbstractBaseClass.getterInvoked = false;
        AbstractBaseClass.setterInvoked = false;
        DerivedClass.derivedGetterInvoked = false;
        DerivedClass.derivedSetterInvoked = false;
    }

    @Test
    public void validateGetters() throws IntrospectionException {
        List<String> errors = new TestAid(DerivedClass.class, true)
                .runGetterTests();
        assertEquals(errors.size(), 0, "DerivedClass did not pass getter validation. " + errors );
        assertTrue(AbstractBaseClass.getterInvoked, "Abstract getters were not tested.");
        assertTrue(DerivedClass.derivedGetterInvoked, "Derived getters were not tested.");
    }
    @Test
    public void validateSetters() throws IntrospectionException {
        List<String> errors = new TestAid(DerivedClass.class, true)
                .runSetterTests();
        assertEquals( errors.size(), 0, "DerivedClass did not pass setter validation. " + errors );
        assertTrue(AbstractBaseClass.setterInvoked, "Abstract setters were not tested.");
        assertTrue(DerivedClass.derivedSetterInvoked, "Derived setters were not tested.");
    }
    @Test
    public void validatePojo() throws IntrospectionException {
        List<String> errors = new TestAid(DerivedClass.class, true)
                .includeAllInEquals()
                .includeInEquals("testDerivedClassString", "testDerivedClassLong")
                .validate();
        assertEquals(errors.size(), 0, "DerivedClass did not pass basic validation. " + errors);
        assertTrue(AbstractBaseClass.getterInvoked, "Abstract getters were not tested.");
        assertTrue(AbstractBaseClass.setterInvoked, "Abstract setters were not tested.");
        assertTrue(DerivedClass.derivedGetterInvoked, "Derived getters were not tested.");
        assertTrue(DerivedClass.derivedSetterInvoked, "Derived setters were not tested.");
    }

    @Test
    public void validatePojoAbstractDisabled() throws IntrospectionException {
        List<String> errors = new TestAid(DerivedClass.class)
                .includeInEquals("testDerivedClassString", "testDerivedClassLong")
                .validate();
        assertEquals(errors.size(), 0, "DerivedClass did not pass basic validation. " + errors);
    }
}
