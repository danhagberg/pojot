package net.digitaltsunami.pojot.property;

/**
 * Created by dhagberg on 1/31/16.
 */
public class BytePrimitiveVal extends ByteVal {
    private byte defaultValue;
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
}
