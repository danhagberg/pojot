package net.digitaltsunami.pojot.testsubject;

/**
 * Created by dhagberg on 3/15/16.
 */
public class InnerClassAndEnum {
    public enum TestSimpleEnum {
        SE_VAL_1,
        SE_VAL_2;
    }

    public enum TestEnumWithCons {
        VAL_1("val_1"),
        VAL_2("val_2");

        private String someVal;
        private TestEnumWithCons(String someVal) {
            this.someVal = someVal;
        }
    }

    public static class InnerClass {
        public int getInnerInt() {
            return innerInt;
        }

        public void setInnerInt(int innerInt) {
            this.innerInt = innerInt;
        }

        private int innerInt;
    }



    private TestSimpleEnum simpleEnum;
    private TestEnumWithCons enumWithCons;
    private InnerClass innerClass;

    public TestSimpleEnum getSimpleEnum() {
        return simpleEnum;
    }

    public void setSimpleEnum(TestSimpleEnum simpleEnum) {
        this.simpleEnum = simpleEnum;
    }

    public TestEnumWithCons getEnumWithCons() {
        return enumWithCons;
    }

    public void setEnumWithCons(TestEnumWithCons enumWithCons) {
        this.enumWithCons = enumWithCons;
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnerClassAndEnum that = (InnerClassAndEnum) o;

        if (simpleEnum != that.simpleEnum) return false;
        if (enumWithCons != that.enumWithCons) return false;
        return innerClass != null ? innerClass.equals(that.innerClass) : that.innerClass == null;

    }

    @Override
    public int hashCode() {
        int result = simpleEnum != null ? simpleEnum.hashCode() : 0;
        result = 31 * result + (enumWithCons != null ? enumWithCons.hashCode() : 0);
        result = 31 * result + (innerClass != null ? innerClass.hashCode() : 0);
        return result;
    }
}
