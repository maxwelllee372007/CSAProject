package com.github.aakm.interactibleMachines;

import java.text.DecimalFormat;

import com.github.aakm.Player;
import com.github.aakm.keyboardTracker.KeyListener;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

public class Machine {
    public static DecimalFormat dollarsdf = new DecimalFormat("#.##");
    private Box hitBox;
    private InteractionBox interactionBox;
    public Machine(Box hitBox, InteractionBox interactionBox) {
        dollarsdf.setDecimalSeparatorAlwaysShown(true);
        dollarsdf.setMinimumFractionDigits(2);
        this.hitBox = hitBox;
        this.interactionBox = interactionBox;
    }
    /**
     * Checks if the player is within the interaction box of the machine.
     * @param player The player to check for interaction.
     * @return true if the player can interact with the machine, false otherwise.
     */
    public boolean getInteractible(Player player) {
        return interactionBox.isCollided(player);
    }
    /**
     * Returns the collision box of the machine.
     * @return The Box object representing the collision area of the machine.
     */
    public Box getCollisionBox() {
        return hitBox;
    }
    /**
     * Interacts with the machine. This method should be overridden by subclasses to implement specific interaction behavior.
     * @param player The player interacting with the machine.
     * @param keyListener The KeyListener to track key events during interaction.
     */
    public void interact(Player player, KeyListener keyListener) {
    }
}
