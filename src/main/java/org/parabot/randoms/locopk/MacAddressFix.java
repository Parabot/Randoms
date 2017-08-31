package org.parabot.randoms.locopk;

import org.parabot.core.Context;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.environment.randoms.Random;
import org.parabot.environment.randoms.RandomType;

import java.lang.reflect.Field;

/**
 * @author EmmaStone
 */
public class MacAddressFix implements Random {

    private boolean done;

    @Override
    public boolean activate() {
        return !done;
    }

    @Override
    public void execute() {
        try {
            final ASMClassLoader classLoader = Context.getInstance().getASMClassLoader();

            Class<?> createUID = classLoader.loadClass("com.locopk.client.rs.CreateUID");

            Field mac = createUID.getDeclaredField("mac");

            String randomMac = randomMacAddress();

            System.out.println(randomMac);

            mac.set(null, randomMac);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
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
        return "locopk";
    }

    @Override
    public RandomType getRandomType() {
        return RandomType.ON_SERVER_START;
    }

    private String randomMacAddress(){
        java.util.Random rand    = new java.util.Random();
        byte[]           macAddr = new byte[6];
        rand.nextBytes(macAddr);

        macAddr[0] = (byte)(macAddr[0] & (byte)254);  //zeroing last 2 bytes to make it unicast and locally adminstrated

        StringBuilder sb = new StringBuilder(18);
        for(byte b : macAddr){

            if(sb.length() > 0)
                sb.append("");

            sb.append(String.format("%02x", b));
        }


        return sb.toString().toUpperCase();
    }
}
