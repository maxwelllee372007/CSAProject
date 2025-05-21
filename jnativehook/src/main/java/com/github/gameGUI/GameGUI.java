package com.github.gameGUI;
import java.awt.*;
import java.security.cert.LDAPCertStoreParameters;

import javax.swing.*;

import com.github.aakm.Constants;
import com.github.aakm.interactibleMachines.Machine;

public class GameGUI extends JComponent{
    private static int WIDTH = 800;
    private static double pixelsToGame = WIDTH/Constants.outerBoundary.getWidth();
    private static int HEIGHT = scaleToGUIPixels(Constants.outerBoundary.getHeight());
    static {
        WIDTH += Constants.borderThickness*2;
        HEIGHT += Constants.borderThickness*2;
    }

    int[] pos = convertToGUIPixels(Constants.playerStartingPos);
    int pw = scaleToGUIPixels(Constants.playerRadius * 2);
    int ph = scaleToGUIPixels(Constants.playerRadius * 2);
    


    private JFrame frame;

    private JLayeredPane p = new JLayeredPane();
    public static JLayeredPane slotsGUI = new JLayeredPane();
    public static JLayeredPane rouletteGUI = new JLayeredPane();

    private JLabel player = new JLabel();
    private JLabel bg = new JLabel();
    private JLabel interactPrompt = new JLabel();
    public static JLabel LeftReel= new JLabel();
    public static JLabel MidReel= new JLabel();
    public static JLabel RightReel= new JLabel();
    public static JLabel backgroundSlots= new JLabel();
    public static JLabel backgroundRoulette= new JLabel();


    private boolean facingLeft = false;
    private boolean isSlots = false;

    private Icon pIcon, pIconStep, pIconLeft, pIconStepLeft, bgIcon, interactIcon, backgroundSlotsIcon, backgroundRouletteIcon;

    public static Icon[] reelSpinIcons = new Icon[3];
    public static Icon[] reelEndIcons = new Icon[3];


    public GameGUI(){

        //game frame
        frame = new JFrame();
        frame.setTitle("Virtual Casino");
        frame.setSize(WIDTH + 16,HEIGHT + 39);// +16 and +39 are the size of the window borders idk why
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(p);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //Layered Pane
        p.setBounds(0,0,WIDTH,HEIGHT);
        p.setBackground(Color.gray);

        //get pictures
        pIcon = new ImageIcon(Constants.playerImagePath);
        pIconStep = new ImageIcon(Constants.playerImagePathStep);
        pIconLeft = new ImageIcon(Constants.playerImagePathLeft);
        pIconStepLeft = new ImageIcon(Constants.playerImagePathStepLeft);
        bgIcon = new ImageIcon(Constants.backgroundImagePath);
        reelSpinIcons[0] = new ImageIcon(Constants.reelSpin1);
        reelSpinIcons[1] = new ImageIcon(Constants.reelSpin2);
        reelSpinIcons[2] = new ImageIcon(Constants.reelSpin3);
        reelEndIcons[0] = new ImageIcon(Constants.reelEnd1);
        reelEndIcons[1] = new ImageIcon(Constants.reelEnd2);
        reelEndIcons[2] = new ImageIcon(Constants.reelEnd3);
        backgroundSlotsIcon = new ImageIcon(Constants.Machines.slotsPopUpImagePath);
        backgroundRouletteIcon = new ImageIcon(Constants.Machines.roulettePopUpImagePath);


        //hud
        interactIcon = new ImageIcon(Constants.InteractPrompt.interactPromptImagePath);
        interactPrompt.setOpaque(false);
        interactPrompt.setBackground(Color.red);
        interactPrompt.setIcon(interactIcon);
        interactPrompt.setBounds(Constants.InteractPrompt.interactPromptPos[0] - Constants.InteractPrompt.interactPromptSize[0] / 2, Constants.InteractPrompt.interactPromptPos[1] + Constants.InteractPrompt.interactPromptSize[1] / 2, Constants.InteractPrompt.interactPromptSize[0], Constants.InteractPrompt.interactPromptSize[1]);
        p.add(interactPrompt);

        //Slots
        slotsGUI.setBounds(0, 0, WIDTH, HEIGHT);
        slotsGUI.setOpaque(false);
        slotsGUI.setBackground(Color.lightGray);
        p.add(slotsGUI, JLayeredPane.DRAG_LAYER);
        slotsGUI.setVisible(isSlots);

            //reels
            LeftReel.setOpaque(false);
            LeftReel.setBackground(Color.red);
            LeftReel.setBounds(183,395,110,190);
            slotsGUI.add(LeftReel);

            MidReel.setOpaque(false);
            MidReel.setBackground(Color.green);
            MidReel.setBounds(323,395,110,190);
            slotsGUI.add(MidReel);

            RightReel.setOpaque(false);
            RightReel.setBackground(Color.blue);
            RightReel.setBounds(463,395,110,190);
            slotsGUI.add(RightReel);

            backgroundSlots.setOpaque(false);
            backgroundSlots.setBackground(Color.blue);
            backgroundSlots.setBounds(0,0,880,880);
            backgroundSlots.setIcon(backgroundSlotsIcon);
            slotsGUI.add(backgroundSlots);

        // Roulette
        rouletteGUI.setBounds(0, 0, WIDTH, HEIGHT);
        rouletteGUI.setOpaque(false);
        rouletteGUI.setBackground(Color.lightGray);
        p.add(rouletteGUI, JLayeredPane.DRAG_LAYER);
        rouletteGUI.setVisible(isSlots);

            backgroundRoulette.setOpaque(false);
            backgroundRoulette.setBackground(Color.blue);
            backgroundRoulette.setBounds(0,0,880,880);
            backgroundRoulette.setIcon(backgroundRouletteIcon);
            rouletteGUI.add(backgroundRoulette);

        //player
        player.setOpaque(false);
        player.setBackground(Color.red);
        player.setIcon(pIcon);
        player.setBounds(pos[0]-pw/2,pos[1]-ph/2,pw,ph);
        p.add(player);

        for (int i = 0; i < Constants.Machines.machineIcons.size(); i++){
            JLabel machineLabel = new JLabel();
            machineLabel.setOpaque(false);
            machineLabel.setBackground(Color.blue);
            machineLabel.setIcon(Constants.Machines.machineIcons.get(i));
            com.github.aakm.obstacles.Box machineBox = Constants.Machines.machines.get(i).getCollisionBox();
            double[] topLeftCorner = {
                machineBox.getCenterPos()[0] - machineBox.getWidth()/2,
                machineBox.getCenterPos()[1] + machineBox.getHeight()/2
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
        return pos;
    }
    public void movePlayer(double[] pos, double xMovement){
        int[] newPos = convertToGUIPixels(pos);
        player.setBounds(newPos[0] - pw/2,newPos[1] - ph/2,pw,ph);
        if (xMovement < 0){
            facingLeft = true;
        } else if (xMovement > 0){
            facingLeft = false;
        }
        if (facingLeft){
            player.setIcon((System.currentTimeMillis() / 1000.0) % (Constants.playerStepFrequency) < Constants.playerStepFrequency * 0.5 ? pIconLeft : pIconStepLeft);
        } else {
            player.setIcon((System.currentTimeMillis() / 1000.0) % (Constants.playerStepFrequency) < Constants.playerStepFrequency * 0.5 ? pIcon : pIconStep);
        }
        p.repaint();
    }
    public void displayInteractPrompt() {
        interactPrompt.setVisible(true);
        p.repaint();
    }
    public void removeInteractPrompt() {
        interactPrompt.setVisible(false);
        p.repaint();
    }
    /**
     * only scales a scalar value
     * @param size
     * @return
     */
    public static int scaleToGUIPixels(double size){
        return (int)(size*pixelsToGame);
    }
    public static double scaleToGameMeters(int pixels){
        return (pixels/pixelsToGame);
    }
    public static int[] convertToGUIPixels(double[] pos){
        int x = (int)(pos[0]*pixelsToGame + WIDTH/2);
        int y = (int)(-pos[1]*pixelsToGame + HEIGHT/2);
        return new int[]{x,y};
    }
    // public static double[] convertToGamePos(int[] pos){
    //     double x = (pos[0] - WIDTH/2)/pixelsToGame;
    //     double y = -(pos[1] - HEIGHT/2)/pixelsToGame;
    //     return new double[]{x,y};
    // }

}
