package com.github.kwhat.interactibleMachines;

import com.github.kwhat.Player;
import com.github.kwhat.obstacles.Box;
import com.github.kwhat.obstacles.InteractionBox;

public class Roulette extends Machine{

    public Roulette(Box hitBox, InteractionBox interactionBox) {
        super(hitBox, interactionBox);
    }
    public boolean getInteractible(Player player) {
        return super.getInteractible(player);
    }
    public Box getCollisionBox() {
        return super.getCollisionBox();
    }

    @Override
    public void interact() {
        // TODO: add
    }
}
