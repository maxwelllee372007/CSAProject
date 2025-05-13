package com.github.kwhat;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.github.kwhat.Constants.KeyBindings;
import com.github.kwhat.Constants.Machines;
import com.github.kwhat.interactibleMachines.Machine;
import com.github.kwhat.keyboardTracker.KeyListener;
import com.github.kwhat.obstacles.Obstacles;

public class Main {
    private static DecimalFormat df = new DecimalFormat("#.###");

    private static KeyListener keyListener = new KeyListener();
    private static Player player = new Player(Constants.playerStartingPos, Constants.playerRadius);
    private static Obstacles obstacles = new Obstacles();
    private static ArrayList<Machine> machines = Machines.machines;
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
        
        generateObstacles();
        int i = 0;
        while (!keyListener.getKeys()[1]) { // MAIN LOOP
            System.out.println("run main loop");

            // move player based on key presses
            double movementIncrement = Constants.loopTime * Constants.playerSpeed;
            double[] playerMovementValue = {((keyListener.getKeys()[KeyBindings.playerMovementKeys[0]] ? movementIncrement : 0) + (keyListener.getKeys()[KeyBindings.playerMovementKeys[1]] ? -movementIncrement : 0)), ((keyListener.getKeys()[KeyBindings.playerMovementKeys[2]] ? movementIncrement : 0) + (keyListener.getKeys()[KeyBindings.playerMovementKeys[3]] ? -movementIncrement : 0))};
            player.movePlayer(playerMovementValue);
            System.out.println("Player Position: (" + df.format(player.getPos()[0]) + ", " + df.format(player.getPos()[1]) + ")");

            // detect and resolve player collisions
            obstacles.resolveCollisions(player); // moves player back to nearest allowable location
            

            // interact with nearby entities
            if (keyListener.getKeys()[KeyBindings.interactKey]) {
                for (Machine machine : machines) {
                    if (machine.getInteractible(player)) {
                        machine.interact();
                    }
                }
            }
                

            // display GUI


            // global sleep
            Thread.sleep((int)(Constants.loopTime * 1000.0)); 
            System.out.println("loop iteration " + i++);
        }

        System.out.println("game exited or finished");
    }

    public static void generateObstacles() {
        obstacles.addObstacle(Constants.outerBoundary);
        for (Machine machine : machines) {
            obstacles.addObstacle(machine.getCollisionBox());
        }
    }
}