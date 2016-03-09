package net.digitaltsunami.pojot.testsubject;

/**
 * Created by dhagberg on 3/8/16.
 */
public class TestEnumValues {
    private TestEnum enum1;
    private TestEnum enum2;

    public TestEnum getEnum1() {
        return enum1;
    }

    public void setEnum1(TestEnum enum1) {
        this.enum1 = enum1;
    }

    public TestEnum getEnum2() {
        return enum2;
    }

    public void setEnum2(TestEnum enum2) {
        this.enum2 = enum2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEnumValues that = (TestEnumValues) o;

        if (enum1 != that.enum1) return false;
        return enum2 == that.enum2;

    }

    @Override
    public int hashCode() {
        int result = enum1 != null ? enum1.hashCode() : 0;
        result = 31 * result + (enum2 != null ? enum2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestEnumValues{" +
                "enum1=" + enum1 +
                ", enum2=" + enum2 +
                '}';
    }
}
