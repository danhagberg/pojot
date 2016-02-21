package net.digitaltsunami.pojot.property;

import java.util.Date;

public class DateVal implements PropertyValue {
    @Override
    public Date getValue() {
        return new Date();
    }

    @Override
    public Date getSmallValue() {
        return new Date(Long.MIN_VALUE);
    }

    @Override
    public Date getLargeValue() {
        return new Date(Long.MAX_VALUE);
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
