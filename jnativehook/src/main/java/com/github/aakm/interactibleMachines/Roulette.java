package com.github.aakm.interactibleMachines;

import java.security.Key;

import com.github.aakm.Player;
import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

public class Roulette extends Machine{

    public Roulette(Box hitBox, InteractionBox interactionBox) {
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
        // TODO: add
        System.out.println("interacting with roulette");
        welcomePlayer(keyListener);
        collectBets(player, keyListener);
        chooseSelection(player, keyListener);
        spinWheel();
        concludeGame();
        
    }
    private void welcomePlayer(KeyListener keyListener) {
        System.out.println("Welcome to the roulette table!");
        System.out.println("Press 'spacebar' to begin."); // todo: add keybinding autodetect to syso
        while (!keyListener.getKeys()[KeyBindings.interactKey]) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void chooseSelection(Player player, KeyListener keyListener) {
        System.out.println("Choose a selection: ");
        System.out.println("1. Red");
        System.out.println("2. Black");
        System.out.println("3. Green");
        int selection = -1;
        while (!keyListener.getKeys()[KeyBindings.interactKey]) {
            if (keyListener.getKeys()[KeyBindings.redKey]) {
                selection = 1;
            } else if (keyListener.getKeys()[KeyBindings.blackKey]) {
                selection = 2;
            } else if (keyListener.getKeys()[KeyBindings.greenKey]) {
                selection = 3;
            }
            displaySelection(selection);
            if (selection != -1) {
                break;
            }
        }
    }
    private void displaySelection(int selection) {
        switch (selection) {
            case 1:
                System.out.println("Selecting Red.");
                break;
            case 2:
                System.out.println("Selecting Black.");
                break;
            case 3:
                System.out.println("Selecting Green.");
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
    }
    private void collectBets(Player player, KeyListener keyListener) {
        double betAmount = 0.0;
        while (!keyListener.getKeys()[KeyBindings.interactKey]) {
            displayBetAmount(betAmount);
            betAmount = keyListener.getNumericalInput(KeyBindings.confirmKey, KeyBindings.deleteKey);
        }
        confirmBetAmount(betAmount);
        player.adjustBalance(-betAmount);
    }
    private void displayBetAmount(double betAmount) {
        System.out.println("Bet amount: " + betAmount);
        // TODO: add GUI display of bet amount
    }
    private void confirmBetAmount(double betAmount) {
        System.out.println("Selected bet amount: " + betAmount);
        // TODO: add GUI display of bet amount
    }
    private void spinWheel() { 
        double waitTime = Math.random() * 1000.0 + 2000.0; // random wait time between 2 and 3 seconds
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < waitTime) {
            displayWheelSpinner();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void displayWheelSpinner() {
        // TODO: add display wheel spinner
        System.out.println("Spinning the wheel...");
    }
    private void concludeGame() {
        System.out.println("The wheel has stopped spinning.");
        

    }
    private void displayBalance(Player player) {
        System.out.println("Your balance is: " + player.getBalance());
    }
}
 