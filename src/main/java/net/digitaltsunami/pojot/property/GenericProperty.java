package net.digitaltsunami.pojot.property;

import net.digitaltsunami.pojot.TestAidException;

/**
 * Created by dhagberg on 1/31/16.
 */
public class GenericProperty implements PropertyValue {
    Object property;
    public GenericProperty(String type) {
        try {
            Class clazz = Class.forName(type);
            property = clazz.newInstance();
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
        return property;
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
        return property;
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
