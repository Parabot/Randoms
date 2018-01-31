package org.parabot.randoms.soulplay;

import org.parabot.core.Context;
import org.parabot.core.reflect.RefClass;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;

/**
 * @author SCoutinho
 */
public class RandomUUID implements Random {
    private boolean changed = false;
    private RefClass clientClass;
    private static final String fieldName = "Ae";
    private long number;

    public RandomUUID() {
        this.clientClass = (RefClass) Context.getInstance().getClient();
        this.number = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;
    }

    @Override
    public boolean activate() {
        return !this.changed;
    }

    @Override
    public void execute() {
        this.clientClass.getField(fieldName).setString("LOFTo be filled by O.E.M.2017" + this.number + ".000000+030LOF3");
        this.changed = true;
    }

    @Override
    public String getName() {
        return "Random UUID";
    }

    @Override
    public String getServer() {
        return "soulplay";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.ON_SERVER_START;
    }
}
