package net.digitaltsunami.pojot;

import java.util.Arrays;

/**
 * Utility to exercise the an Enum class to provide code coverage.  Iterates over all
 * constants and invokes the valueOf and toString for each.  If these methods have been overridden, then they
 * should be tested using an actual test.
 */
public class EnumExerciser {

    public static <E extends Enum<E>> long exercise(Class<E> targetEnum) {
        return Arrays.stream(targetEnum.getEnumConstants())
                .map(constant -> constant.valueOf(targetEnum, constant.toString()))
                .count();
    }
}
