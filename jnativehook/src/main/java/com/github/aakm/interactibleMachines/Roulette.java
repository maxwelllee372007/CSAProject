package com.github.aakm.interactibleMachines;


import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.Player;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

import java.util.Scanner;

import org.jnativehook.keyboard.NativeKeyEvent;

public class Roulette extends Machine
{
    private int displayFrame = 0;
    public Roulette(Box hitBox, InteractionBox interactionBox) 
    {
        super(hitBox, interactionBox);
    }
    public boolean getInteractible(Player player) 
    {
        return super.getInteractible(player);
    }
    public Box getCollisionBox() 
    {
        return super.getCollisionBox();
    }
    @Override
    public void interact(Player player, KeyListener keyListener) {
        // TODO: add interrupt functionality
        welcomePlayer(keyListener);
        while (keyListener.getKeys()[KeyBindings.interactKey] || keyListener.getKeys()[KeyBindings.confirmKey]) {
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }
            RouletteBet bet = collectBets(player, keyListener);
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }
            playRoulette(keyListener, player, bet);
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }
            concludeGame(player, keyListener);
        }
        System.out.println("exited roulette game");
    }
    private void welcomePlayer(KeyListener keyListener) {
        System.out.println("Welcome to the roulette machine!");
        System.out.println("Press '" + NativeKeyEvent.getKeyText(KeyBindings.interactKey) + "' to begin."); 
        while (keyListener.getKeys()[KeyBindings.interactKey]) {
            // System.out.println("waiting for player to release interact key");
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
    private RouletteBet collectBets(Player player, KeyListener keyListener) {
        RouletteBet bet = new RouletteBet(player, keyListener);
        
        confirmBetAmount(bet, keyListener);
        if (keyListener.getKeys()[KeyBindings.escapeKey]) {
            System.out.println("Player has escaped the game.");
            return new RouletteBet();
        }
        player.adjustBalance(-bet.GetAmount());
        return bet;
    }
    private void confirmBetAmount(RouletteBet bet, KeyListener keyListener) {
        System.out.println("Roulette costs $" + dollarsdf.format(bet.GetAmount()) + " to play" + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to pay)");
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
    private void playRoulette(KeyListener keyListener, Player player, RouletteBet bet) { 
        double waitTime = Math.random() * 1000.0 + 2000.0; // random wait time between 2 and 3 seconds
        double totalTime = waitTime + 700.0; // total time to spin the wheel
        double startTime = System.currentTimeMillis();
        RouletteSpinResult result = new RouletteSpinResult();
        while (System.currentTimeMillis() - startTime < totalTime) {

            if (System.currentTimeMillis() - startTime < waitTime) {
                spinWheel();
            } else if (System.currentTimeMillis() - startTime < totalTime) {
                displaySpinningWheeelFinal(player, bet, result);
            } else {
                System.out.println("slots timing error");
            }
            
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        System.out.println("Game has ended");
        if (true)
        {
            System.out.println("Winners! +$" + dollarsdf.format(10.0));            
            player.adjustBalance(10.0); //different based on bet            
        }
        else
        {
            System.out.println("You lose!");
        }
        displayBalance(player.getBalance());
    }
    private void spinWheel() {
        // TODO: add display wheel spinner
        System.out.println("Spinning the left wheel at display frame " + displayFrame + "...");
        //red and black alternatating then show value
        if (displayFrame == 2) {
            displayFrame = 0;
        } else {
            displayFrame++;
        }
    }
    private void displaySpinningWheeelFinal(Player player, RouletteBet bet, RouletteSpinResult result) {
        // TODO: add display wheel spinner
        RouletteBetEvaluator evaluator = new RouletteBetEvaluator();
        evaluator.Evaluate(player, bet, result);
        System.out.println("The roulette wheel is " + result);
    }
    private void concludeGame(Player player, KeyListener keyListener) {
        double startTime = System.currentTimeMillis();
        System.out.println("play again? (press '" + NativeKeyEvent.getKeyText(KeyBindings.confirmKey) + "' to play again)" + " (press '" + NativeKeyEvent.getKeyText(KeyBindings.escapeKey) + "' to exit)");
        while (!keyListener.getKeys()[KeyBindings.confirmKey] && System.currentTimeMillis() - startTime < 5000) {
            
            if (keyListener.getKeys()[KeyBindings.escapeKey]) {
                System.out.println("Player has escaped the game.");
                return;
            }
            // System.out.println("waiting for player to press interact key");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void displayBalance(double balance) {
        System.out.println("Your balance is: $" + dollarsdf.format(balance));
    }    
}