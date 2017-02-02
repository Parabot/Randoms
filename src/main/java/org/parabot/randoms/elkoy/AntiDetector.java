package org.parabot.randoms.elkoy;

import org.parabot.core.Context;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefField;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;

/**
 * @author JKetelaar, Empathy, Ethan
 */
public class AntiDetector implements Random, Runnable {

    private boolean activated;

    private final String[] toBeNull = {"P", "Q", "N"};
    private final String className = "cb";

    @Override
    public boolean activate() {
        return !activated;
    }

    @Override
    public void execute() {
        activated = true;

        new Thread(this).start();
    }

    @Override
    public String getName() {
        return "Anti Bot Detector";
    }

    @Override
    public String getServer() {
        return "Elkoy";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.ON_SERVER_START;
    }

    @Override
    public void run() {
        while (activated) {
            try {
                final RefClass refClass = new RefClass(Context.getInstance().getASMClassLoader().loadClass(className));

                for (RefField refField : refClass.getFields()) {
                    if (refField != null) {
                        if (refClass.isStatic()) {
                            if (refField.getType().getName().equalsIgnoreCase("boolean")) {
                                refField.set(false);
                            } else if (refField.getType().getName().equalsIgnoreCase("int")) {
                                int i = (int) refField.asObject();
                                if (i >= 0) {
                                    refField.set(-1);
                                }
                            } else if (refField.getType().getName().equalsIgnoreCase("string")) {
                                String str = (String) refField.asObject();
                                if (str.length() > 0) {
                                    refField.set(null);
                                }
                            }
                        }
                    }
                }

                for (String s : toBeNull) {
                    refClass.getField(s).set(null);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Time.sleep(25);
        }
    }
}
