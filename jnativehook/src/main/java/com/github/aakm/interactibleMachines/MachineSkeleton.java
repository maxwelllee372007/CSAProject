package com.github.aakm.interactibleMachines;






import org.jnativehook.keyboard.NativeKeyEvent;


import com.github.aakm.Player;
import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;


public class MachineSkeleton extends Machine{


    public MachineSkeleton(Box hitBox, InteractionBox interactionBox) {
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
        collectBets(player, keyListener);
        chooseSelection(player, keyListener);
        spinWheel();
        concludeGame(player);
    }
    private void welcomePlayer(KeyListener keyListener) {
        System.out.println("Welcome to the roulette table!");
        System.out.println("Press '" + NativeKeyEvent.getKeyText(KeyBindings.interactKey) + "' to begin.");
        while (keyListener.getKeys()[KeyBindings.interactKey]) {
            System.out.println("waiting for player to release interact key");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!keyListener.getKeys()[KeyBindings.interactKey]) {
            System.out.println("waiting for player to press interact key");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void collectBets(Player player, KeyListener keyListener) {
        double betAmount = 0.0;
        boolean[] previousKeys = keyListener.getKeys();
        displayBetAmount(betAmount);
        // while (keyListener.getKeys()[KeyBindings.confirmKey]) {}
        while (!keyListener.getKeys()[KeyBindings.confirmKey]) {
            // System.out.println("waiting for player to press confirm key");
            for (int i = 2; i <= 11; i++) {
                if (keyListener.getKeys()[i]) {
                    betAmount = betAmount * 10 + (i%10 - 1);
                    displayBetAmount(betAmount);
                }
            }
            if (keyListener.getKeys()[KeyBindings.deleteKey]) {
                betAmount = Math.floor(betAmount / 10);
                displayBetAmount(betAmount);
            }
            previousKeys = keyListener.getKeys();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
    private void chooseSelection(Player player, KeyListener keyListener) {
        System.out.println("Choose a selection: ");
        System.out.println(NativeKeyEvent.getKeyText(KeyBindings.redKey) + ": Red");
        System.out.println(NativeKeyEvent.getKeyText(KeyBindings.blackKey) + ": Black");
        System.out.println(NativeKeyEvent.getKeyText(KeyBindings.greenKey) + ": Green");
        int selection = -1;
        while (keyListener.getKeys()[KeyBindings.confirmKey]) {}
        while (!keyListener.getKeys()[KeyBindings.confirmKey] || selection == -1) {
            if (keyListener.getKeys()[KeyBindings.redKey]) {
                selection = 1;
                displaySelection(selection);
            } else if (keyListener.getKeys()[KeyBindings.blackKey]) {
                selection = 2;
                displaySelection(selection);
            } else if (keyListener.getKeys()[KeyBindings.greenKey]) {
                selection = 3;
                displaySelection(selection);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void displaySelection(int selection) {
        switch (selection) {
            case 1:
                System.out.println("Selecting Red.");
                System.out.println("Press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to confirm your selection.");
                break;
            case 2:
                System.out.println("Selecting Black.");
                System.out.println("Press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to confirm your selection.");
                break;
            case 3:
                System.out.println("Selecting Green.");
                System.out.println("Press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to confirm your selection.");
                break;
            default:
                System.out.println("No color selected.");
        }
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
