package net.digitaltsunami.pojot.property;
public class IntegerVal implements PropertyValue {


    @Override
    public Object getValue() {
        return Integer.valueOf(10);
    }

    @Override
    public Object getSmallValue() {
        return Integer.MIN_VALUE;
    }

    @Override
    public Object getLargeValue() {
        return Integer.MAX_VALUE;
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
