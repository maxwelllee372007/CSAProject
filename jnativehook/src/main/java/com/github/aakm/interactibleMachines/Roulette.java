package com.github.aakm.interactibleMachines;

import com.github.aakm.Player;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

import java.util.Scanner;




public class Roulette extends Machine
{

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

    private String getBetType(String betType)
    {
        if(betType == "Straight Up"|| betType == "SU")
        {
            return "Straight Up";
        }

        if(betType == "Red/Black"|| betType == "R/B")
        {
            if(betType == "Red"|| betType == "R")
            {
                return "Red";
            }

            if(betType == "Black"|| betType == "B")
            {
                return "Black";
            }
        }

        if(betType == "Odd/Even"|| betType == "O/D")
        {
            if(betType == "Odd"|| betType == "O")
            {
                return "Red";
            }

            if(betType == "Even"|| betType == "E")
            {
                return "Even";
            }
        }

        if(betType == "High/Low"|| betType == "SU")
        {
            if(betType == "High"|| betType == "H")
            {
                return "Red";
            }

            if(betType == "Low"|| betType == "L")
            {
                String betTypeSelect = "Low";
                return "Black";
            }
        }

        if(betType == "High/Low"|| betType == "SU")
        {
            if(betType == "High"|| betType == "H")
            {
                return "Red";
            }

            if(betType == "Low"|| betType == "L")
            {
                String betTypeSelect = "Low";
                return "Black";
            }
        }    
    }


    private String getColor(String result) 
    {
        if (result.equals("0") || result.equals("00")) 
        {
            return "Green";
        }

        int number = Integer.parseInt(result);
        // Red numbers in American roulette
        int[] reds = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

        for (int red : reds) 
        {
            if (number == red) 
            {
                return "Red";
            }
        }
        return "Black";
    }

  

    private int getBetAmount(String betAmount) {
    String[] parts = betAmount.split(" ");
    if (parts.length == 2) 
    { 
        try 
        {
            return Integer.parseInt(parts[1]);
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid bet amount.");
        }
    }
    return 0; // Default or error value
}


    private int getWinOrLose(String result, String betType, int betAmount) 
    {    
        if (betType.equalsIgnoreCase("Red") || betType.equalsIgnoreCase("Black") || betType.equalsIgnoreCase("Green")) 
        {
            String outcomeColor = getColor(result);
            if (betType.equalsIgnoreCase(outcomeColor)) 
            {
                return betAmount * 2; // Simple double money back
            }
        } 
        
        else if (betType.equals(result)) 
        {
            return betAmount * 36; // Straight number bet
        }

        return 0; // Loss
}



    @Override
    public void interact(Player player, KeyListener keyListener) 
    {
        
        int spinIndex = (int) (Math.random() * 38); // 0 to 37

        Scanner bet = new Scanner(System.in);
        System.out.println("Enter the amount that you want to bet at the roulette table: ");

        Scanner betType = new Scanner(System.in);
        System.out.println("Enter the amount that you want to bet at the roulette table: ");

        Scanner betAmount = new Scanner(System.in);
        System.out.println("Enter the amount that you want to bet at the roulette table: ");

        String result;
        if (spinIndex == 36) 
        {
            result = "0";
        } 
        
        else if (spinIndex == 37) 
        {
            result = "00";
        }

        else 
        {
            result = Integer.toString(spinIndex + 1); // 1 to 36
        }

        String color = getColor(result);
        int winOrLose = getWinOrLose(result, color, betAmount);

        System.out.println("Roulette spun and landed on: " + result + " (" + color + ")");

        bet.close();
    }


    
}
