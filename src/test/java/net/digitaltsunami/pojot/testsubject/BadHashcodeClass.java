package net.digitaltsunami.pojot.testsubject;

/**
 *
 */
public class BadHashcodeClass {

    public final int value = 10;
    public final int value2 = 20;
    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BadHashcodeClass)) return false;

        BadHashcodeClass that = (BadHashcodeClass) o;

        return value == that.value;

    }
}
