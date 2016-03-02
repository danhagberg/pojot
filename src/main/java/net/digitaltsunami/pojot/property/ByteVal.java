package net.digitaltsunami.pojot.property;

public class ByteVal implements PropertyValue {
    @Override
    public Object getValue() {
        return Byte.valueOf(ByteNumberGenerator.getNextNumber());
    }

    @Override
    public Object getSmallValue() {
        return Byte.MIN_VALUE;
    }

    @Override
    public Object getLargeValue() {
        return Byte.MAX_VALUE;
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
