package net.digitaltsunami.pojot.testsubject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to test that equals and hashcode tests correctly permute all fields provided to TestAid and ignore fields
 * not included.
 * Created on 2/6/16.
 */
public class EqualsHashTestClass {
    private String testString;
    private Integer testInteger;
    private long testPrimitiveLong;

    private Date testNotIncludedDate;
    private int testNotIncludedInt;

    private final static Set<String> fieldsInEquals;
    private final static Set<String> fieldsNotInEquals;

    static {
        fieldsInEquals = new HashSet<String>();
        fieldsInEquals.add("testString");
        fieldsInEquals.add("testInteger");
        fieldsInEquals.add("testPrimitiveLong");

        fieldsNotInEquals = new HashSet<String>();
        fieldsNotInEquals.add("testNotIncludedDate");
        fieldsNotInEquals.add("testNotIncludedInt");
        //fieldsNotInEquals.add("fieldsInEquals");
        //fieldsNotInEquals.add("fieldsNotInEquals");
    };

    public static Set<String> getFieldsInEquals() {
        return fieldsInEquals;
    }
    public static Set<String> getFieldsInHashcode() {
        return fieldsInEquals;
    }
    public static Set<String> getFieldsNotInEquals() {
        return fieldsNotInEquals;
    }
    public static Set<String> getFieldsNotInHashcode() {
        return fieldsNotInEquals;
    }

    public Date getTestNotIncludedDate() {
        return testNotIncludedDate;
    }

    public void setTestNotIncludedDate(Date testNotIncludedDate) {
        this.testNotIncludedDate = testNotIncludedDate;
    }

    public int getTestNotIncludedInt() {
        return testNotIncludedInt;
    }

    public void setTestNotIncludedInt(int testNotIncludedInt) {
        this.testNotIncludedInt = testNotIncludedInt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EqualsHashTestClass)) return false;

        EqualsHashTestClass that = (EqualsHashTestClass) o;

        if (testPrimitiveLong != that.testPrimitiveLong) return false;
        if (testString != null ? !testString.equals(that.testString) : that.testString != null) return false;
        return !(testInteger != null ? !testInteger.equals(that.testInteger) : that.testInteger != null);

    }

    @Override
    public int hashCode() {
        int result = 0;
        result = testString != null ? testString.hashCode() : 0;
        result = 31 * result + (testInteger != null ? testInteger.hashCode() : 0);
        result = 31 * result + (int) (testPrimitiveLong ^ (testPrimitiveLong >>> 32));
        return result;
    }

    public String getTestString() {

        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public Integer getTestInteger() {
        return testInteger;
    }

    public void setTestInteger(Integer testInteger) {
        this.testInteger = testInteger;
    }

    public long getTestPrimitiveLong() {
        return testPrimitiveLong;
    }

    public void setTestPrimitiveLong(long testPrimitiveLong) {
        this.testPrimitiveLong = testPrimitiveLong;
    }
}
