package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.TestEnumSingletonValues;
import net.digitaltsunami.pojot.testsubject.TestEnumValues;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 *
 */
public class EnumTestRunnerTest {

    @Test
    public void testRunTests() throws Exception {
        List<String> errors =
                new TestAid<TestEnumValues>(TestEnumValues.class)
                        .includeAllInEquals()
                        .validate();
        assertTrue(errors.isEmpty(), errors.toString());
    }
    @Test
    public void testRunTestWithEnumSingleton() throws Exception {
        List<String> errors =
                new TestAid<TestEnumSingletonValues>(TestEnumSingletonValues.class)
                        .includeAllInEquals()
                        .validate();
        assertTrue(errors.isEmpty(), errors.toString());
    }
}
