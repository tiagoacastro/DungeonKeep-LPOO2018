package dkeep.gui;

import dkeep.logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GamePanel extends JPanel {

    private Game game;
    private DungeonKeepGUI gui;

    private BufferedImage background;

    public GamePanel(DungeonKeepGUI d){
        try {
            background = ImageIO.read(new File("images/background.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        gui = d;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(gui.checkGameStart()) {
            game = gui.getGame();
            Character[][] map = game.getLevel().getPlayMap();

            for (int i = 0; i < map.length; ++i)
                for (int j = 0; j < map[i].length; ++j){
                    switch(map[i][j]) {
                        case 'X':
                            g.drawImage(background, j * 25, i * 25, null);
                            break;
                        case 'H':
                            g.drawImage(background, j * 25, i * 25, null);
                            break;
                    }
            }
        }
    }
}
