package org.parabot.randoms.runewild;

import java.util.UUID;
import org.parabot.api.output.Logger;
import org.parabot.core.Context;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefField;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;

/**
 * @author EmmaStone
 */
public class MacAddressFix_Runewild implements Random {

    private boolean done;

    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
        try {
            final ASMClassLoader classLoader = Context.getInstance().getASMClassLoader();

            RefClass createUID = new RefClass(classLoader.loadClass("com.rw.client.rs.CreateUID"));


            try {
                Logger.info("MacAddressFix_RW", String.format(
                        "Before applying MAC override : %s | %s | %s",
                        createUID.getField("mac").getField().get(""),
                        createUID.getField("firstId").getField().get(""),
                        createUID.getField("secondId").getField().get("")));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            String s1 = UUID.randomUUID().toString();
            String s2 = UUID.randomUUID().toString();

            RefField mac = createUID.getField("mac");
            mac.set("lolmkay");

            RefField firstId = createUID.getField("firstId");
            firstId.set(s1);

            RefField secondId = createUID.getField("secondId");
            secondId.set(s2);
            try {
                Logger.info("MacAddressFix_RW", String.format(
                        "Applied MAC override : %s | %s | %s",
                        mac.getField().get(""),
                        firstId.getField().get(""),
                        secondId.getField().get("")));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Logger.error("Macfix RW", e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        done = true;
    }

    @Override
    public String getName() {
        return "Mac Address Fix";
    }

    @Override
    public String getServer() {
        return "RuneWild";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.ON_SERVER_START;
    }

    private String randomMacAddress() {
        java.util.Random rand    = new java.util.Random();
        byte[]           macAddr = new byte[6];
        rand.nextBytes(macAddr);

        macAddr[0] = (byte) (macAddr[0] & (byte) 254);  //zeroing last 2 bytes to make it unicast and locally adminstrated

        StringBuilder sb = new StringBuilder(18);
        for (byte b : macAddr) {

            if (sb.length() > 0) {
                sb.append("");
            }

            sb.append(String.format("%02x", b));
        }

        return sb.toString().toUpperCase();
    }
}
