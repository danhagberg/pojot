package net.digitaltsunami.pojot.property;
public class StringVal implements PropertyValue {
    /**
     * Test data for populating large string values.
     */
    private static String largeString;

    /*
    Create and populate largeString property.
     */
    static {
        StringBuilder sb = new StringBuilder("abcDEFGHIJKLMNOPQRSTUVWXYZ");
        for (int i = 0; i < 10; i++) {
            sb.append(sb.toString());
        }
        largeString = sb.toString();
    }


    @Override
    public Object getValue() {
        return "Test String Value";
    }

    @Override
    public Object getSmallValue() {
        return "";
    }

    @Override
    public Object getLargeValue() {
        return largeString;
    }

    /**
     * Return a null value for the type. Primitive types should throw an UnsupportedOperationException
     *
     * @return null unconditionally
     * @throws UnsupportedOperationException
     */
    @Override
    public Object getDefaultValue() {
        return null;
    }

}
