package com.github.aakm.interactibleMachines;

import java.text.DecimalFormat;

import com.github.aakm.Player;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

public class Machine {
    public DecimalFormat dollarsdf = new DecimalFormat("#.##");
    private Box hitBox;
    private InteractionBox interactionBox;
    public Machine(Box hitBox, InteractionBox interactionBox) {
        dollarsdf.setDecimalSeparatorAlwaysShown(true);
        dollarsdf.setMinimumFractionDigits(2);
        this.hitBox = hitBox;
        this.interactionBox = interactionBox;
    }
    public boolean getInteractible(Player player) {
        return interactionBox.isCollided(player);
    }
    public Box getCollisionBox() {
        return hitBox;
    }
    public void interact(Player player, KeyListener keyListener) {
        // TODO: add generic machine interaction behavior
    }
}
