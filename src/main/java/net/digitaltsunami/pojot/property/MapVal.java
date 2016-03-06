package net.digitaltsunami.pojot.property;

import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Used to test properties of type Map.  This includes only the Map interface and not concrete types
 * or sub-interfaces.
 */
public class MapVal implements PropertyValue {

    /**
     * Return a value that matches the property type.
     *
     * @return non null value of the the given type.
     */
    @Override
    public Object getValue() {
        return Stream.of("A", "B")
                .collect(toMap(identity(), String::toLowerCase));
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
        return Collections.EMPTY_MAP;
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
        return IntStream.range(1,100)
                .boxed()
                .collect(toMap(identity(), field -> field % 2));
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
