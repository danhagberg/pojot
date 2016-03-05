package net.digitaltsunami.pojot.testsubject;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fields are in hashcode and equals, but no getter or setters so no property values returned for the bean.
 * This class is used to test for failure as it initializes the value to a different value for each instance.
 */
public class EqualsReturningNotEqualsDefaultValuesClass {
    private static final AtomicInteger valGen = new AtomicInteger(1);
    private static Set<String> fieldsInEquals;

    static {
        fieldsInEquals = new HashSet<String>();
        fieldsInEquals.add("testVal");
    }

    private long testVal;

    public EqualsReturningNotEqualsDefaultValuesClass() {
        this.testVal = valGen.incrementAndGet();
    }

    public static Set<String> getFieldsInEqualsCode() {
        return fieldsInEquals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EqualsReturningNotEqualsDefaultValuesClass)) return false;

        EqualsReturningNotEqualsDefaultValuesClass that = (EqualsReturningNotEqualsDefaultValuesClass) o;

        return testVal == that.testVal;

    }

    @Override
    public int hashCode() {
        return (int) (testVal ^ (testVal >>> 32));
    }
}
