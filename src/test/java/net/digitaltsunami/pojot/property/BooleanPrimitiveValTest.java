package net.digitaltsunami.pojot.property;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class BooleanPrimitiveValTest {
    private boolean expectedValue;
    @Test
    public void testGetDefaultValue() throws Exception {
        Assert.assertEquals(expectedValue, new BooleanPrimitiveVal().getDefaultValue());
    }
}