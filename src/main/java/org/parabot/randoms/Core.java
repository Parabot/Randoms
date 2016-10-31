package org.parabot.randoms;

import org.parabot.core.Context;
import org.parabot.environment.scripts.randoms.Random;
import org.parabot.randoms.dreamscape.SerialBanAvoider;
import org.parabot.randoms.pkhonor.*;

import java.util.ArrayList;

/**
 * @author JKetelaar
 */
public class Core {
    private ArrayList<Random> randoms = new ArrayList<>();

    public void init(String server) {
        // PkHonor
        randoms.add(new Jail());
        randoms.add(new TriangleSandwich());
        randoms.add(new SandwichLady());
        randoms.add(new MysteriousOldMan());
        randoms.add(new BobsIsland());

        // Dreamscape
        randoms.add(new SerialBanAvoider());

        org.parabot.core.Core.verbose("Possible randoms:");
        for (Random random : randoms) {
            if (random.getServer().equalsIgnoreCase(server)) {
                org.parabot.core.Core.verbose("-> " + random.getName());
                Context.getInstance().getRandomHandler().addRandom(random);
            }
        }
    }
}
