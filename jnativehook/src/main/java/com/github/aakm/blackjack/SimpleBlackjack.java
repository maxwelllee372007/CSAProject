package com.github.aakm.blackjack;
import java.util.*;

public class SimpleBlackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        
        List<Integer> playerHand = new ArrayList<>();
        List<Integer> dealerHand = new ArrayList<>();
        
        playerHand.add(drawCard(rand));
        playerHand.add(drawCard(rand));
        dealerHand.add(drawCard(rand));
        dealerHand.add(drawCard(rand));
        
        System.out.println("Welcome to Blackjack!");
        System.out.println("Your hand: " + playerHand + " (Total: " + handValue(playerHand) + ")"); //Displays your hand
        System.out.println("Dealer's visible card: " + dealerHand.get(0)); //Displays dealer's card
        
        while (handValue(playerHand) < 21) {
            System.out.print("Hit or stand? (h/s): "); //Ask whether you want to add cards or not (h or s)
            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("h")) {
                playerHand.add(drawCard(rand));
                System.out.println("Your hand: " + playerHand + " (Total: " + handValue(playerHand) + ")"); //Adds to total
            } else {
                break;
            }
        }
        
        int playerTotal = handValue(playerHand);
        if (playerTotal > 21) {
            System.out.println("You busted! Dealer wins."); //Shows loss result if player's total reaches above 21
            return;
        }
        
        System.out.println("Dealer's hand: " + dealerHand + " (Total: " + handValue(dealerHand) + ")");
        while (handValue(dealerHand) < 17) { 
            dealerHand.add(drawCard(rand));  //adds cards to dealer's hand while their handValue is less than 17.
            System.out.println("Dealer draws: " + dealerHand.get(dealerHand.size() - 1));
        }
        
        int dealerTotal = handValue(dealerHand);
        System.out.println("Dealer's final hand: " + dealerHand + " (Total: " + dealerTotal + ")"); //Shows dealer's final hand and their total
        
        if (dealerTotal > 21 || playerTotal > dealerTotal) {  //condition for Winning
            System.out.println("You win!");
        } else if (playerTotal == dealerTotal) { //condition for a tie result
            System.out.println("It's a tie!"); //loss result
        } else {
            System.out.println("Dealer wins!");
        }
    }
    /**
     * Draws a random card from the deck
     * @param rand (Simulates drawing a card from the deck, picks a random number from 1-13)
     * @return An integer ranging from 1-13, but Jacks, Queens, and Kings are assigned 11-13 but then are given the value 10 after selected.
     */
    public static int drawCard(Random rand) {  
        int card = rand.nextInt(13) + 1;
        return Math.min(card, 10); 
    }
    /**
     * Provides hand value of the player or dealer.
     * @param hand (An ArrayList of the cards in the player's or dealer's hand.)
     * @return The value of the hand (cards combined/added together)
     */
    public static int handValue(List<Integer> hand) {  
        int total = 0;
        int aces = 0;
        for (int card : hand) {
            if (card == 1) {
                aces++;
                total += 11;
            } else {
                total += card;
            }
        }
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }
}
