package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

/**
 * @author Fryslan
 */
public class MysteriousOldMan implements Random {

    private Npc man;
    private final int id = 410;

    @Override
    public boolean activate() {
        this.man = getMan();
        return man != null;
    }

    @Override
    public void execute() {
        if (this.man != null) {
            man.interact(Npcs.Option.TALK_TO);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return man.distanceTo() > 0 || !man.getInteractingCharacter().equals(Players.getMyPlayer());
                }
            }, 1500);
        }
    }

    private Npc getMan() {
        for (Npc man : Npcs.getNearest(id)) {
            if (man != null && man.getDef() != null && man.getInteractingCharacter().equals(Players.getMyPlayer())) {
                return man;
            }
        }

        return null;
    }

    @Override
    public String getName() {
        return "Mysterious Old Man Solver";
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
