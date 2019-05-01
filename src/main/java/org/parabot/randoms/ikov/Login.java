package org.parabot.randoms.ikov;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.api.methods.Game;

/**
 * @author JKetelaar
 */
public class Login implements Random {

    private static int timer = 0;

    @Override
    public boolean activate() {
        return System.currentTimeMillis() - timer > 0 && !Game.isLoggedIn();
    }

    @Override
    public void execute() {
        timer = 0;

        // Move and click mouse on the login button
        Mouse.getInstance().moveMouse(380, 320);
        Mouse.getInstance().click(380, 320, true);

        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Game.isLoggedIn();
            }
        }, 4500);
        if (Game.isLoggedIn()) {
        /* Sleep to let the client load their objects and players */
            Time.sleep(5000);
        }else{
            // Move and click mouse on the close button
            Mouse.getInstance().moveMouse(515, 130);
            Mouse.getInstance().click(515, 130, true);
        }
    }

    @Override
    public String getName() {
        return "Login";
    }

    @Override
    public String getServer() {
        return "Ikov";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.SCRIPT;
    }

    /**
     *
     * @param timeOut seconds
     */
    public static void setTimer(int timeOut){
        timer = ((int) System.currentTimeMillis() + (timeOut * 1000));
    }
}
