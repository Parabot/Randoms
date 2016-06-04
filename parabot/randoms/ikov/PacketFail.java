package org.parabot.randoms.ikov;

import org.parabot.core.ui.Logger;
import org.parabot.environment.scripts.randoms.Random;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Game;
import org.rev317.min.script.ScriptEngine;

/**
 * @author JKetelaar
 */
public class PacketFail implements Random, MessageListener {

    private int fails = 0;
    private long lastFail = 0;

    public PacketFail(){
        ScriptEngine.getInstance().addMessageListener(this);
    }

    @Override
    public boolean activate() {
        if (fails >= 3 && System.currentTimeMillis() - lastFail <= 25 * 1000){
            return true;
        }else if (System.currentTimeMillis() - lastFail > 25 * 1000){
            lastFail = 0;
            fails = 0;
        }
        return false;
    }

    @Override
    public void execute() {
        if (!Game.dropClient()){
            Logger.addMessage("Couldn't drop the client, please restart if required", true);
        }
        fails = 0;
    }

    @Override
    public String getName() {
        return "Client restarter";
    }

    @Override
    public String getServer() {
        return "Ikov";
    }

    @Override
    public void messageReceived(MessageEvent messageEvent) {
        if (messageEvent.getType() == 0){
            String message = messageEvent.getMessage().toLowerCase();
            switch (message){
                case "unable to receive input":
                case "command does not exist":
                case "too far away from this object":
                case "that object does not exist":
                case "party room is currently disabled":
                    fails++;
                    lastFail = System.currentTimeMillis();
                    break;
            }
        }
    }
}
