package com.github.gameGUI;
import java.awt.*;
import java.security.cert.LDAPCertStoreParameters;

import javax.swing.*;

public class GameGUI extends JComponent{
    private static int WIDTH = 800;
    private static int HEIGHT = 800;

    int px = 400;
    int py = 400;
    int pw = 100;
    int ph = 100;
    

    private Point playerLoc;

    private JFrame frame;

    private JLayeredPane p = new JLayeredPane();

    private JLabel player = new JLabel();
    private JLabel bg = new JLabel();

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
        player.setOpaque(false);
        player.setBackground(Color.red);
        player.setIcon(pIcon);
        player.setBounds(px,py,pw,ph);
        p.add(player);

        //bg
        bg.setOpaque(true);
        bg.setBackground(Color.gray);
        bg.setIcon(bgIcon);
        bg.setBounds(0,0,WIDTH,HEIGHT);
        p.add(bg);


        frame.setVisible(true);
    }
    public int[] getGUISize(){
        return new int[]{WIDTH,HEIGHT};
    }
    public int[] getPlayerGUIPos(){
        return new int[]{px,py};
    }
    public void movePlayer(double[] pos){
        px = (int)(pos[0]*400 + 400);
        py = (int)(-pos[1]*400 + 400);
        player.setBounds(px,py,pw,ph);
        p.repaint();
    }

}
