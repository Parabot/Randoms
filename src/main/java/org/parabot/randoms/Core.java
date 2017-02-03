package org.parabot.randoms;

import org.parabot.core.Context;
import org.parabot.environment.randoms.Random;
import org.parabot.randoms.dreamscape.SerialBanAvoider;
import org.parabot.randoms.elkoy.AntiDetector;
import org.parabot.randoms.elkoy.LogoutDisabler;
import org.parabot.randoms.elkoy.QuestionSolver;
import org.parabot.randoms.elkoy.MouseOnScreen;
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
        randoms.add(new BanFile());

        // Dreamscape
        randoms.add(new SerialBanAvoider());

        // Elkoy
        randoms.add(new QuestionSolver());
        randoms.add(new LogoutDisabler());
        randoms.add(new MouseOnScreen());
        randoms.add(new AntiDetector());

        org.parabot.core.Core.verbose("Possible randoms:");
        for (Random random : randoms) {
            if (random.getServer().equalsIgnoreCase(server)) {
                org.parabot.core.Core.verbose("-> " + random.getName());
                Context.getInstance().getRandomHandler().addRandom(random);
            }
        }
    }
}
