package net.digitaltsunami.pojot.property;

public class IntPrimitiveVal extends IntegerVal {
    private int defaultValue;
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
}
