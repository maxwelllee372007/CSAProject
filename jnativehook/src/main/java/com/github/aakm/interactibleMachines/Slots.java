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
    /**
     * Interacts with the machine. This method implements a simple interaction flow for a single slots game.
     * @param player The player interacting with the machine.
     * @param keyListener The KeyListener to track key events during interaction.
     */
    public void interact(Player player, KeyListener keyListener) {
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
    /**
     * Welcomes the player to the machine and waits for them to press the interact key.
     * @param keyListener The KeyListener to track key events.
     */
    private void welcomePlayer(KeyListener keyListener) {
        System.out.println("Welcome to the slots machine!");
        GameGUI.slotsGUI.setVisible(true);
        System.out.println("Press '" + NativeKeyEvent.getKeyText(KeyBindings.interactKey) + "' to begin."); 
        GameGUI.slotsText.setText("Press '" + NativeKeyEvent.getKeyText(KeyBindings.interactKey) + "' to begin.");
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
    /**
     * Collects bets from the player and waits until they confirm their bet.
     * @param player The player placing the bet.
     * @param keyListener The KeyListener to track key events during betting.
     */
    private void collectBets(Player player, KeyListener keyListener) {
        double betAmount = 1.0;
        confirmBetAmount(betAmount, keyListener);
        if (keyListener.getKeys()[KeyBindings.escapeKey]) {
            System.out.println("Player has escaped the game.");
            return;
        }
        player.adjustBalance(-betAmount);
    }
    /**
     * Displays the bet amount to the player and waits for them to confirm it.
     * @param betAmount The amount the player has bet.
     * @param keyListener The KeyListener to track key events during betting.
     */
    private void confirmBetAmount(double betAmount, KeyListener keyListener) {
        System.out.println("Slots costs $" + dollarsdf.format(betAmount) + " to play" + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to pay)");
        GameGUI.slotsText.setText("Slots costs $" + dollarsdf.format(betAmount) + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to pay)");
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
    }
    /** 
     * generates a randomized number for the slots game.
     * @return a rigged number between 0 and 2, inclusive
     */
    private int getRandomResult() {
        int ret = (int)(Math.random() * 5); 
        if (ret == 3 || ret == 4) {
            ret = 2; // 4 and 5 are treated as 2
        } else if (ret == 1 || ret == 2) {
            ret = 1; // 2 and 3 are treated as 1
        }
        return ret; // returns a random number between 0 and 2, inclusive
    }
    /**
     * Plays the slots game by spinning the reels and displaying the results.
     * @param keyListener The KeyListener to track key events during the game.
     * @param player The player participating in the slots game.
     */
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
        int[] results = {getRandomResult(), getRandomResult(), getRandomResult()}; // 0, 0, 0 is win; identical is also win; values are 0-2, inclusive

        
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
                GameGUI.slotsText.setText("Spinning...");
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
            System.out.println("Jackpot! +$" + dollarsdf.format(50.0));
            GameGUI.slotsText.setText("Jackpot! +$" + dollarsdf.format(50.0) + " Press 'Enter' to play again");
            player.adjustBalance(50.0);
        } else if (results[0] == results[1] && results[1] == results[2]) {
            System.out.println("You win! +$" + dollarsdf.format(3.0));
            GameGUI.slotsText.setText("You win! +$" + dollarsdf.format(3.0)+ " Press 'Enter' to play again");
            player.adjustBalance(3.0);
        // } else if (results[0] == results[1] || results[1] == results[2] || results[0] == results[2]) {
        //     System.out.println("You win! +$" + dollarsdf.format(2.0));
        //     player.adjustBalance(2.0);
        } else {
            System.out.println("You lose!");
            GameGUI.slotsText.setText("You lose!"+ " Press 'Enter' to play again");
        }
        displayBalance(player.getBalance());
    }
    private void spinLeft() {
        System.out.println("Spinning the left wheel at display frame " + displayFrames[0] + "...");
        GameGUI.LeftReel.setIcon(GameGUI.reelSpinIcons[displayFrames[0]]);
        if (displayFrames[0] == 2) {
            displayFrames[0] = 0;
        } else {
            displayFrames[0]++;
        }
    }
    private void displayLeft(int[] results) {
        GameGUI.LeftReel.setIcon(GameGUI.reelEndIcons[results[0]]);
        System.out.println("left is " + results[0]);
    }
    private void spinMiddle() {
        System.out.println("Spinning the middle wheel at display frame " + displayFrames[1] + "...");
        GameGUI.MidReel.setIcon(GameGUI.reelSpinIcons[displayFrames[1]]);
        if (displayFrames[1] == 2) {
            displayFrames[1] = 0;
        } else {
            displayFrames[1]++;
        }    
    }
    private void displayMiddle(int[] results) {
        GameGUI.MidReel.setIcon(GameGUI.reelEndIcons[results[1]]);
        System.out.println("middle is " + results[1]);
    }
    private void spinRight() {
        System.out.println("Spinning the right wheel at display frame " + displayFrames[2] + "...");
        GameGUI.RightReel.setIcon(GameGUI.reelSpinIcons[displayFrames[2]]);
        if (displayFrames[2] == 2) {
            displayFrames[2] = 0;
        } else {
            displayFrames[2]++;
        }    
    }
    private void displayRight(int[] results) {
        GameGUI.RightReel.setIcon(GameGUI.reelEndIcons[results[2]]);
        System.out.println("right is " + results[2]);
    }
    /**
     * Concludes the game by asking the player if they want to play again or exit.
     * @param player The player whose balance is displayed.
     * @param keyListener The KeyListener to track key events during conclusion.
     */
    private void concludeGame(Player player, KeyListener keyListener) {
        double startTime = System.currentTimeMillis();
        System.out.println("play again? (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to play again)" + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.escapeKey) + "' to exit)");
        // GameGUI.slotsText.setText("play again? (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to play again)" + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.escapeKey) + "' to exit)");
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
 