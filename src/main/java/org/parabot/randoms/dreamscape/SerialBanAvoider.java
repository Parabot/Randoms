package org.parabot.randoms.dreamscape;

import org.parabot.core.Context;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.parabot.randoms.utils.Reflection;

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
        Reflection.workAroundStaticValues(Context.getInstance().getClient().getClass(), serialAddressField, serialAddressValue);
        done = true;
    }

    @Override
    public String getName() {
        return "Serial ban avoider";
    }

    @Override
    public String getServer() {
        return "dreamscape";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.ON_SERVER_START;
    }
}
