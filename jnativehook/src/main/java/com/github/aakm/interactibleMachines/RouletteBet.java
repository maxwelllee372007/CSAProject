package com.github.aakm.interactibleMachines;

import org.jnativehook.keyboard.NativeKeyEvent;

import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.Player;
import com.github.aakm.keyboardTracker.KeyListener;

public class RouletteBet 
{ 
    public static RouletteBet Empty()
    {
        RouletteBet bet = new RouletteBet();
        return bet;
    }
    public static RouletteBet CreateStraightUpBet(double amount, int value)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType     = RouletteBetType.StraightUp;
        bet.amount      = amount;
        bet.Value       = value;
        return bet;
    }
    public static RouletteBet CreateHighOrLowBet(double amount, boolean isHigh)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType     = RouletteBetType.HighOrLow;
        bet.amount      = amount;
        bet.isHigh      = isHigh;
        return bet;
    }
    public static RouletteBet CreateOddOrEvenBet(double amount, boolean isOdd)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType     = RouletteBetType.OddOrEven;
        bet.amount      = amount;
        bet.isOdd      = isOdd;
        return bet;
    }
    public static RouletteBet CreateBlackOrRedBet(double amount, boolean isBlack)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType     = RouletteBetType.RedOrBlack;
        bet.amount      = amount;
        bet.isBlack     = isBlack;
        return bet;
    }
    public static RouletteBet CreateDozensBet(double amount, RouletteDozens dozens)
    {
        RouletteBet bet = new RouletteBet();        
        bet.betType     = RouletteBetType.Dozens;
        bet.amount      = amount;
        bet.dozens      = dozens;
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