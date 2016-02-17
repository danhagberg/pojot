package net.digitaltsunami.pojot.property;

/**
 * Created by dhagberg on 1/31/16.
 */
public class DoublePrimitiveVal extends DoubleVal {
    private double defaultValue;
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
}
