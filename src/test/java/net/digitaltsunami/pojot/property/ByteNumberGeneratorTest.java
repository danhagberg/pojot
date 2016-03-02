package net.digitaltsunami.pojot.property;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * Created by dhagberg on 2/28/16.
 */
public class ByteNumberGeneratorTest {
    @Test
    public void testNumberRange() {
        Set<Byte> generated = new HashSet<>(255);
        for (int i = 0; i < 255; i++) {
            generated.add(ByteNumberGenerator.getNextNumber());
        }
        assertFalse(generated.contains(Byte.MIN_VALUE), "-128 value should have been excluded from generation");
        assertFalse(generated.contains(Byte.MAX_VALUE), "127 value should have been excluded from generation");
        assertFalse(generated.contains((byte)0), "Zero value should have been excluded from generation");
        assertFalse(generated.contains(null), "null value should have been excluded from generation");
        assertEquals(generated.size(), 252, "Generated did not generate the expected number of distinct values");
    }

}