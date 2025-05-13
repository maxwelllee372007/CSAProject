package com.github.kwhat.interactibleMachines;

import com.github.kwhat.Player;
import com.github.kwhat.obstacles.Box;
import com.github.kwhat.obstacles.InteractionBox;

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
