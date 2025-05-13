package com.github.aakm.obstacles;

import com.github.aakm.Player;

public class Boundary extends Box{
    public Boundary(double[] centerPos, double width, double height) {
        super(centerPos, width, height);
    }
    public Boundary(double[] cornerOne, double[] cornerTwo) {
        super(cornerOne, cornerTwo);
    }

    @Override
    public boolean isCollided(double[] playerCenter, double playerRadius) {
        return !super.isCollided(playerCenter, -playerRadius);
        }
    public boolean isCollided(Player player) {
        return isCollided(player.getPos(), player.getRadius());
    }
}
