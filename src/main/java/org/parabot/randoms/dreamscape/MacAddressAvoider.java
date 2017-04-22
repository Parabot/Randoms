package org.parabot.randoms.dreamscape;

import org.parabot.core.Context;
import org.parabot.environment.randoms.RandomType;
import org.parabot.randoms.utils.Reflection;

/**
 * @author EmmaStone
 */
public class MacAddressAvoider implements org.parabot.environment.randoms.Random {

    private static final String MAC_ADDRESS_FIELD = "MAC_ADDRESS";
    private static final String MAC_ADDRESS_VALUE = "empty_mac";

    private boolean done;

    @Override
    public boolean activate() {
        return !done;
    }

    @Override
    public void execute() {
        Reflection.workAroundStaticValues(Context.getInstance().getClient().getClass(), MAC_ADDRESS_FIELD, MAC_ADDRESS_VALUE);
        done = true;
    }

    @Override
    public String getName() {
        return "Mac address avoider";
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
