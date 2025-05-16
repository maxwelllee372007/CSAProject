package com.github.gameGUI;
import java.awt.*;
import javax.swing.*;

public class GameGUI extends JComponent{
    private static int WIDTH = 1000;
    private static int HEIGHT = 1000;

    int px = 500;
    int py = 500;
    

    private Point playerLoc;

    private JFrame frame;

    private Panel bg = new Panel();

    public GameGUI(){
        //player location
        playerLoc = new Point(px,py);

        //game frame
        frame = new JFrame();
        frame.setTitle("Virtual Casino");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        frame.setResizable(false);

        //bg
        frame.setContentPane(bg);

        frame.setVisible(true);
    }
    public int[] getGUISize(){
        return new int[]{WIDTH,HEIGHT};
    }
    // public void movePlayer(double[] pos){
    //     super.paintComponent(g);
    //     panel.drawImage(player, (int)(pos[0]*1000)+500, (int)(pos[1]*1000)+500, null);
    //     playerLoc.setLocation(px, py);
    // }
}
