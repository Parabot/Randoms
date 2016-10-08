package org.parabot.randoms.pkhonor;

import org.parabot.environment.scripts.randoms.Random;

import java.io.File;

/**
 * Created by Fryslan.
 */
public class BanFile implements Random {

    private static final File[] locations = {new File("C:/PkHonor/",".jagex_cache_58993.dat"),new File(System.getProperty("user.home"), ".app_info_3541"),new File(System.getProperty("user.home"), "AppData/Applications")};
    private boolean checked = false;

    @Override
    public boolean activate() {
        return !checked && filePresent();
    }

    @Override
    public void execute() {
        for(File banfile : locations){
            if(banfile.exists()){
               banfile.delete();
            }
        }

        checked = true;
    }

    @Override
    public String getName() {
        return "BanFile Handler";
    }

    @Override
    public String getServer() {
        return "pkhonor";
    }

    private boolean filePresent() {
        for(File banfile : locations){
            if(banfile.exists()){
                return true;
            }
        }
        checked = true;
        return false;
    }
}
