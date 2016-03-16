package net.digitaltsunami.pojot;

import net.digitaltsunami.pojot.testsubject.InnerClassAndEnum;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by dhagberg on 3/15/16.
 */
public class TestInnerClassAndEnum {
    @Test
    public void testWithInnerClassAndEnum() throws IntrospectionException {
        List<String> errors = new TestAid< InnerClassAndEnum>(InnerClassAndEnum.class)
                .includeAllInEquals()
                .validate();
        assertTrue(errors.isEmpty(), errors.toString());
    }
}
