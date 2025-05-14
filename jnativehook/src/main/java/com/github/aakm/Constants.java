package com.github.aakm;

import java.util.ArrayList;

import com.github.aakm.interactibleMachines.Machine;
import com.github.aakm.obstacles.Boundary;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

public class Constants {
    public class KeyBindings {
        public static final int[] playerMovementKeys = {57421, 57419, 57416, 57424}; // right, left, up, down arrow keys
        public static final int interactKey = 57; // spacebar
    }

    public static final double loopTime = 0.01; // Loop time; in seconds
    public static final double playerSpeed = 3.0; // speed of the player's movement; in meters per second

    public static final double[] playerStartingPos = {0.0, 0.0}; // X, Y starting position of player; in meters
    public static final double playerRadius = 0.1; // radial width of player's hitbox and interaction box; in meters

    public static final double collisionResolutionIncrement = 0.01; // increment that program will use to attempt to "unstuck" player from wall; in meters

    // outer boundary
    private static final double[] boundaryCenter = {0.0, 0.0};
    private static final double boundaryWidth = 2.0;
    private static final double boundaryHeight = 2.0;
    public static final Boundary outerBoundary = new Boundary(boundaryCenter, boundaryWidth, boundaryHeight);


    public class Machines {
        private static final double interactibleBuffer = 0.05; // minimum distance from machine to interact; in meters
        public static final ArrayList<Machine> machines = new ArrayList<>();

        // machine 1
        private static final double[] machine1Center = {-0.5, -0.6};
        private static final double machine1Width = 0.5;
        private static final double machine1Height = 0.5;
        private static final double machine1InteractibleWidth = machine1Width + interactibleBuffer;
        private static final double machine1InteractibleHeight = machine1Height + interactibleBuffer;
        private static final Box machine1HitBox = new Box(machine1Center, machine1Width, machine1Height);
        private static final InteractionBox machine1InteractionBox = new InteractionBox(machine1Center, machine1InteractibleWidth, machine1InteractibleHeight);
        private static final Machine machine1 = new Machine(machine1HitBox, machine1InteractionBox);
        static {
            machines.add(machine1);
        }

        // machine 2
        private static final double[] machine2Center = {0.0, 0.25};
        private static final double machine2Width = 0.5;
        private static final double machine2Height = 0.5;
        private static final double machine2InteractibleWidth = machine2Width + interactibleBuffer;
        private static final double machine2InteractibleHeight = machine2Height + interactibleBuffer;
        private static final Box machine2HitBox = new Box(machine2Center, machine2Width, machine2Height);
        private static final InteractionBox machine2InteractionBox = new InteractionBox(machine2Center, machine2InteractibleWidth, machine2InteractibleHeight);
        private static final Machine machine2 = new Machine(machine2HitBox, machine2InteractionBox);
        static {
            machines.add(machine2);
        }
    }

}
