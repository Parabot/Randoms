package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * @author Fryslan
 */

public class Jail implements Random {
    private Npc jailer;
  
    private final int[] rocks = {7456, 7455, 7488};
    private final int[] pickAxes = {1266, 1268, 1270, 1272, 1274, 1276, 14605, 14608};

    @Override
    public boolean activate() {
        try {
            if (getJailer() != null) {
                this.jailer = getJailer();
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void execute() {
        try {

            SceneObject rock = getRock();

            //Check if we got an Pickaxe
            if (Inventory.getCount(pickAxes) > 0) {

                //Check if we can min the ores
                if (!Inventory.isFull()) {
                    if (rock != null) {
                        if (Players.getMyPlayer().getAnimation() == -1) {
                            rock.interact(SceneObjects.Option.MINE);
                            Time.sleep(new SleepCondition() {
                                @Override
                                public boolean isValid() {
                                    return Players.getMyPlayer().getAnimation() != -1;
                                }
                            }, 2000);
                        }
                    }

                    //Inventory is full depositting ores
                } else {
                    jailer.interact(Npcs.Option.TALK_TO);

                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return !Inventory.isFull();
                        }
                    }, 5000);
                    Time.sleep(2500);
                }

                //getting Pickaxe
            } else {
                jailer.interact(Npcs.Option.TALK_TO);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount(1266) > 0;
                    }
                }, 5000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Npc getJailer() {
        for (Npc jailer : Npcs.getNearest(300)) {
            if (jailer != null && jailer.getDef() != null) {
                return jailer;
            }
        }

        return null;
    }

    private SceneObject getRock() {
        for (SceneObject rock : SceneObjects.getNearest(rocks)) {
            if (rock != null) {
                return rock;
            }
        }

        return null;
    }

    @Override
    public String getName() {
        return "Jail solver";
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
