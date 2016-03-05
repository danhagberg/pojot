package net.digitaltsunami.pojot.property;

import net.digitaltsunami.pojot.TestAidException;

/**
 * Generic property for all classes not already represented by the basic types.  Will create an empty instance used
 * to populate property values.
 * <p>
 * The provided class must have a zero argument constructor so that a default instance can be created for testing.
 * Created by dhagberg on 1/31/16.
 */
public class GenericProperty implements PropertyValue {
    Object property;
    private String type;

    public GenericProperty(String type) {
        this.type = type;
        property = createInstance();
    }

    private Object createInstance() {
        try {
            Class clazz = Class.forName(type);
            return clazz.newInstance();
        } catch ( ClassNotFoundException
                | InstantiationException
                | IllegalAccessException e) {
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
        return createInstance();
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
        return createInstance();
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
        return property;
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
