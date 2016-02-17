package net.digitaltsunami.pojot.property;

/**
 * Created by dhagberg on 1/31/16.
 */
public class BooleanPrimitiveVal extends BooleanVal {
    private boolean defaultValue;
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
}
