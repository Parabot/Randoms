package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * @author Fryslan
 */
public class BobsIsland implements Random {

    private static final Tile center   = new Tile(2525, 4777);
    private static final int  portalId = 8987;

    @Override
    public boolean activate() {
        return center.distanceTo() < 25;
    }

    @Override
    public void execute() {
        SceneObject[] portals = SceneObjects.getNearest(portalId);

        for (final SceneObject portal : portals) {
            if (portal != null) {
                portal.interact(SceneObjects.Option.FIRST);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return portal.distanceTo() < 2;
                    }
                }, 10000);
                Time.sleep(1000);

                if (center.distanceTo() > 24) {
                    break;
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bobs Island Solver";
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
