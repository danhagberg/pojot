package net.digitaltsunami.pojot.testsubject;

/**
 * Created by dhagberg on 5/7/16.
 */
public abstract class AbstractBaseClass {
    private String testBaseClassString;
    private int testBaseClassInt;
    public static boolean getterInvoked;
    public static boolean setterInvoked;

    public abstract void testMethod();

    public String getTestBaseClassString() {
        getterInvoked = true;
        return testBaseClassString;
    }

    public void setTestBaseClassString(String testBaseClassString) {
        setterInvoked = true;
        this.testBaseClassString = testBaseClassString;
    }

    public int getTestBaseClassInt() {
        getterInvoked = true;
        return testBaseClassInt;
    }

    public void setTestBaseClassInt(int testBaseClassInt) {
        setterInvoked = true;
        this.testBaseClassInt = testBaseClassInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBaseClass that = (AbstractBaseClass) o;

        if (getTestBaseClassInt() != that.getTestBaseClassInt()) return false;
        return getTestBaseClassString() != null ? getTestBaseClassString().equals(that.getTestBaseClassString()) : that.getTestBaseClassString() == null;

    }

    @Override
    public int hashCode() {
        int result = getTestBaseClassString() != null ? getTestBaseClassString().hashCode() : 0;
        result = 31 * result + getTestBaseClassInt();
        return result;
    }
}
