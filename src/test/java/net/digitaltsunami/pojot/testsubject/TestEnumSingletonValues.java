package net.digitaltsunami.pojot.testsubject;

/**
 * Created by dhagberg on 3/9/16.
 */
public class TestEnumSingletonValues {
    private TestEnumSingleton enumWithOneValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEnumSingletonValues that = (TestEnumSingletonValues) o;

        return enumWithOneValue == that.enumWithOneValue;

    }

    @Override
    public int hashCode() {
        return enumWithOneValue != null ? enumWithOneValue.hashCode() : 0;
    }

    public TestEnumSingleton getEnumWithOneValue() {
        return enumWithOneValue;
    }

    public void setEnumWithOneValue(TestEnumSingleton enumWithOneValue) {
        this.enumWithOneValue = enumWithOneValue;
    }

    @Override
    public String toString() {
        return "TestEnumSingletonValues{" +
                "enumWithOneValue=" + enumWithOneValue +
                '}';
    }
}
