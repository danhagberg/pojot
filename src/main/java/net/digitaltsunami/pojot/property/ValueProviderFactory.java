package net.digitaltsunami.pojot.property;

import net.digitaltsunami.pojot.TestAidException;

/**
 * Created by dhagberg on 3/9/16.
 */
public class ValueProviderFactory {
    /**
     * Create and return a PropertyValue for the provided type.
     *
     * @param type class type of property.
     * @return PropertyValue for type used to provide values for testing.
     * @throws TestAidException if the type could not be created.
     */
    public static PropertyValue getProvider(String type) {
        try {
            Class clazz = getClassForName(type);
            if (clazz.isEnum()) {
                return new EnumVal(type);
            } else {
                return new GenericProperty(type);
            }
        } catch (ClassNotFoundException e) {
            throw new TestAidException("Cannot generate test for type.", e);
        }
    }

    /**
     * Wrapper around Class.forName to allow for inner classes and enums that have
     * require a $ between the enclosing class name and the target inner class name.
     * The name is returned with a period (.) separating these elements.
     *
     * @param type full name of class to retrieve
     * @return Instance of class for type
     * @throws ClassNotFoundException if the class could not be found after trying both name types.
     */
    public static Class getClassForName(String type) throws ClassNotFoundException {
        try {
            return Class.forName(type);
        } catch (ClassNotFoundException origException) {
            int index = type.lastIndexOf('.');
            String newType = new StringBuilder(type).replace(index, index+1, "$").toString();
            return Class.forName(newType);
        }

    }
}
