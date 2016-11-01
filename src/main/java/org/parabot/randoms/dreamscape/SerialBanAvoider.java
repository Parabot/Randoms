package org.parabot.randoms.dreamscape;

import org.parabot.core.Context;
import org.parabot.core.Core;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefField;
import org.parabot.environment.scripts.randoms.Random;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author JKetelaar
 */
public class SerialBanAvoider implements Random {

    private static final String serialAddressField = "SERIAL_ADDRESS";
    private static final String serialAddressValue = "empty_or_unknown";

    private boolean done;

    @Override
    public boolean activate() {
        return !done;
    }

    @Override
    public void execute() {
        try {
            workAroundStaticValues(Context.getInstance().getClient().getClass(), serialAddressField, serialAddressValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        done = true;
    }

    private void workAroundStaticValues(Class clazz, String fieldName, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiers = field.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    @Override
    public String getName() {
        return "Serial ban avoider";
    }

    @Override
    public String getServer() {
        return "dreamscape";
    }
}
