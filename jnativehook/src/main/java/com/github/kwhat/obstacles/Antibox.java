package com.github.kwhat.obstacles;

public class Antibox extends Box{
    public Antibox(double[] centerPos, double width, double height) {
        super(centerPos, width, height);
    }
    public Antibox(double[] cornerOne, double[] cornerTwo) {
        super(cornerOne, cornerTwo);
    }

    @Override
    public boolean isCollided(double[] playerCenter, double playerRadius) {
        return !super.isCollided(playerCenter, playerRadius);
    }
}
