# APCSA - Final Project 2024-2025

## Project Abstract: 

Create a top-down rpg pokemon-style virtual casino, where your playable character can walk around and interact with individual machines, with different mini-games. Easily scalable as new minigames can be added as time allows.

NOTE: requires Java 21; you will need to install Java 21 if you do not have it, or the code will not compile. 

## GUI Elements:
Player - 3 frame animation
Generic room
Machines/Minigames (for each game) - 3-4 frame animations and GUIs
 - Blackjack
 - Roulette
 - Slot Machines
     - Background (contains a pop-up window and a slot machine without the spinning wheels)
     - Spinning wheel animation (a few blurred images of a single slots wheel spinning)
     - end image for all cases (picture of a single stationary slots wheel for all of the possible ending positions)
HUD elements - money, time, etc?
Player Class:
 - Tracked stats
 - Account balance (total money remaining, gained, lost)
 - Win rate 
World Class:
 - Moving player based on user input
 - Game Areas

## Sprint Task List:
[AP CSA Final Sprints - Arush, Komron, Maxwell, Aaditya](https://docs.google.com/spreadsheets/d/19JoTXzAoMQJRPFE5i3C_DQpquk25m3sQzjOXDzMlXcg/edit?gid=0#gid=0)


# Phase 2 Proposal: 

Class Hierarchy: 
Main.java
 - KeyboardListener.java - listens for keyboard inputs and updates Keyboard.java correspondingly
   - Keyboard.java - stores keyboard presses; continuously receives updates
 - Machine.java - parent class for all minigames
   - Slots.java - class that stores functionality for Slots
    - instance of Box - slots' hitbox
    - instance of InteractionBox - slots' interaction box
   - Roulette.java - class that stores functionality for Roulette
    - instance of Box - Roulette' hitbox
    - instance of InteractionBox - Roulette' interaction box
   - Blackjack.java - class that stores functionality for Blackjack
    - instance of Box - Blackjack' hitbox
    - instance of InteractionBox - Blackjack' interaction box
- Constants.java - stores project-wide constants
- Player.java - stores player position & budget
- Obstacles.java - stores all obstacles
  - Box.java - single instance of non-enterable obstacle
  - Boundary.java - single instance of non-exitable obstacle
  - InteractionBox.java - single instance of boundary that is used to define where a machine can be interacted from
- instance of GameGUI - displays game room and player
GameGUI.java - used by all machines to output game-specific GUI


By the due date (May 23), we will have real-time player movement inside of a room with obstacles. The player will be shown in a GUI along with the room scenery. The player will be able to interact with machines and play a unique pop-up minigame for each machine. 


Library Demo/Sample: https://github.com/kwhat/jnativehook/blob/2.2/src/main/java/com/github/kwhat/jnativehook/example/NativeHookDemo.java 
