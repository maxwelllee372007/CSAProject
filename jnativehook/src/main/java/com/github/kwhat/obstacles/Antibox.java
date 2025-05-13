package com.github.kwhat.obstacles;

import com.github.kwhat.Player;

public class Antibox extends Box{
    // private double[] centerPos = new double[2];
    // private double width; // X width
    // private double height; // Y height
    public Antibox(double[] centerPos, double width, double height) {
        super(centerPos, width, height);
        // this.centerPos = centerPos;
        // this.width = width;
        // this.height = height;
    }
    public Antibox(double[] cornerOne, double[] cornerTwo) {
        super(cornerOne, cornerTwo);
        // this.centerPos[0] = (cornerOne[0] + cornerTwo[0]) * 0.5;
        // this.centerPos[1] = (cornerOne[1] + cornerTwo[1]) * 0.5;
        // this.width = Math.abs(cornerOne[0] - cornerTwo[0]);
        // this.height = Math.abs(cornerOne[1] - cornerTwo[1]);
    }

    @Override
    public boolean isCollided(double[] playerCenter, double playerRadius) {
        // boolean xInBoundsLeft = playerCenter[0] - playerRadius > centerPos[0] - (width * 0.5); // whether player is intersecting with the infinitely-long left wall
        // boolean xInBoundsRight = playerCenter[0] + playerRadius < centerPos[0] + (width * 0.5); // whether player is intersecting with the infinitely-long right wall
        // boolean xInBounds = xInBoundsLeft && xInBoundsRight; // player is intersecting with X direction of Box iff player is in between left and right walls
        // boolean yInBoundsBottom = playerCenter[1] - playerRadius > centerPos[1] - (height * 0.5); 
        // boolean yInBoundsTop = playerCenter[1] + playerRadius < centerPos[1] + (height * 0.5); 
        // boolean yInBounds = yInBoundsBottom && yInBoundsTop; 
        // return !(xInBounds && yInBounds);
        return !super.isCollided(playerCenter, -playerRadius);
        }
    public boolean isCollided(Player player) {
        return isCollided(player.getPos(), player.getRadius());
    }
}
