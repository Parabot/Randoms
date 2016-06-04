package org.parabot.randoms;

import org.parabot.core.Context;
import org.parabot.environment.scripts.randoms.Random;
import org.parabot.randoms.ikov.LogoutDisabler;
import org.parabot.randoms.ikov.PacketFail;
import org.parabot.randoms.ikov.Login;
import org.parabot.randoms.ikov.QuestionSolver;
import org.parabot.randoms.pkhonor.*;

import java.util.ArrayList;

/**
 * @author JKetelaar
 */
public class Core {
    private ArrayList<Random> randoms = new ArrayList<Random>();

    public void init(String server) {
        randoms.add(new Jail());
        randoms.add(new TriangleSandwich());
        randoms.add(new SandwichLady());
        randoms.add(new MysteriousOldMan());
        randoms.add(new QuestionSolver());
        randoms.add(new BobsIsland());
        randoms.add(new LogoutDisabler());
        randoms.add(new Login());
        randoms.add(new PacketFail());

        org.parabot.core.Core.verbose("Possible randoms:");
        for (Random random : randoms) {
            if (random.getServer().toLowerCase().equalsIgnoreCase(server.toLowerCase())) {
                org.parabot.core.Core.verbose("-> " + random.getName());
                Context.getInstance().getRandomHandler().addRandom(random);
            }
        }
    }
}
