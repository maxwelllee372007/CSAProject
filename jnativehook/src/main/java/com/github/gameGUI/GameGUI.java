package com.github.gameGUI;
import java.awt.*;
import java.security.cert.LDAPCertStoreParameters;

import javax.swing.*;

import com.github.aakm.Constants;
import com.github.aakm.interactibleMachines.Machine;

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
        pIcon = new ImageIcon(Constants.currentDirectory + "\\jnativehook\\src\\main\\java\\com\\github\\gameGUI\\goomba-waddle-2.gif");
        bgIcon = new ImageIcon(Constants.currentDirectory + "\\jnativehook\\src\\main\\java\\com\\github\\gameGUI\\testBG.png");

        //hud


        //player
        player.setOpaque(false);
        player.setBackground(Color.red);
        player.setIcon(pIcon);
        player.setBounds(px,py,pw,ph);
        p.add(player);

        for (int i = 0; i < Constants.Machines.machineIcons.size(); i++){
            JLabel machineLabel = new JLabel();
            machineLabel.setOpaque(false);
            machineLabel.setBackground(Color.blue);
            machineLabel.setIcon(Constants.Machines.machineIcons.get(i));
            com.github.aakm.obstacles.Box machineBox = Constants.Machines.machines.get(i).getCollisionBox();
            double[] topLeftCorner = {
                machineBox.getCenterPos()[0] - machineBox.getWidth()/2,
                machineBox.getCenterPos()[1] - machineBox.getHeight()/2
            };
            int[] topLeft = convertToGUIPixels(topLeftCorner);
            machineLabel.setBounds(topLeft[0],topLeft[1],scaleToGUIPixels(machineBox.getWidth()),scaleToGUIPixels(machineBox.getHeight()));
            p.add(machineLabel);
        }

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
    /**
     * only scales a scalar value
     * @param pos
     * @return
     */
    public int scaleToGUIPixels(double pos){
        return (int)(pos*400);
    }
    public int[] convertToGUIPixels(double[] pos){
        int x = (int)(pos[0]*400 + 400);
        int y = (int)(-pos[1]*400 + 400);
        return new int[]{x,y};
    }
    public double[] convertToGamePos(int[] pos){
        double x = (pos[0] - 400)/400;
        double y = -(pos[1] - 400)/400;
        return new double[]{x,y};
    }

}
