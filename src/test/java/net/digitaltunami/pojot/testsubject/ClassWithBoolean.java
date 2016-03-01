package net.digitaltunami.pojot.testsubject;


/**
 * Created by dhagberg on 2/29/16.
 */
public class ClassWithBoolean {
    private boolean booleanPrimitive;
    private Boolean booleanObject;
    private String name;

    public boolean isBooleanPrimitive() {
        return booleanPrimitive;
    }

    public void setBooleanPrimitive(boolean booleanPrimitive) {
        this.booleanPrimitive = booleanPrimitive;
    }

    public Boolean getBooleanObject() {
        return booleanObject;
    }

    public void setBooleanObject(Boolean booleanObject) {
        this.booleanObject = booleanObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassWithBoolean that = (ClassWithBoolean) o;

        if (booleanPrimitive != that.booleanPrimitive) return false;
        if (booleanObject != null ? !booleanObject.equals(that.booleanObject) : that.booleanObject != null)
            return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = (booleanPrimitive ? 1 : 0);
        result = 31 * result + (booleanObject != null ? booleanObject.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
