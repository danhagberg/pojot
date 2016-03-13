package net.digitaltsunami.pojot.property;


import java.time.LocalDateTime;

public class LocalDateTimeVal implements PropertyValue {
    @Override
    public LocalDateTime getValue() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime getSmallValue() {
        return LocalDateTime.MIN;
    }

    @Override
    public LocalDateTime getLargeValue() {
        return LocalDateTime.MAX;
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
