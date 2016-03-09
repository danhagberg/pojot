package net.digitaltsunami.pojot.property;

import net.digitaltsunami.pojot.TestAidException;

/**
 * Enum value property provider. This provider differs from the others in that it
 * may not be able to provide alternate values as there may be a single enum constant.
 * In those cases, it will return the same value for all queries except for default,
 * which will return null.
 */
public class EnumVal implements PropertyValue {
    Class<? extends Enum> enumClazz;
    private String type;
    private int numberOfConstants;

    public EnumVal(String type) {
        this.type = type;
        try {
            this.enumClazz = (Class<? extends Enum>) Class.forName(type);
            numberOfConstants = this.enumClazz.getEnumConstants().length;
        } catch (ClassNotFoundException e) {
            throw new TestAidException("Cannot generate test for type.", e);
        }
    }

    /**
     * Return a value that matches the property type.
     *
     * @return non null value of the the given type.
     */
    @Override
    public Object getValue() {
        return enumClazz.getEnumConstants()[0];
    }

    /**
     * Return a small value that matches the property type.
     * If the type is a number, this will be the smallest number for the type.
     * If a string, then it will be an empty string.
     *
     * @return non null value of the the given type.
     */
    @Override
    public Object getSmallValue() {
        int index = numberOfConstants > 1 ? 1 : 0;
        return enumClazz.getEnumConstants()[index];
    }

    /**
     * Return a large value that matches the property type.
     * If the type is a number, this will be the largest number for the type.
     * If a string, then it will be a large string.
     *
     * @return non null value of the the given type.
     */
    @Override
    public Object getLargeValue() {
        int index = numberOfConstants > 1 ? 1 : 0;
        return enumClazz.getEnumConstants()[index];
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
