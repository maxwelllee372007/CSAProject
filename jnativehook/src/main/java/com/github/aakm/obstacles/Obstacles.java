package com.github.aakm.obstacles;

import java.util.ArrayList;

import com.github.aakm.Constants;
import com.github.aakm.Player;

public class Obstacles {
    private ArrayList<Box> boxes = new ArrayList<>(); 
    private ArrayList<Boundary> antiBoxes = new ArrayList<>(); 
    public void addObstacle(Box box) {
        boxes.add(box);
    }
    public void addObstacle(Boundary antiBox) {
        antiBoxes.add(antiBox);
    }
    public boolean isCollided(double[] playerCenter, double playerRadius) {
        for (Box box : boxes) {
            if (box.isCollided(playerCenter, playerRadius)) {
                return true;
            }
        }
        for (Boundary antiBox : antiBoxes) {
            if (antiBox.isCollided(playerCenter, playerRadius)) {
                return true;
            }
        }
        return false;
    }
    public boolean isCollided(Player player) {
        return isCollided(player.getPos(), player.getRadius());
    }

    public double[] resolveCollisions(Player player) {
        double[] startingPos = player.getPos();
        int i = 0;
        while (isCollided(player) && i < 1000) {
            double angle = (i % 8) * (Math.PI / 4); // 8 directions, 45 degrees apart
            double distance = i/8 * Constants.collisionResolutionIncrement;
            double[] increment = {distance * Math.cos(angle), distance * Math.sin(angle)};
            double[] newPos = {startingPos[0] + increment[0], startingPos[1] + increment[1]};
            i++;
            if (!isCollided(newPos, player.getRadius())) {
                player.movePlayer(increment);
                startingPos = player.getPos();
                i = 0;
            }
        }
        if (i == 1000) {
            System.out.println("collision resolution loop overrun");
        }
        return player.getPos();
    }

}
