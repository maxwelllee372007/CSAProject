package com.github.aakm.interactibleMachines;

import com.github.aakm.Player;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

public class Machine {
    private Box hitBox;
    private InteractionBox interactionBox;
    public Machine(Box hitBox, InteractionBox interactionBox) {
        this.hitBox = hitBox;
        this.interactionBox = interactionBox;
    }
    public boolean getInteractible(Player player) {
        return interactionBox.isCollided(player);
    }
    public Box getCollisionBox() {
        return hitBox;
    }
    public void interact() {
        // TODO: add
    }
}
