package org.parabot.randoms.ikov;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.parabot.core.Context;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URLEncoder;

public class QuestionSolver implements Random {

    private final int backlogID = 368, cacheID = 372;

    @Override
    public boolean activate() {
        return Game.isLoggedIn() && Game.getOpenBackDialogId() == backlogID;
    }

    @Override
    public void execute() {
        String message = Loader.getClient().getInterfaceCache()[cacheID].getMessage();
        if (!message.contains("lose items on death, beware")) {
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

    public String getAnswer(String question) {
        try {
            JSONObject object = (JSONObject) WebUtil.getJsonParser().parse(WebUtil.getContents("http://bdn.parabot.org/api/v2/data/questions/get",
                    "server=ikov&question=" + URLEncoder.encode(question, "UTF-8")));
            JSONObject result;
            if ((result = (JSONObject) object.get("result")) != null) {
                Object answer;
                if ((answer = result.get("answer")) != null) {
                    return (String) answer;
                }
            }
        } catch (ParseException | MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception ignored) {
            // Catching for old bots
        }
        return null;
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.SCRIPT;
    }

    @Override
    public String getName() {
        return "Question solver";
    }

    @Override
    public String getServer() {
        return "Ikov";
    }

    private void forceRelog() {
        try {
            Logger.addMessage("Logging out...", false);
            Class<?> c = Loader.getClient().getClass();

            Method m = c.getDeclaredMethod("am");
            m.setAccessible(true);

            m.invoke(Loader.getClient());
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !Game.isLoggedIn();
                }
            }, 5000);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Login.setTimer(5 * 60);
    }
}
