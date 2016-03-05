package net.digitaltsunami.pojot.testsubject;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class BadGetterSetterClass {
    private String testString1;
    private String testString2;

    private static final Set<String> fieldsInHashCode;
    static {
        fieldsInHashCode =  Stream.of("testString1", "testString2")
                .collect(Collectors.toSet());
    }

    public static Set<String> getFieldsInHashCode() {
        return fieldsInHashCode;
    }

    private String getTestString1() {
        return testString1;
    }
    private void setTestString1(String testString1) {
        this.testString1 = testString1;
    }
    public String getTestString2() {
        return testString2;
    }
    public void setTestString2(String testString2) {
        this.testString2 = testString2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BadGetterSetterClass)) return false;

        BadGetterSetterClass that = (BadGetterSetterClass) o;

        if (testString1 != null ? !testString1.equals(that.testString1) : that.testString1 != null) return false;
        return !(testString2 != null ? !testString2.equals(that.testString2) : that.testString2 != null);

    }

    @Override
    public int hashCode() {
        int result = testString1 != null ? testString1.hashCode() : 0;
        result = 31 * result + (testString2 != null ? testString2.hashCode() : 0);
        return result;
    }
}
