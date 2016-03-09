package net.digitaltsunami.pojot.property;

import net.digitaltsunami.pojot.TestAidException;

/**
 * Created by dhagberg on 3/9/16.
 */
public class ValueProviderFactory {
    /**
     * Create and return a PropertyValue for the provided type.
     * @param type class type of property.
     * @return PropertyValue for type used to provide values for testing.
     * @throws TestAidException if the type could not be created.
     */
    public static PropertyValue getProvider(String type) {
        try {
            Class clazz = Class.forName(type);
            if (clazz.isEnum()) {
                return new EnumVal(type);
            }
            else {
                return new GenericProperty(type);
            }
        } catch (ClassNotFoundException e) {
            throw new TestAidException("Cannot generate test for type.", e);
        }

    }
}
