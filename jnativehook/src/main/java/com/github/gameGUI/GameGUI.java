package com.github.gameGUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.io.File;
import javax.imageio.ImageIO;

public class GameGUI extends JComponent{
    private static int WIDTH = 500;
    private static int HEIGHT = 500;

    int px = 250;
    int py = 250;
    
    private Image bgImage;
    private Image player;

    private Point playerLoc;

    private JFrame frame;

    public GameGUI(){
        //player location
        playerLoc = new Point(px,py);

        //game frame
        frame = new JFrame();
        frame.setTitle("Virtual Casino");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
