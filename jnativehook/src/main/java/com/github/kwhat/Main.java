package com.github.kwhat;

import java.text.DecimalFormat;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {
    private static DecimalFormat df = new DecimalFormat("#.###");

    private static KeyListener keyListener = new KeyListener();
    private static double[] startingPos = {0.0, 0.0}; // meters
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

            // move player based on key presses
            double movementIncrement = Constants.loopTime * Constants.playerSpeed;
            double[] playerMovementValue = {((keyListener.getKeys()[Constants.playerMovementKeys[0]] ? movementIncrement : 0) + (keyListener.getKeys()[Constants.playerMovementKeys[1]] ? -movementIncrement : 0)), ((keyListener.getKeys()[Constants.playerMovementKeys[2]] ? movementIncrement : 0) + (keyListener.getKeys()[Constants.playerMovementKeys[3]] ? -movementIncrement : 0))};
            player.movePlayer(playerMovementValue);
            System.out.println("Player Position: (" + df.format(player.getPos()[0]) + ", " + df.format(player.getPos()[1]) + ")");

            // interact with nearby entities


            // display GUI

            Thread.sleep((int)(Constants.loopTime * 1000.0)); 
        }
    }
}