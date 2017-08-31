package org.parabot.randoms.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author EmmaStone
 */
public class Reflection {

    public static void workAroundStaticValues(Class clazz, String fieldName, Object newValue) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            Field modifiers = field.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, newValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
