package net.digitaltsunami.pojot.property;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 *
 */
public class IntPrimitiveValTest {

    private int expectedValue;

    @Test
    public void testGetDefaultValue() throws Exception {
        Assert.assertEquals(expectedValue, new IntPrimitiveVal().getDefaultValue());
    }
}