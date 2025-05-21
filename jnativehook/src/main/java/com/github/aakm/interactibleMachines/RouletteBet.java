package com.github.aakm.interactibleMachines;

import org.jnativehook.keyboard.NativeKeyEvent;

import com.github.aakm.Constants.KeyBindings;
import com.github.aakm.Player;
import com.github.aakm.keyboardTracker.KeyListener;

public class RouletteBet 
{    
    public RouletteBet(){}
    public RouletteBet(Player player, KeyListener keyListener)
    {
        this.amount = 1.0;
        //TODO:write key listener key to create a bet.
        //set amount
        //set type
        //set value if straight up
        //set DozensType if Dozens
        //High or Low
        //Black or Red
    }
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
    public void setValue(int value)
    {
        if(value < 1)
            return;
        if(value > 36)
            return;
        this.Value = value;
    }

    public int getValue()
    {
        return this.Value;
    } 

    private RouletteDozens dozens = RouletteDozens.notSet;
    public RouletteDozens getDozens()
    {
        return this.dozens;
    }

    private boolean isHigh = false;
    public boolean getIsHigh()
    {
        return this.isHigh;        
    }    

    private boolean isBlack = false;
    public boolean getIsBlack()
    {
        return this.isBlack;        
    }
}
