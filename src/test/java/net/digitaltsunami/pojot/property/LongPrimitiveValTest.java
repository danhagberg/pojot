package net.digitaltsunami.pojot.property;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 *
 */
public class LongPrimitiveValTest {
    private long expectedValue;

    @Test
    public void testGetDefaultValue() throws Exception {
        Assert.assertEquals(expectedValue, new LongPrimitiveVal().getDefaultValue());
    }
}