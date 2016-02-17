package net.digitaltsunami.pojot.property;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 *
 */
public class ShortPrimitiveValTest {
    private short expectedValue;

    @Test
    public void testGetDefaultValue() throws Exception {
        Assert.assertEquals(expectedValue, new ShortPrimitiveVal().getDefaultValue());
    }
}