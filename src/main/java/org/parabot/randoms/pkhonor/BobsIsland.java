package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Area;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 2-1-2015
 * Time: 13:19
 */
public class BobsIsland implements Random {

    private static final Tile CENTER = new Tile(2525,4777);
    private static final int PORTAL_ID = 8987;

    public BobsIsland() {
    }

    @Override
    public boolean activate() {
        return CENTER.distanceTo() < 25;
    }

    @Override
    public void execute() {
        SceneObject[] portals = SceneObjects.getNearest(PORTAL_ID);

        for(final SceneObject portal : portals){
            if(portal != null){
                portal.interact(SceneObjects.Option.FIRST);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return portal.distanceTo() < 2;
                    }
                }, 10000);
                Time.sleep(1000);

                if(CENTER.distanceTo() > 24){
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
}
