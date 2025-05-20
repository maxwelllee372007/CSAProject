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
            this.isEven = ((value % 2) == 0);
            this.setDozens(value);
            this.setIsBlack(value);
            this.isLow = value < 19;
            this.isValid = true;
        }

        catch(Exception ex)
        {
            Logger.getLogger(ex.getMessage());
        }
    }

    private void setDozens(Integer value)
    {       
        if(value < 13)
        {    
            this.dozens = RouletteDozens.Low;
            return;
        }
        if(value > 12 && value < 25)
        {
            this.dozens = RouletteDozens.Middle;
            return;
        }
        this.dozens = RouletteDozens.High;
    }

    private void setIsBlack(Integer value)
    {        
        for(int index = 0; index < 17; index++)
        {
            if(blackNum[index] == value)
            {
                this.isBlack = true;
                break;                    
            }
        }
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