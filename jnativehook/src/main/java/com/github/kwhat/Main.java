package com.github.kwhat;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {
    private static KeyListener keyListener = new KeyListener();
    private static double[] startingPos = {0.0, 0.0};
    private static Player player = new Player(startingPos);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(keyListener);
        
        while (!keyListener.getKeys()[1]) { // MAIN LOOP
            System.out.println("run main loop");

            double playerIncrement = Constants.loopTime * Constants.playerSpeed;
            double[] playerMovementValue = {playerIncrement * ((keyListener.getKeys()[57421] ? 1 : 0)+(keyListener.getKeys()[57419] ? -1 : 0)), playerIncrement * ((keyListener.getKeys()[57416] ? 1 : 0) + (keyListener.getKeys()[57424] ? -1 : 0))};
            player.movePlayer(playerMovementValue);

            System.out.println("Player Position: (" + player.getPos()[0] + ", " + player.getPos()[1] + ")");
            Thread.sleep((int)(Constants.loopTime * 1000.0)); 
        }
    }
}