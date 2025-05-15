package com.github.gameGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;
import javax.imageio.ImageIO;

public class GameGUI extends JComponent{
    private static int WIDTH = 1000;
    private static int HEIGHT = 1000;

    int px = 500;
    int py = 500;
    
    private ImageIcon bgImage;
    private ImageIcon playerImage;

    private Point playerLoc;

    private JFrame frame;

    private JLabel bg;
    private JLabel player;

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

        //import files
        try {
            playerImage = new ImageIcon("testFile.png");
        } catch (Exception e) {
            System.out.println("Could not find file for player sprite");
        }
        try {
            bgImage = new ImageIcon();
        } catch (Exception e) {
            System.out.println("Could not find file for player sprite");
        }

        //panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        frame.add(panel, BorderLayout.CENTER);

        //bg
        panel.add(bg);
        bg.setIcon(bgImage);



    }
    // public void movePlayer(double[] pos){
    //     super.paintComponent(g);
    //     panel.drawImage(player, (int)(pos[0]*1000)+500, (int)(pos[1]*1000)+500, null);
    //     playerLoc.setLocation(px, py);
    // }
}
