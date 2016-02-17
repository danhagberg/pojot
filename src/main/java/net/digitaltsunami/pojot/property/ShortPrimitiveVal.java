package net.digitaltsunami.pojot.property;

/**
 * Created by dhagberg on 1/31/16.
 */
public class ShortPrimitiveVal extends ShortVal {
    private short defaultValue;
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
}
