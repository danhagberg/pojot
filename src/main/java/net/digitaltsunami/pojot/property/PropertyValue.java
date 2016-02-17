package net.digitaltsunami.pojot.property;

/**
 * Created by dhagberg on 1/31/16.
 */
public interface PropertyValue {
    /**
     * Return a value that matches the property type.
     *
     * @return non null value of the the given type.
     */
    Object getValue();

    /**
     * Return a small value that matches the property type.
     * If the type is a number, this will be the smallest number for the type.
     * If a string, then it will be an empty string.
     *
     * @return non null value of the the given type.
     */
    Object getSmallValue();

    /**
     * Return a large value that matches the property type.
     * If the type is a number, this will be the largest number for the type.
     * If a string, then it will be a large string.
     *
     * @return non null value of the the given type.
     */
    Object getLargeValue();

    /**
     * Return the default value for the type.  For example, a default Integer is null, whereas a default int is 0.
     * @return default value for type.
     */
    Object getDefaultValue();
}
