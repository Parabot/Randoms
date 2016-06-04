package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

/**
 * Created with IntelliJ IDEA. User: Piet Jetse Date: 12-9-2014 Time: 16:13
 */
public class SandwichLady implements Random {

    Npc lady;

    @Override
    public boolean activate() {
        for (Npc npc : Npcs.getNearest(3117)) {
            if (npc != null && npc.getInteractingCharacter().equals(Players.getMyPlayer())) {
                lady = npc;
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        if (lady != null && lady.getInteractingCharacter().equals(Players.getMyPlayer())) {
            lady.interact(0);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !lady.getInteractingCharacter().equals(Players.getMyPlayer());
                }
            }, 1500);
        }
    }

    @Override
    public String getName() {
        return "Sandwich Lady Solver";
    }

    @Override
    public String getServer() {
        return "pkhonor";
    }
}
