package com.github.gameGUI;
import java.awt.*;
import java.security.cert.LDAPCertStoreParameters;

import javax.swing.*;

public class GameGUI extends JComponent{
    private static int WIDTH = 1000;
    private static int HEIGHT = 1000;

    int px = 500;
    int py = 500;
    int pw = 50;
    int ph = 50;
    

    private Point playerLoc;

    private JFrame frame;

    private JLayeredPane p = new JLayeredPane();

    private Icon pIcon;
    private Icon bgIcon;


    public GameGUI(){
        //player location
        playerLoc = new Point(px,py);

        //game frame
        frame = new JFrame();
        frame.setTitle("Virtual Casino");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(p);
        frame.setResizable(false);

        //Layered Pane
        p.setBounds(0,0,WIDTH,HEIGHT);
        p.setBackground(Color.gray);

        //get pictures
        pIcon = new ImageIcon("C:\\Users\\Arush\\Documents\\CSAProject\\jnativehook\\src\\main\\java\\com\\github\\gameGUI\\testFile.png");
        bgIcon = new ImageIcon("C:\\Users\\Arush\\Documents\\CSAProject\\jnativehook\\src\\main\\java\\com\\github\\gameGUI\\testBG.jpg");
        //hud


        //player
        JLabel player = new JLabel();
        player.setOpaque(false);
        player.setBackground(Color.red);
        player.setIcon(pIcon);
        player.setBounds(px,py,pw,ph);
        p.add(player);

        //bg
        JLabel bg = new JLabel();
        bg.setOpaque(true);
        bg.setBackground(Color.blue);
        bg.setIcon(bgIcon);
        bg.setBounds(0,0,WIDTH,HEIGHT);
        p.add(bg);


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
