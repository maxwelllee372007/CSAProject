package com.github.kwhat.obstacles;

import java.util.ArrayList;

import com.github.kwhat.Constants;
import com.github.kwhat.Player;

public class Obstacles {
    private ArrayList<Box> boxes = new ArrayList<>(); 
    private ArrayList<Antibox> antiBoxes = new ArrayList<>(); 
    public void addObstacle(Box box) {
        boxes.add(box);
    }
    public void addObstacle(Antibox antiBox) {
        antiBoxes.add(antiBox);
    }
    public boolean isCollided(double[] playerCenter, double playerRadius) {
        for (Box box : boxes) {
            if (box.isCollided(playerCenter, playerRadius)) {
                return true;
            }
        }
        for (Antibox antiBox : antiBoxes) {
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
            double[] increment = {0.0, 0.0};
            switch (i % 4) {
                case 0:
                    increment[0] = i * Constants.collisionResolutionIncrement;
                    break;
                case 1:
                    increment[1] = i * Constants.collisionResolutionIncrement;
                    break;
                case 2: 
                    increment[0] = -i * Constants.collisionResolutionIncrement;
                    break;
                case 3: 
                    increment[1] = -i * Constants.collisionResolutionIncrement;
                    break;
                default:
                    break;
            }
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
