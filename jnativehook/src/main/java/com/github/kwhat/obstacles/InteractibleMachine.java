package com.github.kwhat.obstacles;

import com.github.kwhat.Player;

public class InteractibleMachine {
    private Box hitBox;
    private InteractionBox interactionBox;
    public InteractibleMachine(Box hitBox, InteractionBox interactionBox) {
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
