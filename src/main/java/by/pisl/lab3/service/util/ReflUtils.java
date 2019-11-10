package by.pisl.lab3.service.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * @version 1.0.0
 */
public class ReflUtils {
    public static PropertyDescriptor getPropertyDescriptor(Class clazz, String property) throws IntrospectionException {
        return Arrays.stream(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
                .filter((pd) -> "id".equals(pd.getName()))
                .findFirst().get();
    }
}
