package com.github.aakm.interactibleMachines;



import org.jnativehook.keyboard.NativeKeyEvent;

import com.github.aakm.Main;
import com.github.aakm.Player;
import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

public class Slots extends Machine{

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
        if (keyListener.getKeys()[KeyBindings.escapeKey]) {
            System.out.println("Player has escaped the game.");
            return;
        }
        collectBets(player, keyListener);
        if (keyListener.getKeys()[KeyBindings.escapeKey]) {
            System.out.println("Player has escaped the game.");
            return;
        }
        spinWheel();
        if (keyListener.getKeys()[KeyBindings.escapeKey]) {
            System.out.println("Player has escaped the game.");
            return;
        }
        concludeGame(player);
    }
    private void welcomePlayer(KeyListener keyListener) {
        System.out.println("Welcome to the slots machine!");
        System.out.println("Press '" + NativeKeyEvent.getKeyText(KeyBindings.interactKey) + "' to begin."); 
        while (!keyListener.getKeys()[KeyBindings.interactKey]) {
            // System.out.println("waiting for player to press interact key");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void collectBets(Player player, KeyListener keyListener) {
        double betAmount = 1.0;
        confirmBetAmount(betAmount, keyListener);
        player.adjustBalance(-betAmount);
    }
    private void confirmBetAmount(double betAmount, KeyListener keyListener) {
        System.out.println("slots costs " + super.dollarsdf.format(betAmount) + " dollars to play" + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to confirm)");
        while (!keyListener.getKeys()[KeyBindings.confirmKey]) {
            // System.out.println("waiting for player to press confirm key");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // TODO: add GUI display of bet amount
    }
    private void spinWheel() { 
        double waitTime = Math.random() * 1000.0 + 2000.0; // random wait time between 2 and 3 seconds
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < waitTime) {
            displayWheelSpinner();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void displayWheelSpinner() {
        // TODO: add display wheel spinner
        System.out.println("Spinning the wheel...");
    }
    private void concludeGame(Player player) {
        System.out.println("The wheel has stopped spinning.");
        displayBalance(player.getBalance());

    }
    private void displayBalance(double balance) {
        System.out.println("Your balance is: " + balance);
    }
}
 