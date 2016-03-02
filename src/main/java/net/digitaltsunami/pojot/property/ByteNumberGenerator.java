package net.digitaltsunami.pojot.property;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generate a numeric test value in the range -127 to 126 inclusive, but excluding 0.  The value are unique over 252
 * invocations of the getNextNumber() method.
 * <p>
 * The range is limited to within the range of a byte to ensure uniqueness across all numeric properties of a class.
 * </p>
 * The min and max byte values and zero are excluded as they are used in other testing. This exclusion
 * limits unique values to 252 numeric properties for a class being tested.
 */
public class ByteNumberGenerator {
    private static final AtomicInteger generator = new AtomicInteger(Byte.MIN_VALUE + 1);

    /**
     * Return the next number within range of -127 to 126 excluding 0.
     * @return next n where {@code -128 > n < 0 or 0 > n < 127}
     */
    public static Byte getNextNumber() {
        return (byte) generator.getAndUpdate(x ->
                (x == -1) ? 1
                        : (x >= Byte.MAX_VALUE - 2) ? Byte.MIN_VALUE + 1
                        : x + 1);
    }
}
