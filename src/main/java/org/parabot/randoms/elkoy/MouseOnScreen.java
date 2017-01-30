package org.parabot.randoms.elkoy;

import org.parabot.core.Context;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;

import java.awt.*;

/**
 * @author JKetelaar, Fryslan
 */
public class MouseOnScreen implements Random {

    @Override
    public boolean activate() {
        return !onScreen();
    }

    @Override
    public void execute() {
        int x = org.parabot.environment.api.utils.Random.between(100, Context.getInstance().getApplet().getWidth());
        int y = org.parabot.environment.api.utils.Random.between(100, Context.getInstance().getApplet().getHeight());
        Mouse.getInstance().moveMouse(x, y);
    }

    @Override
    public String getName() {
        return "Mouse on screen";
    }

    @Override
    public String getServer() {
        return "Elkoy";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.SCRIPT;
    }

    private boolean onScreen(){
        Point loc = Mouse.getInstance().getPoint();
        return Context.getInstance().getApplet().contains(loc);
    }
}
