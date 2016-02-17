package net.digitaltsunami.pojot.property;
public class LongVal implements PropertyValue {
    @Override
    public Long getValue() {
        return 42L;
    }

    @Override
    public Long getSmallValue() {
        return Long.MIN_VALUE;
    }

    @Override
    public Long getLargeValue() {
        return Long.MAX_VALUE;
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
