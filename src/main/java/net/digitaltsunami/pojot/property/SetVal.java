package net.digitaltsunami.pojot.property;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * Created by dhagberg on 3/5/16.
 */
public class SetVal implements PropertyValue {
    /**
     * Return a value that matches the property type.
     *
     * @return non null value of the the given type.
     */
    @Override
    public Object getValue() {
        return Stream.of(1,2,3).collect(toSet());
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
        return Stream.of().collect(toSet());
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
        return IntStream.range(1,100).boxed().collect(toSet());
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
