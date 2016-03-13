package net.digitaltsunami.pojot.property;


import java.time.LocalDate;

public class LocalDateVal implements PropertyValue {
    @Override
    public LocalDate getValue() {
        return LocalDate.now();
    }

    @Override
    public LocalDate getSmallValue() {
        return LocalDate.MIN;
    }

    @Override
    public LocalDate getLargeValue() {
        return LocalDate.MAX;
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
