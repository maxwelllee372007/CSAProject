package com.github.aakm.interactibleMachines;
import java.util.logging.Logger;


public class RouletteSpinResult
{
    private static Integer[] blackNum = new Integer[]{26,15,4,2,17,6,13,11,8,10,24,33,20,31,22,29,28,35};
    public RouletteSpinResult()
    {
        try
        {
            int value = (int)(Math.random() * 36) + 1;
            if(value < 1 || value > 36)
            {
                Logger.getLogger("Number out of range!");
                return;
            }
            this.Value = value;
            System.out.println("The ball fell on " + value + ".");
       
            this.isEven = ((value % 2) == 0);
            System.out.println("IsEven:" + this.isEven + ".");


            this.setDozens(value);
            this.setIsBlack(value);


            this.isLow = value < 19;            
            System.out.println("IsLow:" + this.isLow + ".");


            this.isValid = true;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private void setDozens(Integer value)
    {      
        if(value < 13)
        {    
            System.out.println("Dozens is Low!");
            this.dozens = RouletteDozens.Low;
            return;
        }
        if(value > 12 && value < 25)
        {
            System.out.println("Dozens is Middle!");
            this.dozens = RouletteDozens.Middle;
            return;
        }
        System.out.println("Dozens is High!");
        this.dozens = RouletteDozens.High;
    }
    private void setIsBlack(Integer value)
    {        
        for(int index = 0; index < 18; index++)
        {
            if(blackNum[index] == value)
            {
                System.out.println("Your Color is Black!");
                this.isBlack = true;
                return;                    
            }
        }
        System.out.println("Your Color is Red!");
        this.isBlack = false;
    }    
    private Boolean isValid = false;
    public Boolean GetIsValid()
    {
        return this.isValid;    
    }
    private Integer Value = 0;
    public Integer GetValue()
    {
        return this.Value;    
    }
    private Boolean isEven = false;
    public Boolean GetIsEven()
    {
        return this.isEven;    
    }
    private Boolean isBlack = false;
    public Boolean GetIsBlack()
    {
        return this.isBlack;    
    }
    private Boolean isLow = false;
    public Boolean GetIsLow()
    {
        return this.isLow;    
    }
    private RouletteDozens dozens = RouletteDozens.notSet;
    public RouletteDozens GetDozens()
    {
        return this.dozens;    
    }
}


