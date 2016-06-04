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

    private final int PORTAL = 8987;
    private ArrayList<SceneObject> portals;
    private final Area ISLAND = new Area(new Tile(2511, 4765), new Tile(2511, 4790), new Tile(2542, 4790), new Tile(2542, 4765));

    public BobsIsland() {
        portals = new ArrayList<>();
    }

    @Override
    public boolean activate() {
        return ISLAND.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {
        //Fill The ArrayList
        for (SceneObject portal : SceneObjects.getNearest(PORTAL)) {
            if (portal != null) {
                portals.add(portal);
            }
        }

        //Loop through the portals
        for (final SceneObject portal : portals) {
            if (portal != null) {
                portal.interact(0);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return portal.distanceTo() < 2;
                    }
                }, 7500);
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
