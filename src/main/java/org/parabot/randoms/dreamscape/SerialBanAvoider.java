package org.parabot.randoms.dreamscape;

import org.parabot.core.Context;
import org.parabot.core.Core;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefField;
import org.parabot.environment.scripts.randoms.Random;

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
        RefClass client = new RefClass(Context.getInstance().getClient());
        RefField serial = client.getField(serialAddressField);
        if (serial != null) {
            serial.set(serialAddressValue);
        } else {
            Core.verbose(String.format("Oh oh... Couldn't find field: %s", serialAddressField));
        }

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
}
