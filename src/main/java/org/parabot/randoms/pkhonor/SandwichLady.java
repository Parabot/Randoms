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

    private Npc lady;
    private final int id = 3117;

    @Override
    public boolean activate() {
        this.lady = getLady();
        return this.lady != null;
    }

    @Override
    public void execute() {
        if (this.lady != null) {
            lady.interact(Npcs.Option.TALK_TO);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return lady.distanceTo() < 2 || !lady.getInteractingCharacter().equals(Players.getMyPlayer());
                }
            }, 1500);
        }
    }

    private Npc getLady() {
        for (Npc lady : Npcs.getNearest(id)) {
            if (lady != null && lady.getDef() != null && lady.getInteractingCharacter().equals(Players.getMyPlayer())) {
                return lady;
            }
        }
        return null;
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
