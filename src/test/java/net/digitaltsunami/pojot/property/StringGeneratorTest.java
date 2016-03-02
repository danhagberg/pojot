package net.digitaltsunami.pojot.property;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * These tests assume the data string is a-zA-Z
 * If this assumption is incorrect, then these tests will fail.
 */
public class StringGeneratorTest {
    private static final String DATA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Test
    public void testGetNextChar() {
        StringGenerator.resetIndex();
        // Test first char
        assertEquals(StringGenerator.getNextChar(), (Character) DATA.charAt(0));
        // Test to ensure index was advanced.
        assertEquals(StringGenerator.getNextChar(), (Character) DATA.charAt(1));
    }
    @Test
    public void testGetSmallString() {
        StringGenerator.resetIndex();
        // Test 0 to size < length
        assertEquals(StringGenerator.getNextString(5), DATA.substring(0,5));
    }
    @Test
    public void testGetNextString() {
        StringGenerator.resetIndex();
        // Test 0 to full length
        assertEquals(StringGenerator.getNextString(DATA.length()), DATA);
        // Test 1 to remaining
        assertEquals(StringGenerator.getNextString(DATA.length() - 1), DATA.substring(1, DATA.length()));
        // Test 2 to remaining, then wrap around for 2
        assertEquals(StringGenerator.getNextString(DATA.length() - 1), DATA.substring(2, DATA.length()) + DATA.substring(0, 1));
    }

    @Test
    public void testGetNextEvenMultiple() {
        // These tests assume the data string is a-zA-Z
        // If this assumption is incorrect, then these tests will fail.
        StringGenerator.resetIndex();
        // Test 0 to full length
        assertEquals(StringGenerator.getNextString(DATA.length() * 3), DATA + DATA + DATA);
    }

    @Test
    public void testGetNextStringZero() {
        assertEquals(StringGenerator.getNextString(0), "");
    }

    @Test
    public void testGetNextStringNegative() {
        assertEquals(StringGenerator.getNextString(-1), "");
    }

}