package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.CollectionsTestClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.beans.IntrospectionException;
import java.util.List;

/**
 * Created by dhagberg on 3/5/16.
 */
public class CollectionsTypesTest {
    @Test
    public void testCollections() throws IntrospectionException {

    List<String> errors = new TestAid<CollectionsTestClass>(CollectionsTestClass.class)
            .includeAllInEquals()
            .validate();
        assertTrue(errors.isEmpty(), errors.toString());
    }
}
