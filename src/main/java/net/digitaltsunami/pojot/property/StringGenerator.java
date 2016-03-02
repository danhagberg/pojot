package net.digitaltsunami.pojot.property;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generates string values with variability to provide collision free string values for a minimum
 * of 52 invocations.  The number of collision free strings increases as requested string size
 * varies.
 *
 */
public class StringGenerator {
    private static final String DATA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int DATA_LEN = DATA.length();
    private static final AtomicInteger index = new AtomicInteger(0);

    public static String getNextString(int size) {
        int bIdx = index.getAndIncrement();
        StringBuilder value = new StringBuilder(size);

        int pe = bIdx + size;

        if (pe < DATA_LEN) {
            value.append(DATA.substring(bIdx, pe));
        }
        else if (pe > DATA_LEN) {
            value.append(DATA.substring(bIdx, DATA_LEN));
            int remaining = size - (DATA_LEN - bIdx);
            int multiples = remaining / DATA_LEN;
            for (int i = 0; i < multiples; i++) {
                value.append(DATA.substring(bIdx));
            }
            value.append(DATA.substring(0, remaining % DATA_LEN));
        }
        else { // pe == DATA_LEN
            value.append(DATA.substring(bIdx, DATA_LEN));
        }

        return value.toString();
    }

    /**
     * Return the next character in the sequence.
     * @return non-null and non-empty Character value
     */
    public static Character getNextChar() {
        return DATA.charAt(index.getAndIncrement());
    }
}
