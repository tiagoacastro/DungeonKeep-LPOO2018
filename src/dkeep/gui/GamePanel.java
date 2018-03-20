package dkeep.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GamePanel extends JPanel {

    private BufferedImage background;

    public GamePanel(){
        try {
            background = ImageIO.read(new File("images/background.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getHeight(), getHeight(), null);
    }
}
