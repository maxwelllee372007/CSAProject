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
System.out.println("Your hand: " + playerHand + " (Total: " + handValue(playerHand) + ")");
System.out.println("Dealer's visible card: " + dealerHand.get(0));

while (handValue(playerHand) < 21) {
System.out.print("Hit or stand? (h/s): ");
String action = scanner.nextLine();
if (action.equalsIgnoreCase("h")) {
playerHand.add(drawCard(rand));
System.out.println("Your hand: " + playerHand + " (Total: " + handValue(playerHand) + ")");
} else {
break;
}
}

int playerTotal = handValue(playerHand);
if (playerTotal > 21) {
System.out.println("You busted! Dealer wins.");
return;
}

System.out.println("Dealer's hand: " + dealerHand + " (Total: " + handValue(dealerHand) + ")");
while (handValue(dealerHand) < 17) {
dealerHand.add(drawCard(rand));
System.out.println("Dealer draws: " + dealerHand.get(dealerHand.size() - 1));
}

int dealerTotal = handValue(dealerHand);
System.out.println("Dealer's final hand: " + dealerHand + " (Total: " + dealerTotal + ")");

if (dealerTotal > 21 || playerTotal > dealerTotal) {
System.out.println("You win!");
} else if (playerTotal == dealerTotal) {
System.out.println("It's a tie!");
} else {
System.out.println("Dealer wins!");
}
}

static int drawCard(Random rand) {
int card = rand.nextInt(13) + 1;
return Math.min(card, 10); 
}

static int handValue(List<Integer> hand) {
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
