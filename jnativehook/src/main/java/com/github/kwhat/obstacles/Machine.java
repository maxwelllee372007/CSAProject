package com.github.kwhat.obstacles;

import com.github.kwhat.Player;

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
}
