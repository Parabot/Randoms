package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

/**
 * @author Lord
 */
public class CombatStone implements Random {

    private final int id = 17025;
    private Npc combat;

    @Override
    public boolean activate() {
        return (this.combat = getCombat()) != null;
    }

    @Override
    public void execute() {
        if (this.combat != null) {
            combat.interact(Npcs.Option.TALK_TO);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return combat.distanceTo() > 0 || !combat.getInteractingCharacter().equals(Players.getMyPlayer());
                }
            }, 1500);
        }
    }

    private Npc getCombat() {
        for (Npc combat : Npcs.getNearest(id)) {
            if (combat != null && combat.getInteractingCharacter().equals(Players.getMyPlayer())) {
                return combat;
            }
        }

        return null;
    }

    @Override
    public String getName() {
        return "CombatStone Solver";
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
