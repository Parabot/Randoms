package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

/**
 * Created with IntelliJ IDEA. User: Piet Jetse Date: 12-9-2014 Time: 17:19
 */
public class MysteriousOldMan implements Random {

    Npc man;

    @Override
    public boolean activate() {
        for (Npc npc : Npcs.getNearest(410)) {
            if (npc != null && npc.getInteractingCharacter().equals(Players.getMyPlayer())) {
                man = npc;
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        if (man != null && man.getInteractingCharacter().equals(Players.getMyPlayer())) {
            man.interact(0);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !man.getInteractingCharacter().equals(Players.getMyPlayer());
                }
            }, 1500);
        }
    }

    @Override
    public String getName() {
        return "Mysterious Old Man Solver";
    }

    @Override
    public String getServer() {
        return "pkhonor";
    }
}
