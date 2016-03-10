package net.digitaltsunami.pojot.testsubject;

/**
 * Created by dhagberg on 3/9/16.
 */
public class SetterGetterForNonPropClass {
    private String testString1;

    public String getTestString1() {
        return testString1;
    }

    public void setTestString1(String testString1) {
        this.testString1 = testString1;
    }

    public String getNonProp() {
        return testString1;
    }
    public void setNonProp(String value) {
        testString1 = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetterGetterForNonPropClass that = (SetterGetterForNonPropClass) o;

        return testString1 != null ? testString1.equals(that.testString1) : that.testString1 == null;

    }

    @Override
    public int hashCode() {
        return testString1 != null ? testString1.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SetterGetterForNonPropClass{" +
                "testString1='" + testString1 + '\'' +
                '}';
    }
}
