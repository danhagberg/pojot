package net.digitaltsunami.pojot.property;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dhagberg on 3/5/16.
 */
public class ListVal implements PropertyValue {
    /**
     * Return a value that matches the property type.
     *
     * @return non null value of the the given type.
     */
    @Override
    public Object getValue() {
        return Stream.of(1,2,3).collect(Collectors.toList());
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
        return Stream.of().collect(Collectors.toList());
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
        return Stream.of(1,2,3,4,5,6).collect(Collectors.toList());
    }

    /**
     * Return the default value for the type.  For example, a default Integer is null, whereas a default int is 0.
     *
     * @return default value for type.
     */
    @Override
    public Object getDefaultValue() {
        return null;
    }
}
