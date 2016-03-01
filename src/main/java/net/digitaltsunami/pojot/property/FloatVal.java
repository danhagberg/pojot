package net.digitaltsunami.pojot.property;

public class FloatVal implements PropertyValue {

    @Override
    public Object getValue() {
        return Float.valueOf(ByteNumberGenerator.getNextNumber() + .1111F);
    }

    @Override
    public Object getSmallValue() {
        return Float.MIN_VALUE;
    }

    @Override
    public Object getLargeValue() {
        return Float.MAX_VALUE;
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
