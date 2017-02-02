package org.parabot.randoms.elkoy;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.parabot.api.io.WebUtil;
import org.parabot.core.Context;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Menu;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

/**
 * @author EmmaStone
 */
public class QuestionSolver implements Random {

    @Override
    public boolean activate() {
        return Game.isLoggedIn() && Interfaces.getBackDialogId() == 368;
    }

    @Override
    public void execute() {
        String message = Loader.getClient().getInterfaceCache()[372].getMessage();
        if (!message.contains("otherwise you will be teleported")) {
            message = message.replace("@dre@ ", "");

            Logger.addMessage("Contacting server to get an answer", false);
            String answer = getAnswer(message);

            if (answer == null) {
                Logger.addMessage("Could not solve the question, please report this question (and the possible answer) on the forums", true);
                Logger.addMessage("Question: " + message, true);
                forceRelog();
                Logger.addMessage("Logged account out for security measures", true);
                Context.getInstance().getRunningScript().setState(Script.STATE_STOPPED);
            } else {
                Logger.addMessage("Answer found, now trying: " + answer, false);
                Menu.sendAction(679, -1, -1, 373);
                Time.sleep(1000);
                Keyboard.getInstance().sendKeys(answer);
            }
        }
        Time.sleep(1000);
    }

    private String getAnswer(String question) {
        try {
            JSONObject object = (JSONObject) WebUtil.getJsonParser().parse(WebUtil.getContents("http://bdn.parabot.org/api/v2/data/questions/get",
                    "server=ikov&question=" + URLEncoder.encode(question, "UTF-8"))); //TODO Implement BDN V3
            JSONObject result;
            if ((result = (JSONObject) object.get("result")) != null) {
                Object answer;
                if ((answer = result.get("answer")) != null) {
                    return (String) answer;
                }
            }
        } catch (MalformedURLException | UnsupportedEncodingException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void forceRelog() {
        Logger.addMessage("Logging out...", false);
        Loader.getClient().dropClient();
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !Game.isLoggedIn();
            }
        }, 5000);
    }

    @Override
    public String getName() {
        return "Question Solver";
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
