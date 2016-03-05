package net.digitaltsunami.pojot.testsubject;

/**
 * Created by dhagberg on 3/5/16.
 */
public class EqualsHashAllFieldsTestClass {
    private boolean testBoolean;
    private Integer testInteger;
    private Object testObject;
    private String testString;
    private double testDouble;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EqualsHashAllFieldsTestClass that = (EqualsHashAllFieldsTestClass) o;

        if (testBoolean != that.testBoolean) return false;
        if (Double.compare(that.testDouble, testDouble) != 0) return false;
        if (testInteger != null ? !testInteger.equals(that.testInteger) : that.testInteger != null) return false;
        if (testObject != null ? !testObject.equals(that.testObject) : that.testObject != null) return false;
        return testString != null ? testString.equals(that.testString) : that.testString == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (testBoolean ? 1 : 0);
        result = 31 * result + (testInteger != null ? testInteger.hashCode() : 0);
        result = 31 * result + (testObject != null ? testObject.hashCode() : 0);
        result = 31 * result + (testString != null ? testString.hashCode() : 0);
        temp = Double.doubleToLongBits(testDouble);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
