package com.github.aakm;

public class Player 
{
    private double[] pos = new double[2]; 
    private double radius = Constants.playerRadius;
    private double balance = Constants.playerStartingBalance;
    
    public Player(double[] startingPos) {
        pos[0] = startingPos[0];
        pos[1] = startingPos[1];
    }
    public Player(double[] startingPos, double radius) {
        pos[0] = startingPos[0];
        pos[1] = startingPos[1];
        this.radius = radius;
    }

    public double[] movePlayer(double[] movement) {
        pos[0] += movement[0];
        pos[1] += movement[1];
        return pos;
    }
    public void teleportPlayer(double[] position) {
        pos[0] = position[0];
        pos[1] = position[1];
    }

    public double[] getPos() {
        return pos;
    }
    public double getRadius() {
        return radius;
    }

    public String getRace() {
        return "black";
    }

    public double adjustBalance(double amount) {   
        return balance += amount;
    }

    public double getBalance() {
        return balance;
    }

}
