package com.github.aakm;

import java.util.ArrayList;

import com.github.aakm.interactibleMachines.Machine;
import com.github.aakm.interactibleMachines.Roulette;
import com.github.aakm.obstacles.Boundary;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;

public class Constants {
    public class KeyBindings {
        public static final int[] playerMovementKeys = {57421, 57419, 57416, 57424}; // right, left, up, down arrow keys
        public static final int interactKey = 57; // spacebar

        public static final int confirmKey = 28; // enter key
        public static final int deleteKey = 14; // backspace key

        // roulette machine
        public static final int redKey = 2; // 1 key
        public static final int blackKey = 3; // 2 key
        public static final int greenKey = 4; // 3 key
    }
    public static final double playerStartingBalance = 100.0; // starting balance of player; in dollars

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

        // machine 1: roulette
        private static final double[] rouletteCenter = {-0.5, -0.6};
        private static final double rouletteWidth = 0.5;
        private static final double rouletteHeight = 0.5;
        private static final double rouletteInteractibleWidth = rouletteWidth + interactibleBuffer;
        private static final double rouletteInteractibleHeight = rouletteHeight + interactibleBuffer;
        private static final Box rouletteHitBox = new Box(rouletteCenter, rouletteWidth, rouletteHeight);
        private static final InteractionBox rouletteInteractionBox = new InteractionBox(rouletteCenter, rouletteInteractibleWidth, rouletteInteractibleHeight);
        private static final Roulette roulette = new Roulette(rouletteHitBox, rouletteInteractionBox);
        static {
            machines.add(roulette);
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
