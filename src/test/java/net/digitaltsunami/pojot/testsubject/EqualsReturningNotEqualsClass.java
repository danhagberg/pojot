package net.digitaltsunami.pojot.testsubject;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class EqualsReturningNotEqualsClass {
    private static final AtomicInteger valGen = new AtomicInteger(1);
    private static Set<String> fieldsInEquals;

    static {
        fieldsInEquals = new HashSet<String>();
        fieldsInEquals.add("testVal");
    }

    private long testVal;

    public static Set<String> getFieldsInEqualsCode() {
        return fieldsInEquals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EqualsReturningNotEqualsClass)) return false;

        EqualsReturningNotEqualsClass that = (EqualsReturningNotEqualsClass) o;

        return testVal == that.testVal;

    }

    @Override
    public int hashCode() {
        return (int) (testVal ^ (testVal >>> 32));
    }

    public long getTestVal() {
        return this.testVal;
    }
    public void setTestVal(long newVal) {
        this.testVal = valGen.incrementAndGet();
    }
}
