package com.github.aakm;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.text.JTextComponent.KeyBinding;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.Constants.Machines;
import com.github.aakm.interactibleMachines.Machine;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Obstacles;
import com.github.gameGUI.GameGUI;


public class Main {
    public static DecimalFormat df = new DecimalFormat("#.###");

    private static KeyListener keyListener = new KeyListener();
    private static Player player = new Player(Constants.playerStartingPos, Constants.playerRadius);
    private static Obstacles obstacles = new Obstacles();
    private static ArrayList<Machine> machines = Machines.machines;

    private static GameGUI game = new GameGUI();

    public static void main(String[] args) throws InterruptedException {


        System.out.println("Hello world!");
        try {
            // Suppress JNativeHook logging
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger(org.jnativehook.GlobalScreen.class.getPackage().getName());
            logger.setLevel(java.util.logging.Level.OFF);
            logger.setUseParentHandlers(false);

            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(keyListener);
        
        generateObstacles();
        int i = 0;
        double absoluteStartTime = System.currentTimeMillis();
        System.out.println("absolute start time: " + absoluteStartTime);
        while (!keyListener.getKeys()[KeyBindings.interactKey]) {
            try{
                Thread.sleep(null);
            }
            catch(Exception e){

            }
        }
        GameGUI.startScreen.setVisible(false);
        while (!(keyListener.getKeys()[KeyBindings.escapeKey] && keyListener.getKeys()[KeyBindings.controlKey])) { // MAIN LOOP is exited by pressing control + escape
            double startTime = System.currentTimeMillis();
            // System.out.println("run main loop" + i);

            // move player based on key presses
            double movementIncrement = Constants.loopTime * Constants.playerSpeed;
            double[] playerMovementValue = {((keyListener.getKeys()[KeyBindings.playerMovementKeys[0]] ? movementIncrement : 0) + (keyListener.getKeys()[KeyBindings.playerMovementKeys[1]] ? -movementIncrement : 0)), ((keyListener.getKeys()[KeyBindings.playerMovementKeys[2]] ? movementIncrement : 0) + (keyListener.getKeys()[KeyBindings.playerMovementKeys[3]] ? -movementIncrement : 0))};
            GameGUI.slotsGUI.setVisible(false);
            GameGUI.rouletteGUI.setVisible(false);
            if (playerMovementValue[0] != 0 && playerMovementValue[1] != 0) {
                playerMovementValue[0] = Math.sqrt(2) / 2 * playerMovementValue[0];
                playerMovementValue[1] = Math.sqrt(2) / 2 * playerMovementValue[1];
            }
            if (playerMovementValue[0] != 0 || playerMovementValue[1] != 0) {
                player.movePlayer(playerMovementValue);
                // System.out.println("Player moved: (" + df.format(playerMovementValue[0]) + ", " + df.format(playerMovementValue[1]) + ")");
                // System.out.println("Player Position: (" + df.format(player.getPos()[0]) + ", " + df.format(player.getPos()[1]) + ")");
                // System.out.println("GUIPose:(" + game.getPlayerGUIPos()[0]+","+ game.getPlayerGUIPos()[1] + ")");   
            }

            // detect and resolve player collisions
            obstacles.resolveCollisions(player); // moves player back to nearest allowable location
            

            // interact with nearby entities
            interact(game);
                

            // display GUI
            if (playerMovementValue[0] != 0 || playerMovementValue[1] != 0) {
                game.movePlayer(player.getPos(), playerMovementValue[0]);   
            }

            // global sleep
            while (System.currentTimeMillis() - startTime < Constants.loopTime * 1000.0) {
                // do nothing
            }
            i++;
        }

        System.out.println("game exited or finished in " + (System.currentTimeMillis() - absoluteStartTime) / 1000.0 + " seconds and " + i + " iterations");
        System.out.println("absolute start time: " + System.currentTimeMillis());
        System.out.println("absolute end time: " + System.currentTimeMillis());
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        return;
    }
    public static void interact(GameGUI game) {
        game.removeInteractPrompt();
        for (Machine machine : machines) {
            if (machine.getInteractible(player)) {
                if (keyListener.getKeys()[KeyBindings.interactKey]) {
                    machine.interact(player, keyListener);
                    return; // only interact with one machine at a time
                } else {
                    game.displayInteractPrompt();
                }
            }
        }
    }

    public static void generateObstacles() {
        obstacles.addObstacle(Constants.outerBoundary);
        for (Machine machine : machines) {
            obstacles.addObstacle(machine.getCollisionBox());
        }
    }
    
}