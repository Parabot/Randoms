package org.parabot.randoms.dreamscape;

import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;

import java.io.File;

/**
 * @author EmmaStone
 */
public class FileBan implements Random {

    private boolean checked = false;

    @Override
    public boolean activate() {
        if (!checked) {
            if (doesFileExist()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void execute() {
        File file = new File(System.getProperty("user.home") + "/jagex_rs.txt");
        file.delete();
    }

    @Override
    public String getName() {
        return "File Ban";
    }

    @Override
    public String getServer() {
        return "dreamscape";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.ON_SERVER_START;
    }

    private boolean doesFileExist() {
        checked = true;

        File file = new File(System.getProperty("user.home") + "/jagex_rs.txt");
        return file.exists() && file.exists();
    }
}
