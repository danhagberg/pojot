package net.digitaltsunami.pojot.testsubject;

/**
 * Created by dhagberg on 5/7/16.
 */
public class DerivedClass extends AbstractBaseClass {
    private String testDerivedClassString;
    private Long testDerivedClassLong;
    public static boolean derivedGetterInvoked;
    public static boolean derivedSetterInvoked;

    @Override
    public void testMethod() {
    }

    @Override
    public String toString() {
        return "DerivedClass{" +
                "testDerivedClassString='" + testDerivedClassString + '\'' +
                ", testDerivedClassLong=" + testDerivedClassLong +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DerivedClass that = (DerivedClass) o;

        if (testDerivedClassString != null ? !testDerivedClassString.equals(that.testDerivedClassString) : that.testDerivedClassString != null)
            return false;
        return testDerivedClassLong != null ? testDerivedClassLong.equals(that.testDerivedClassLong) : that.testDerivedClassLong == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (testDerivedClassString != null ? testDerivedClassString.hashCode() : 0);
        result = 31 * result + (testDerivedClassLong != null ? testDerivedClassLong.hashCode() : 0);
        return result;
    }

    public String getTestDerivedClassString() {
        derivedGetterInvoked = true;
        return testDerivedClassString;
    }

    public void setTestDerivedClassString(String testDerivedClassString) {
        derivedSetterInvoked = true;
        this.testDerivedClassString = testDerivedClassString;
    }

    public Long getTestDerivedClassLong() {
        derivedGetterInvoked = true;
        return testDerivedClassLong;
    }

    public void setTestDerivedClassLong(Long testDerivedClassLong) {
        derivedSetterInvoked = true;
        this.testDerivedClassLong = testDerivedClassLong;
    }
}
