package com.github.kwhat;

public class Player {
    private double[] pos = new double[2]; 
    
    public Player(double[] startingPos) {
        pos[0] = startingPos[0];
        pos[1] = startingPos[1];
    }

    public double[] movePlayer(double[] movement) {
        pos[0] += movement[0];
        pos[1] += movement[1];
        return pos;
    }
    public void resetPlayer(double[] position) {
        pos[0] = position[0];
        pos[1] = position[1];
    }

    public double[] getPos() {
        return pos;
    }

}
