package com.github.aakm.interactibleMachines;

import javax.swing.*;

import org.jnativehook.keyboard.NativeKeyEvent;

import com.github.aakm.Main;
import com.github.aakm.Player;
import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;
import com.github.gameGUI.GameGUI;

public class Slots extends Machine{
    private int[] displayFrames = {0, 0, 0};

    public Slots(Box hitBox, InteractionBox interactionBox) {
        super(hitBox, interactionBox);
    }
    public boolean getInteractible(Player player) {
        return super.getInteractible(player);
    }
    public Box getCollisionBox() {
        return super.getCollisionBox();
    }

    @Override
    public void interact(Player player, KeyListener keyListener) {
        // TODO: add interrupt functionality
        welcomePlayer(keyListener);
        while (keyListener.getKeys()[KeyBindings.interactKey] || keyListener.getKeys()[KeyBindings.confirmKey]) {
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                GameGUI.slotsGUI.setVisible(false);
                System.out.println("Player has escaped the game.");
                return;
            }
            collectBets(player, keyListener);
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                GameGUI.slotsGUI.setVisible(false);
                System.out.println("Player has escaped the game.");
                return;
            }
            playSlots(keyListener, player);
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                GameGUI.slotsGUI.setVisible(false);
                System.out.println("Player has escaped the game.");
                return;
            }
            concludeGame(player, keyListener);
        }
        System.out.println("exited slots game");
    }
    private void welcomePlayer(KeyListener keyListener) {
        System.out.println("Welcome to the slots machine!");
        GameGUI.slotsGUI.setVisible(true);
        System.out.println("Press '" + NativeKeyEvent.getKeyText(KeyBindings.interactKey) + "' to begin."); 
        while (keyListener.getKeys()[KeyBindings.interactKey]) {
            // System.out.println("waiting for player to release interact key");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                GameGUI.slotsGUI.setVisible(false);
                System.out.println("Player has escaped the game.");
                return;
            }
        }
        while (!keyListener.getKeys()[KeyBindings.interactKey]) {
            // System.out.println("waiting for player to press interact key");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }
        }
    }
    private void collectBets(Player player, KeyListener keyListener) {
        double betAmount = 1.0;
        confirmBetAmount(betAmount, keyListener);
        if (keyListener.getKeys()[KeyBindings.escapeKey]) {
            System.out.println("Player has escaped the game.");
            return;
        }
        player.adjustBalance(-betAmount);
    }
    private void confirmBetAmount(double betAmount, KeyListener keyListener) {
        System.out.println("Slots costs $" + dollarsdf.format(betAmount) + " to play" + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to pay)");
        while (!keyListener.getKeys()[KeyBindings.confirmKey]) {
            // System.out.println("waiting for player to press confirm key");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }
        }
        // TODO: add GUI display of bet amount
    }
    private void playSlots(KeyListener keyListener, Player player) { 
        double waitTimeLeft = Math.random() * 500.0 + 1000.0; // random wait time between 2 and 3 seconds
        double waitTimeMiddle = Math.random() * 250.0 + 1000.0; 
        double waitTimeRight = Math.random() * 250.0 + 1000.0; 
        double endDisplayTime = 700.0; // total time to spin the wheel
        double maxTime = 5000.0; // max time to spin the wheel
        double startTime = System.currentTimeMillis();
        double startTimeFirst = startTime;
        double startTimeSecond = startTime;
        double startTimeThird = startTime;
        int numSpacePressed = 0;
        int numSpaceReleased = 0;
        int[] results = {(int)(Math.random() * 3), (int)(Math.random() * 3), (int)(Math.random() * 3)}; // 0, 0, 0 is win; identical is also win; values are 0-5, inclusive

        
        while (System.currentTimeMillis() - startTime < maxTime) {
            if (keyListener.getKeys()[KeyBindings.interactKey]) {
                numSpacePressed = numSpaceReleased + 1;
            } else {
                numSpaceReleased = numSpacePressed;
            }
            if (System.currentTimeMillis() - startTime < waitTimeLeft && numSpacePressed < 1) {
                spinLeft();
                spinMiddle();
                spinRight();
                // GameGUI.LeftReel.repaint();
                startTimeFirst = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - startTimeFirst < waitTimeMiddle && numSpacePressed < 2) {
                displayLeft(results);
                spinMiddle();
                spinRight();
                startTimeSecond = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - startTimeSecond < waitTimeRight && numSpacePressed < 3) {
                displayLeft(results);
                displayMiddle(results);
                spinRight();
                startTimeThird = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - startTimeThird < endDisplayTime) {
                displayLeft(results);
                displayMiddle(results);
                displayRight(results);
            } else {
                break;
                // System.out.println("slots timing error");
            }
            
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        System.out.println("Game has ended");
        if (results[0] == 0 && results[1] == 0 && results[2] == 0) {
            System.out.println("Jackpot! +$" + dollarsdf.format(10.0));
            player.adjustBalance(10.0);
        } else if (results[0] == results[1] && results[1] == results[2]) {
            System.out.println("You win! +$" + dollarsdf.format(3.0));
            player.adjustBalance(3.0);
        // } else if (results[0] == results[1] || results[1] == results[2] || results[0] == results[2]) {
        //     System.out.println("You win! +$" + dollarsdf.format(2.0));
        //     player.adjustBalance(2.0);
        } else {
            System.out.println("You lose!");
        }
        displayBalance(player.getBalance());
    }
    private void spinLeft() {
        // TODO: add display wheel spinner
        System.out.println("Spinning the left wheel at display frame " + displayFrames[0] + "...");
        GameGUI.LeftReel.setIcon(GameGUI.reelSpinIcons[displayFrames[0]]);
        if (displayFrames[0] == 2) {
            displayFrames[0] = 0;
        } else {
            displayFrames[0]++;
        }
    }
    private void displayLeft(int[] results) {
        // TODO: add display wheel spinner
        GameGUI.LeftReel.setIcon(GameGUI.reelEndIcons[results[0]]);
        System.out.println("left is " + results[0]);
    }
    private void spinMiddle() {
        // TODO: add display wheel spinner
        System.out.println("Spinning the middle wheel at display frame " + displayFrames[1] + "...");
        GameGUI.MidReel.setIcon(GameGUI.reelSpinIcons[displayFrames[1]]);
        if (displayFrames[1] == 2) {
            displayFrames[1] = 0;
        } else {
            displayFrames[1]++;
        }    
    }
    private void displayMiddle(int[] results) {
        // TODO: add display wheel spinner
        GameGUI.MidReel.setIcon(GameGUI.reelEndIcons[results[1]]);
        System.out.println("middle is " + results[1]);
    }
    private void spinRight() {
        // TODO: add display wheel spinner
        System.out.println("Spinning the right wheel at display frame " + displayFrames[2] + "...");
        GameGUI.RightReel.setIcon(GameGUI.reelSpinIcons[displayFrames[2]]);
        if (displayFrames[2] == 2) {
            displayFrames[2] = 0;
        } else {
            displayFrames[2]++;
        }    
    }
    private void displayRight(int[] results) {
        // TODO: add display wheel spinner
        GameGUI.RightReel.setIcon(GameGUI.reelEndIcons[results[2]]);
        System.out.println("right is " + results[2]);
    }
    private void concludeGame(Player player, KeyListener keyListener) {
        double startTime = System.currentTimeMillis();
        System.out.println("play again? (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to play again)" + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.escapeKey) + "' to exit)");
        while (!keyListener.getKeys()[KeyBindings.confirmKey]){// && System.currentTimeMillis() - startTime < 5000) {
            
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }
            // System.out.println("waiting for player to press interact key");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void displayBalance(double balance) {
        System.out.println("Your balance is: $" + dollarsdf.format(balance));
    }
}
 