package net.digitaltsunami.pojot.property;
public class LongVal implements PropertyValue {
    @Override
    public Long getValue() {
        return Long.valueOf(ByteNumberGenerator.getNextNumber());
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
     * Return a null value for the type.
     *
     * @return null unconditionally
     */
    @Override
    public Object getDefaultValue() {
        return null;
    }

}
