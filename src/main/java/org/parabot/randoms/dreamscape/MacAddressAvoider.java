package org.parabot.randoms.dreamscape;

import org.parabot.core.Context;
import org.parabot.core.network.NetworkInterface;
import org.parabot.environment.randoms.RandomType;
import org.parabot.randoms.utils.Reflection;

/**
 * @author EmmaStone
 */
public class MacAddressAvoider implements org.parabot.environment.randoms.Random {

    private boolean done;

    @Override
    public boolean activate() {
        return !done;
    }

    @Override
    public void execute() {
        byte[] mac = new byte[6];
        new java.util.Random().nextBytes(mac);
        Reflection.workAroundStaticValues(Context.getInstance().getClient().getClass(), "MAC_ADDRESS", NetworkInterface.formatMac(mac));
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
