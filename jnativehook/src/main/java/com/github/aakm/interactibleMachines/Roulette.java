package com.github.aakm.interactibleMachines;

import com.github.aakm.Player;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

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
