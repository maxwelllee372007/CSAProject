package com.github.aakm;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jnativehook.NativeHookException;

import com.github.aakm.interactibleMachines.Machine;
import com.github.aakm.interactibleMachines.Roulette;
import com.github.aakm.interactibleMachines.Slots;
import com.github.aakm.obstacles.Boundary;
import com.github.aakm.obstacles.Box;
import com.github.aakm.obstacles.InteractionBox;
import com.github.gameGUI.GameGUI;

public class Constants {
    public static final String currentDirectory = System.getProperty("user.dir");
    public static final String imageFolder = currentDirectory + "\\jnativehook\\src\\main\\java\\com\\github\\gameGUI\\images\\";
    
    
    public static final String playerImagePath = imageFolder + "playerClear.png";
    public static final String playerImagePathStep = imageFolder + "playerClearStep.png";
    public static final String playerImagePathLeft = imageFolder + "playerClearLeft.png";
    public static final String playerImagePathStepLeft = imageFolder + "playerClearStepLeft.png";
    public static final String backgroundImagePath = imageFolder + "betterBG.png";
    public static final String reelSpin1 = imageFolder + "reelSpin1.png";
    public static final String reelSpin2 = imageFolder + "reelSpin2.png";
    public static final String reelSpin3 = imageFolder + "reelSpin3.png";
    public static final String reelEnd1 = imageFolder + "reelEnd1.png";
    public static final String reelEnd2 = imageFolder + "reelEnd2.png";
    public static final String reelEnd3 = imageFolder + "reelEnd3.png";

    public static final File pixelFontFile = new File("C:\\Users\\Arush\\Documents\\CSAProject\\jnativehook\\src\\main\\java\\com\\github\\aakm\\VCR_OSD_MONO_1.001.ttf");
    public static Font pixelFont = new Font(null);
    static{
    try{
        pixelFont = Font.createFont(Font.TRUETYPE_FONT, pixelFontFile);
    }
    catch(Exception e){
        System.out.println("no font for u");
    }
    }
    public static final Font slotsFont = pixelFont.deriveFont(24f);

    public class KeyBindings {
        public static final int[] playerMovementKeys = {57421, 57419, 57416, 57424}; // right, left, up, down arrow keys
        public static final int interactKey = 57; // spacebar

        public static final int confirmKey = 28; // enter key
        public static final int deleteKey = 14; // backspace key

        public static final int escapeKey = 1; // escape key
        public static final int controlKey = 29; // ctrl key

        // roulette machine
        public static final int redKey = 2; // 1 key
        public static final int blackKey = 3; // 2 key
        public static final int greenKey = 4; // 3 key
    }

    public class InteractPrompt {
        public static final String interactPromptImagePath = imageFolder + "interactPrompt.png";
        public static final int[] interactPromptPos = {430, 760}; // X, Y position of interact prompt; in pixels
        public static final int[] interactPromptSize = {300, 50}; // width, height of interact prompt; in pixels
    }
    public static final double playerStartingBalance = 100.0; // starting balance of player; in dollars

    public static final double loopTime = 0.01; // Loop time; in seconds
    public static final double playerSpeed = 0.8; // speed of the player's movement; in meters per second
    public static final double playerStepFrequency = 0.4; // amount of time to animate a full cycle of player stepping; in seconds

    public static final double[] playerStartingPos = {0.0, 0.0}; // X, Y starting position of player; in meters
    public static final double playerRadius = 0.1; // radial width of player's hitbox and interaction box; in meters

    public static final double collisionResolutionIncrement = 0.0025; // increment that program will use to attempt to "unstuck" player from wall; in meters

    // outer boundary
    private static final double[] boundaryCenter = {0.0, 0.0};
    private static final double boundaryWidth = 2.0;
    private static final double boundaryHeight = 2.0;
    public static final Boundary outerBoundary = new Boundary(boundaryCenter, boundaryWidth, boundaryHeight);
    public static final int borderThickness = 40; // pixels

    public class Machines {
        private static final double interactibleBuffer = 0.05; // minimum distance from machine to interact; in meters
        public static final ArrayList<Machine> machines = new ArrayList<>();
        public static final ArrayList<Icon> machineIcons = new ArrayList<>();

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
            machineIcons.add(new ImageIcon(imageFolder + "roulette.png"));
        }

        // machine 2: slots
        private static final double[] slotsCenter = {0.0, 0.4};
        private static final double slotsWidth = GameGUI.scaleToGameMeters(171);
        private static final double slotsHeight = GameGUI.scaleToGameMeters(224);
        private static final double slotsInteractibleWidth = slotsWidth + interactibleBuffer;
        private static final double slotsInteractibleHeight = slotsHeight + interactibleBuffer;
        private static final Box slotsHitBox = new Box(slotsCenter, slotsWidth, slotsHeight);
        private static final InteractionBox slotsInteractionBox = new InteractionBox(slotsCenter, slotsInteractibleWidth, slotsInteractibleHeight);
        private static final Slots slots = new Slots(slotsHitBox, slotsInteractionBox);
        public static final String slotsPopUpImagePath = imageFolder + "slotsBGMini-removebg-preview.png";
        static {
            machines.add(slots);
            machineIcons.add(new ImageIcon(imageFolder + "ChatGPT_Image_May_16__2025__01_19_37_PM.png"));

        }
    }

}
