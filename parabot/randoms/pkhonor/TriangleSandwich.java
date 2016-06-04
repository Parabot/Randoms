package org.parabot.randoms.pkhonor;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.Item;

/**
 * Created with IntelliJ IDEA. User: Piet Jetse Date: 11-9-2014 Time: 22:29
 */
public class TriangleSandwich implements Random {

    Item item;

    @Override
    public boolean activate() {
        for (Item i : Inventory.getItems(6963)) {
            if (i != null) {
                this.item = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        if (this.item != null) {
            item.drop();
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(6963) == 0;
                }
            }, 1500);
        }
    }

    @Override
    public String getName() {
        return "Triangle Sandwich Handler";
    }

    @Override
    public String getServer() {
        return "pkhonor";
    }
}
