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
    /**
     * Interacts with the machine. This method implements a simple interaction flow for a roulette game.
     * @param player The player interacting with the machine.
     * @param keyListener The KeyListener to track key events during interaction.
     */
    public void interact(Player player, KeyListener keyListener) {
        welcomePlayer(keyListener);
        collectBets(player, keyListener);
        chooseSelection(player, keyListener);
        spinWheel();
        concludeGame(player);
    }
    /**
     * Welcomes the player to the machine and waits for them to press the interact key.
     * @param keyListener The KeyListener to track key events.
     */
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
    /**
     * Collects bets from the player until they confirm their bet.
     * @param player The player placing the bet.
     * @param keyListener The KeyListener to track key events during betting.
     */
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
    /**
     * Displays the current bet amount to the player.
     * @param betAmount The amount of the bet to display.
     */
    private void displayBetAmount(double betAmount) {
        System.out.println("Bet amount: " + betAmount);
        // TODO: add GUI display of bet amount
    }
    /**
     * Confirms the bet amount with the player.
     * @param betAmount The amount of the bet to confirm.
     */
    private void confirmBetAmount(double betAmount) {
        System.out.println("Selected bet amount: " + betAmount);
        // TODO: add GUI display of bet amount
    }
    /**
     * Prompts the player to choose a selection (Red, Black, or Green) and waits for confirmation.
     * @param player The player making the selection.
     * @param keyListener The KeyListener to track key events during selection.
     */
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
    /**
     * Displays the selected color to the player and prompts for confirmation.
     * @param selection The player's selection (1 for Red, 2 for Black, 3 for Green).
     */
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
    /**
     * Simulates spinning the roulette wheel for a random duration between 2 and 3 seconds.
     */
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
    /**
     * Displays the spinning wheel animation.
     * This method simulates the wheel spinning by printing a message to the console.
     */
    private void displayWheelSpinner() {
        // TODO: add display wheel spinner
        System.out.println("Spinning the wheel...");
    }
    /**
     * Concludes the game by displaying the final balance of the player.
     * @param player The player whose balance is displayed.
     */
    private void concludeGame(Player player) {
        System.out.println("The wheel has stopped spinning.");
        displayBalance(player.getBalance());

    }
    /**
     * Displays the player's balance.
     * @param balance The current balance of the player.
     */
    private void displayBalance(double balance) {
        System.out.println("Your balance is: " + balance);
    }
}
 