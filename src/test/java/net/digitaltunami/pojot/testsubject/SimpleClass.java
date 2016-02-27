package net.digitaltunami.pojot.testsubject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dhagberg on 1/16/16.
 */
public class SimpleClass extends BasicClass {
    private final String testString0;
    private Date startDate;
    private Integer testInteger1;
    private int testInt2;
    private OtherClass otherClass;

    public SimpleClass() {
        testString0 = "Default Value";
    }
    public SimpleClass(String testString) {
        this.testString0 = testString;
    }

    public static int getNumberOfFields() {
        return 5;
    }
    public static List<String> getFieldNames() {
        // Cant use reflection. When running under jacoco, it adds an additional field for internal use.
        return Stream.of("testString0", "startDate", "testInteger1", "testInt2", "otherClass")
                 .collect(Collectors.toList());
    }

    public static List<String> getMethodNames() {
        return Arrays.asList("getTestString0", "getTestInteger1", "getTestInt2", "getStartDate", "getOtherClass"
        , "setTestInteger1", "setTestInt2", "setStartDate", "setOtherClass" );
    }

    public static int getNumberOfMethods() {
        return getNumberOfFields() * 2 - 1; // getter setter pairs - 1 setter for the final field.
    }

    public String getTestString0() {
        return testString0;
    }

    public Integer getTestInteger1() {
        return testInteger1;
    }

    public void setTestInteger1(Integer testInteger1) {
        this.testInteger1 = testInteger1;
    }

    public int getTestInt2() {
        return testInt2;
    }

    public void setTestInt2(int testInt2) {
        this.testInt2 = testInt2;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public OtherClass getOtherClass() {
        return otherClass;
    }

    public void setOtherClass(OtherClass otherClass) {
        this.otherClass = otherClass;
    }

    @Override
    public String toString() {
        return new StringBuilder("SimpleClass: ")
                .append("testString0=")
                .append(testString0)
                .toString();
    }
}
