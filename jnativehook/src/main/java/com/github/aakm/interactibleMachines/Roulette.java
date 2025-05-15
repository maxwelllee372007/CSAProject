package com.github.aakm.interactibleMachines;

import com.github.aakm.Player;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

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


    public void interact() 
    {
        int spinIndex = (int) (Math.random() * 38); // 0 to 37

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
        System.out.println("Roulette spun and landed on: " + result + " (" + color + ")");
    }

    
}
