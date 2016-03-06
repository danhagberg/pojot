package net.digitaltsunami.pojot.property;

import java.util.*;

/**
 * Set of values to place into a property for testing.  Each is identified by type Integer, int, String, etc.
 * Created by dhagberg on 1/17/16.
 */
public enum PropertyValues {
    BooleanPropPrimitiveVal("boolean", new BooleanPrimitiveVal()),
    BooleanPropVal(Boolean.class.getCanonicalName(), new BooleanVal()),
    BytePropPrimitiveVal("byte", new BytePrimitiveVal()),
    BytePropVal(Byte.class.getCanonicalName(), new ByteVal()),
    CharPropPrimitiveVal("char", new CharPrimitiveVal()),
    CharPropVal(Character.class.getCanonicalName(), new CharVal()),
    DatePropVal(java.util.Date.class.getCanonicalName(), new DateVal()),
    DoublePrimitivePropVal("double", new DoublePrimitiveVal()) ,
    DoublePropVal(Double.class.getCanonicalName(), new DoubleVal()) ,
    FloatPropPrimitiveVal("float", new FloatPrimitiveVal()) ,
    FloatPropVal(Float.class.getCanonicalName(), new FloatVal()) ,
    IntPrimitivePropVal("int", new IntPrimitiveVal()),
    IntegerPropVal(Integer.class.getCanonicalName(), new IntegerVal()),
    LongPropPrimitiveVal("long", new LongPrimitiveVal()),
    LongPropVal(Long.class.getCanonicalName(), new LongVal()),
    ShortPropPrimitiveVal("short", new ShortPrimitiveVal()),
    ShortPropVal(Short.class.getCanonicalName(), new ShortVal()),
    StringPropVal(String.class.getCanonicalName(), new StringVal()),
    SetsPropVal(Set.class.getCanonicalName(), new SetVal()),
    MapPropVal(Map.class.getCanonicalName(), new MapVal()),
    ListPropVal(List.class.getCanonicalName(), new ListVal())
   ;


    /**
     * Map of data types to provider.
     */
    private static final Map<String, PropertyValue> types = new HashMap<>();

    private final String type;
    private final PropertyValue provider;

    /**
     * Return an instance of PropertyValue based on the provided type.
     * @param type canonical name of the type being tested.
     * @return either an instance of PropertyValue specialized for the type or a generic implementation.
     */
    public static PropertyValue fromString(String type) {
        PropertyValue provider = types.get(type);
        return (provider == null) ? new GenericProperty(type) : provider;
    }


    /**
     * Construct and save the type and provider for future lookups.
     * @param type String representing canonical name of data type.
     * @param provider PropertyValue instance that will provide data values.
     */
    PropertyValues(String type, PropertyValue provider) {
        this.type = type;
        this.provider = provider;
    }

    /*
    Create a map of all types to the value provider.
     */
    static {
       for (PropertyValues pv : EnumSet.allOf(PropertyValues.class)) {
           types.put(pv.type, pv.provider);
       }
    }
}
