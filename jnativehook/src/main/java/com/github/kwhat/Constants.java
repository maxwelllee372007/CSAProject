package com.github.kwhat;

import java.util.ArrayList;

import com.github.kwhat.obstacles.Boundary;
import com.github.kwhat.obstacles.Box;
import com.github.kwhat.obstacles.Machine;
import com.github.kwhat.obstacles.InteractionBox;

public class Constants {
    public static final double loopTime = 0.01; // Loop time of 0.1 seconds
    public static final double playerSpeed = 3.0; // speed of the player's movement, in meters per second
    public static final int[] playerMovementKeys = {57421, 57419, 57416, 57424}; // right, left, up, down arrow keys

    public static final double[] playerStartingPos = {0.0, 0.0}; // meters
    public static final double playerRadius = 0.15; // meters

    public static final double collisionResolutionIncrement = 0.001; // meters

    // outer boundary
    private static final double[] boundaryCenter = {0.0, 0.0};
    private static final double boundaryWidth = 2.0;
    private static final double boundaryHeight = 2.0;
    public static final Boundary outerBoundary = new Boundary(boundaryCenter, boundaryWidth, boundaryHeight);


    // machines
    private static final double interactibleBuffer = 0.05; // minimum distance from machine to interact
    public static final ArrayList<Machine> machines = new ArrayList<>();

    // machine 1
    private static final double[] machine1Center = {-0.3, -0.3};
    private static final double machine1Width = 0.3;
    private static final double machine1Height = 0.3;
    private static final double machine1InteractibleWidth = machine1Width + interactibleBuffer;
    private static final double machine1InteractibleHeight = machine1Height + interactibleBuffer;
    private static final Box machine1HitBox = new Box(machine1Center, machine1Width, machine1Height);
    private static final InteractionBox machine1InteractionBox = new InteractionBox(machine1Center, machine1InteractibleWidth, machine1InteractibleHeight);
    private static final Machine machine1 = new Machine(machine1HitBox, machine1InteractionBox);
    static {
        machines.add(machine1);
    }

    // machine 2
    private static final double[] machine2Center = {0.0, 0.3};
    private static final double machine2Width = 0.3;
    private static final double machine2Height = 0.3;
    private static final double machine2InteractibleWidth = machine2Width + interactibleBuffer;
    private static final double machine2InteractibleHeight = machine2Height + interactibleBuffer;
    private static final Box machine2HitBox = new Box(machine2Center, machine2Width, machine2Height);
    private static final InteractionBox machine2InteractionBox = new InteractionBox(machine2Center, machine2InteractibleWidth, machine2InteractibleHeight);
    private static final Machine machine2 = new Machine(machine2HitBox, machine2InteractionBox);
    static {
        machines.add(machine2);
    }


}
