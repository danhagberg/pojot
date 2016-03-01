package net.digitaltsunami.pojot.property;

public class BooleanVal implements PropertyValue {
    @Override
    public Object getValue() {
        return Boolean.TRUE;
    }

    @Override
    public Object getSmallValue() {
        return Boolean.FALSE;
    }

    @Override
    public Object getLargeValue() {
        return Boolean.FALSE;
    }

    /**
     * Return a null value for the type.
     *
     * @return null unconditionally
     */
    @Override
    public Object getDefaultValue() {
        return null;
    }


}
