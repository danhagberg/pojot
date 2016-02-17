package net.digitaltsunami.pojot.property;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 *
 */
public class FloatPrimitiveValTest {

    private float expectedValue;

    @Test
    public void testGetDefaultValue() throws Exception {
        Assert.assertEquals(expectedValue, new FloatPrimitiveVal().getDefaultValue());
    }
}