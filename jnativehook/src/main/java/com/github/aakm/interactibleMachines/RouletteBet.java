package com.github.aakm.interactibleMachines;

import org.jnativehook.keyboard.NativeKeyEvent;

import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.Player;
import com.github.aakm.keyboardTracker.KeyListener;
import java.util.Scanner;


public class RouletteBet 
{ 
    public static RouletteBet Empty()
    {
        RouletteBet bet = new RouletteBet();
        return bet;
    }
    public static RouletteBet InputBetFromKeyboard()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter bet amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Enter a number to select a bet type 1)Straight Up, 2) High Low, 3) Odd Even, 4)Color, 5) Dozens Number ");
        Integer betType = scanner.nextInt();
        if(betType < 1 || betType > 5)
        {
            System.out.println("Invalid Bet Type selection value of:" +  betType + "! Please select a number between 1-5!");
            return new RouletteBet();
        }
        switch(betType)
        {
            case 1:
                return RouletteBet.InputStraightUpBet(scanner, amount);
            case 2:
                return RouletteBet.InputHighOrLowBet(scanner, amount);
            case 3:
                return RouletteBet.InputOddOrEvenBet(scanner, amount);
            case 4:
                return RouletteBet.InputBlackOrRedBet(scanner, amount);
            case 5:
                return RouletteBet.InputDozensBet(scanner, amount);
        }
        return new RouletteBet();
    }
    public static RouletteBet InputStraightUpBet(Scanner scanner, double amount)
    {
        System.out.println("Player has seltected a Straight Up Bet!");
        System.out.println("Please input the value to bet for your Straight Up Wager between 1-36!");
        Integer value = scanner.nextInt();
        if(value < 1 || value > 36)
        {
            System.out.println("Invalid bet valie! Please select a number between 1-36!");
            return new RouletteBet();
        }
        return RouletteBet.CreateStraightUpBet(amount, value);
    }
    public static RouletteBet CreateStraightUpBet(double amount, int value)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType = RouletteBetType.StraightUp;
        bet.amount = amount;
        bet.Value = value;
        return bet;
    }
    public static RouletteBet InputHighOrLowBet(Scanner scanner, double amount)
    {
        System.out.println("Player has selected a Straight Up Bet!");
        System.out.println("Please input the value to bet for your Straight Up Wager between 1-36!");
        Integer value = scanner.nextInt();
        if(value < 1 || value > 36)
        {
            System.out.println("Invalid bet valie! Please select a number between 1-36!");
            return new RouletteBet();
        }
        return RouletteBet.CreateHighOrLowBet(amount, true);
    }
    public static RouletteBet CreateHighOrLowBet(double amount, boolean isHigh)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType = RouletteBetType.HighOrLow;
        bet.amount = amount;
        bet.isHigh = isHigh;
        return bet;
    }
    public static RouletteBet InputOddOrEvenBet(Scanner scanner, double amount)
    {        
        System.out.println("Player has selected a Odd Or Even Bet!");
        System.out.println("Please input an O for Odds or any other letter for Events!");
        String value = scanner.next();
        if(value.trim().compareToIgnoreCase("o") == 0)
        {
            return RouletteBet.CreateOddOrEvenBet(amount, true);
        }
        return RouletteBet.CreateOddOrEvenBet(amount, false);
    }
    public static RouletteBet CreateOddOrEvenBet(double amount, boolean isOdd)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType = RouletteBetType.OddOrEven;
        bet.amount = amount;
        bet.isOdd = isOdd;
        return bet;
    }
    public static RouletteBet InputBlackOrRedBet(Scanner scanner, double amount)
    {
        //TODO:Convert to even odd bet
        System.out.println("Player has seltected a Straight Up Bet!");
        System.out.println("Please input the value to bet for your Straight Up Wager between 1-36!");
        Integer value = scanner.nextInt();
        if(value < 1 || value > 36)
        {
            System.out.println("Invalid bet valie! Please select a number between 1-36!");
            return new RouletteBet();
        }
        return RouletteBet.CreateBlackOrRedBet(amount, true);
    }
    public static RouletteBet CreateBlackOrRedBet(double amount, boolean isBlack)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType = RouletteBetType.RedOrBlack;
        bet.amount  = amount;
        bet.isBlack = isBlack;
        return bet;
    }
    public static RouletteBet InputDozensBet(Scanner scanner, double amount)
    {
        //TODO:Convert to even odd bet
        System.out.println("Player has seltected a Straight Up Bet!");
        System.out.println("Please input the value to bet for your Straight Up Wager between 1-36!");
        Integer value = scanner.nextInt();
        if(value < 1 || value > 36)
        {
            System.out.println("Invalid bet valie! Please select a number between 1-36!");
            return new RouletteBet();
        }
        return RouletteBet.CreateDozensBet(amount, RouletteDozens.High);
    }
    public static RouletteBet CreateDozensBet(double amount, RouletteDozens dozens)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType = RouletteBetType.Dozens;
        bet.amount = amount;
        bet.dozens = dozens;
        return bet;
    }
    private RouletteBet(){}
    private double amount = 0;
    public double GetAmount()
    {
        return this.amount;
    }
    private RouletteBetType betType   = RouletteBetType.notSet;
    public RouletteBetType GetBetType()
    {
        return this.betType;
    }
    private int Value = 0;
    public int getValue()
    {
        return this.Value;
    } 
    private RouletteDozens dozens       = RouletteDozens.notSet;
    public RouletteDozens getDozens()
    {
        return this.dozens;
    }
    private boolean isHigh = false;
    public boolean getIsHigh()
    {
        return this.isHigh;        
    }
    private boolean isOdd = false;
    public boolean getIsOdd()
    {
        return this.isOdd;        
    }
    private boolean isBlack = false;
    public boolean getIsBlack()
    {
        return this.isBlack;        
    }
}