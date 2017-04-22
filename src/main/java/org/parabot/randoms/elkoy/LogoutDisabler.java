package org.parabot.randoms.elkoy;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.randoms.RandomType;
import org.rev317.min.api.methods.Game;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author EmmaStone
 */
public class LogoutDisabler implements org.parabot.environment.randoms.Random {

    private final int[]  KEYS   = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT };
    private final Random random = new Random();
    private       long   ms     = System.currentTimeMillis();

    @Override
    public boolean activate() {
        return Game.isLoggedIn() && (System.currentTimeMillis() - ms) / 1000 > random.nextInt((50 - 30) + 1) + 30;
    }

    @Override
    public void execute() {
        int keyCode = KEYS[random.nextInt(KEYS.length)];
        Keyboard.getInstance().pressKey(keyCode);
        Time.sleep(random.nextInt((20 - 5) + 1) + 5);
        Keyboard.getInstance().releaseKey(keyCode);

        ms = System.currentTimeMillis();
    }

    @Override
    public String getName() {
        return "Logout Disabler";
    }

    @Override
    public String getServer() {
        return "Elkoy";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.SCRIPT;
    }
}
