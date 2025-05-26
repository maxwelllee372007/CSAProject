package com.github.aakm.interactibleMachines;
import java.util.List;

import com.github.aakm.Player;

public class RouletteBetEvaluator 
{
    public RouletteBetEvaluator(){} 
    public void Evaluate(Player player, List<RouletteBet> bets, RouletteSpinResult result)
    {
        for(RouletteBet bet : bets) {
            this.Evaluate(player, bet, result);            
        }
        return;
    }   
    public void Evaluate(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() == RouletteBetType.notSet)
        {
            return;
        }
        this.EvaluateStraightUp(player, bet, result); 
        this.EvaluateOddsOrEven(player, bet, result);
        this.EvaluateHighOrLow(player, bet, result);
        this.EvaluateRedOrBlack(player, bet, result);
        this.EvaluateDozens(player, bet, result);
    }
    private Double _ChangeInWinnings    = 0.0;
    public Double GetChangeInWinnings()
    {
        return this._ChangeInWinnings;
    }
    private void EvaluateStraightUp(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.StraightUp)
            return;
        
        boolean value = bet.getValue() == result.GetValue();
        if(!value)
        {
            System.out.println("Player lost on a Straight Up Bet for Amount:" + bet.GetAmount() + " due to wrong value...");
            return;
        }
        double payoff = bet.GetAmount() * 36; //origonal bet amont plus 35 times for winning
        this._ChangeInWinnings = this._ChangeInWinnings + payoff;
        player.adjustBalance(payoff);
        System.out.println("Player has won on a Straight Up Bet with a payout:" + payoff + " as the number hit!");
        return;
    }
    private void EvaluateOddsOrEven(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.OddOrEven)
            return;
        boolean value = (bet.getIsOdd() != result.GetIsEven());
        if(!value)
        {
            System.out.println("Player lost on a Odd or Even Bet for Amount:" + bet.GetAmount() + "...");
            return;
        }
        double payoff = bet.GetAmount() * 2; //origonal bet amont plus 1 more time for winning
        this._ChangeInWinnings = this._ChangeInWinnings + payoff;
        player.adjustBalance(payoff);
        System.out.println("Player has won on a Odd or Even Bet with a payout:" + payoff + "....");
        return;
    }
    private void EvaluateHighOrLow(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.HighOrLow)
            return;
        boolean value = (bet.getIsHigh() != result.GetIsLow());
        if(!value)
        {
            System.out.println("Player lost on a High or Low Bet for Amount:" + bet.GetAmount() + "...");
            return;
        }
        double payoff = bet.GetAmount() * 2; //origonal bet amont plus 1 more time for winning
        this._ChangeInWinnings = this._ChangeInWinnings + payoff;
        player.adjustBalance(payoff);
        System.out.println("Player has won on a High or Low Bet with a payout:" + payoff + "...");
        return;
    }
    private void EvaluateRedOrBlack(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.RedOrBlack)
            return;
        boolean value = (bet.getIsBlack() == result.GetIsBlack());        
        if(!value)
        {
            System.out.println("Player lost on a Red or Black Bet for Amount:" + bet.GetAmount() + " as you picked the wrong color...");
            return;
        }
        double payoff = bet.GetAmount() * 2; //origonal bet amont plus 1 more time for winning
        this._ChangeInWinnings = this._ChangeInWinnings + payoff;
        player.adjustBalance(payoff);
        System.out.println("Player has won on a High or Low Bet with a payout:" + payoff + " as you picked the correct color...");
        return;
    }
    private void EvaluateDozens(Player player, RouletteBet bet, RouletteSpinResult result)
    {
        if(bet.GetBetType() != RouletteBetType.Dozens)
            return;        
        boolean value = (bet.getDozens() == result.GetDozens());
        if(!value)
        {
            System.out.println("Player lost on a Dozens Bet for Amount:" + bet.GetAmount() + "...");
            return;
        }
        double payoff = bet.GetAmount() * 3; //origonal bet amont plus 3 more time for winning
        this._ChangeInWinnings = this._ChangeInWinnings + payoff;
        player.adjustBalance(payoff);
        System.out.println("Player has won on a Dozens Bet with a payout:" + payoff + "...");
        return;
    }
}
