package org.parabot.randoms.pkhonor;

import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;

import java.io.File;

/**
 * @author Fryslan
 */
public class BanFile implements Random {

    private static final File[]  locations = { new File("C:/PkHonor/", ".jagex_cache_58993.dat"), new File(System.getProperty("user.home"), ".app_info_3541"), new File(System.getProperty("user.home"), "AppData/Applications") };
    private              boolean checked   = false;

    @Override
    public boolean activate() {
        if (!checked) {
            if (filePresent()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void execute() {
        for (File banfile : locations) {
            if (banfile.exists()) {
                banfile.delete();
            }
        }
    }

    @Override
    public String getName() {
        return "BanFile Handler";
    }

    @Override
    public String getServer() {
        return "pkhonor";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.ON_SERVER_START;
    }

    private boolean filePresent() {
        checked = true;
        for (File banfile : locations) {
            if (banfile.exists()) {
                return true;
            }
        }

        return false;
    }
}
