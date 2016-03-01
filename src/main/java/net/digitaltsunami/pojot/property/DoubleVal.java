package net.digitaltsunami.pojot.property;

public class DoubleVal implements PropertyValue {

    @Override
    public Object getValue() {
        return Double.valueOf(ByteNumberGenerator.getNextNumber() + .1111);
    }

    @Override
    public Object getSmallValue() {
        return Double.MIN_VALUE;
    }

    @Override
    public Object getLargeValue() {
        return Double.MAX_VALUE;
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
