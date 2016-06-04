package org.parabot.randoms.ikov;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.api.methods.Game;

import java.awt.event.KeyEvent;

/**
 * @author JKetelaar
 */
public class LogoutDisabler implements Random {

    private final int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
    private final java.util.Random random = new java.util.Random();

    private long ms = System.currentTimeMillis();

    @Override
    public boolean activate() {
        return Game.isLoggedIn() && (System.currentTimeMillis() - ms) / 1000 > random.nextInt((50 - 30) + 1) + 30;
    }

    @Override
    public void execute() {
        int keyCode = keys[random.nextInt(keys.length)];
        Keyboard.getInstance().pressKey(keyCode);
        Time.sleep(random.nextInt((20 - 5) + 1) + 5);
        Keyboard.getInstance().releaseKey(keyCode);

        ms = System.currentTimeMillis();
    }

    @Override
    public String getName() {
        return "Logout disabler";
    }

    @Override
    public String getServer() {
        return "Ikov";
    }
}
