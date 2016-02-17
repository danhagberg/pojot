package net.digitaltsunami.pojot.property;

public class CharVal implements PropertyValue {
    @Override
    public Object getValue() {
        return Character.valueOf('a');
    }

    @Override
    public Object getSmallValue() {
        return Character.MIN_VALUE;
    }

    @Override
    public Object getLargeValue() {
        return Character.MAX_VALUE;
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
