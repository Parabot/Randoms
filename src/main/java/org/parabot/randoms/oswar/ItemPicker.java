package org.parabot.randoms.oswar;

import org.json.simple.JSONObject;
import org.parabot.core.Core;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Menu;


/**
 * @author AlexanderBielen
 */
public class ItemPicker implements Random {
    private final int INTERFACE_ID = 33300;
    private final String URL = "http://bdn.parabot.org/api/v2/data/items/";

    @Override
    public boolean activate() {
        return Game.isLoggedIn() && Interfaces.getOpenInterfaceId() == INTERFACE_ID;
    }

    @Override
    public void execute() {
        Core.verbose("Item picker random event activated");
        Time.sleep(2000);

        String message = Interfaces.getInterface(INTERFACE_ID + 2).getMessage();
        String itemName = message.substring(message.indexOf("'") + 1, message.indexOf("'", message.indexOf("'") + 1));

        for(int i = INTERFACE_ID + 11; i <= INTERFACE_ID + 17; i += 3) {
            try {
                int id = Interfaces.getInterface(i).getItems()[0];

                String response = WebUtil.getContents(URL.concat("" + id));
                JSONObject json = (JSONObject) WebUtil.getJsonParser().parse(response);
                JSONObject result = (JSONObject) WebUtil.getJsonParser().parse(json.get("result").toString());

                if(result.get("name").equals(itemName)) {
                    Core.verbose("Attempting solution");
                    Menu.sendAction(315,0,0,i+1,2);
                    break;
                }
            } catch(Exception ignore) {}
        }
        Time.sleep(1000);
    }

    @Override
    public String getName() {
        return "Item picker";
    }

    @Override
    public String getServer() {
        return "OSWar";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.SCRIPT;
    }
}