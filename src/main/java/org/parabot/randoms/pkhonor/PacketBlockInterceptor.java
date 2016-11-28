package org.parabot.randoms.pkhonor;

import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefField;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.rev317.min.Loader;

/**
 * @author EmmaStone
 */
public class PacketBlockInterceptor implements Random {

    @Override
    public boolean activate() {
        return !getField();
    }

    @Override
    public void execute() {
        setField();
    }

    private boolean getField() {
        return new RefClass(Loader.getClient()).getField("PG").asBoolean();
    }

    private void setField() {
        RefField refField = new RefClass(Loader.getClient()).getField("PG");
        refField.set(true);
    }

    @Override
    public String getName() {
        return "Packet Block Interceptor";
    }

    @Override
    public String getServer() {
        return "pkhonor";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.SCRIPT;
    }
}
