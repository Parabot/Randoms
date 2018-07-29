package org.parabot.randoms;

import org.parabot.api.output.Logger;
import org.parabot.core.Context;
import org.parabot.environment.randoms.Random;
import org.parabot.randoms.dreamscape.FileBan;
import org.parabot.randoms.elkoy.AntiDetector;
import org.parabot.randoms.elkoy.LogoutDisabler;
import org.parabot.randoms.elkoy.MouseOnScreen;
import org.parabot.randoms.elkoy.QuestionSolver;
import org.parabot.randoms.locopk.MacAddressFix;
import org.parabot.randoms.pkhonor.*;
import org.parabot.randoms.soulplay.RandomUUID;

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
        randoms.add(new CombatStone());

        // Elkoy
        randoms.add(new QuestionSolver());
        randoms.add(new LogoutDisabler());
        randoms.add(new MouseOnScreen());
        randoms.add(new AntiDetector());

        // LocoPK
        randoms.add(new MacAddressFix());

        // Dreamscape
        randoms.add(new FileBan());

        // SoulPlay
        randoms.add(new RandomUUID());

        StringBuilder sb = new StringBuilder();
        sb.append("Possible randoms:\n");
        int randomCount = 0;
        for (Random random : randoms) {
            if (random.getServer().equalsIgnoreCase(server)) {
                sb.append("\t-> ").append(random.getName());
                Context.getInstance().getRandomHandler().addRandom(random);
                randomCount++;
            }
        }
        final String toPrint = sb.append("\n").toString();
        if (toPrint.contains("->")) {
            org.parabot.core.Core.verbose(toPrint);
        }
        Logger.info("org.parabot.randoms.Core", "A total of "+randomCount+" randoms have been loaded for server: "+server);
    }
}
