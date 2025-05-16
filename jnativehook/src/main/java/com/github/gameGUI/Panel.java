package com.github.gameGUI;
import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel{
    public void paintComponent(Graphics g){
        ImageIcon bgImage = new ImageIcon("C:\\Users\\Arush\\Documents\\CSAProject\\jnativehook\\src\\main\\java\\com\\github\\gameGUI\\testBG.jpg");

        g.drawImage(bgImage.getImage(), 0, 0, 1000, 1000, null);
    }
}
