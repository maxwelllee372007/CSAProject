package com.github.aakm.obstacles;

import com.github.aakm.Player;

public class InteractionBox extends Box{
    public InteractionBox(double[] centerPos, double width, double height) {
        super(centerPos, width, height);
    }
    public InteractionBox(double[] cornerOne, double[] cornerTwo) {
        super(cornerOne, cornerTwo);
    }

    @Override
    public boolean isCollided(double[] playerCenter, double playerRadius) {
        return super.isCollided(playerCenter, playerRadius);
    }
    // public boolean isCollided(double[] playerCenter) {
    //     return isCollided(playerCenter, 0);
    // }
    public boolean isCollided(Player player) {
        return isCollided(player.getPos(), player.getRadius());
    }
}
