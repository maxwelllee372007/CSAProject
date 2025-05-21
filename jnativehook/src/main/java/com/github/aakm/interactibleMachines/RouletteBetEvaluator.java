package com.github.aakm.interactibleMachines;
import com.github.aakm.Player;

public class RouletteBetEvaluator 
{
    public RouletteBetEvaluator(){}    
    public void Evaluate(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        this.EvaluateStraightUp(player, bet, result); 
        this.EvaluateOddsOrEven(player, bet, result);
        this.EvaluateHighOrLown(player, bet, result);
        this.EvaluateRedOrBlack(player, bet, result);
        this.EvaluateDozens(player, bet, result);
    }

    private void AdjustBalance(Player player, RouletteBet bet, boolean value)
    {
        if(value)
        {   //you win
            player.adjustBalance(bet.GetAmount());
            return;
        }
        //you lose
        player.adjustBalance(-bet.GetAmount());
    }

    private void EvaluateStraightUp(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.StraightUp)
            return;
        
        boolean value =bet.getValue() == result.GetValue();
        this.AdjustBalance(player, bet, value);
    }

    private void EvaluateOddsOrEven(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.OddOrEven)
            return;
        this.AdjustBalance(player, bet, result.GetIsEven());
    }

    private void EvaluateHighOrLown(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.HighOrLow)
            return;
        boolean value = (bet.getIsHigh() != result.GetIsLow());
        this.AdjustBalance(player, bet, value);
    }

    private void EvaluateRedOrBlack(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.RedOrBlack)
            return;
        boolean value = (bet.getIsBlack() == result.GetIsBlack());
        this.AdjustBalance(player, bet, value);
    }

    private void EvaluateDozens(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.Dozens)
            return;        
        boolean value = (bet.getDozens() == result.GetDozens());
        this.AdjustBalance(player, bet, value);
    }
}
