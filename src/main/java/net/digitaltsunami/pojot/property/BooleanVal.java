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
        return Boolean.TRUE;
    }

    /**
     * Return a null value for the type. Primitive types should throw an UnsupportedOperationException
     *
     * @return null unconditionally
     * @throws UnsupportedOperationException
     */
    @Override
    public Object getDefaultValue() {
        return null;
    }


}
