package net.digitaltsunami.pojot.property;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by dhagberg on 2/28/16.
 */
public class StringGeneratorTest {
    @Test
    public void testGetNextString() {
        StringGenerator.getNextString(26*3);
        StringGenerator.getNextString(26*2+1);
        StringGenerator.getNextString(25);
        StringGenerator.getNextString(5);
        StringGenerator.getNextString(25);
    }

}