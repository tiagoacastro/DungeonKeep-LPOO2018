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
    private BufferedImage guard;
    private BufferedImage hero;
    private BufferedImage wall;
    private BufferedImage closedDoor;
    private BufferedImage openedDoor;
    private BufferedImage lever;

    public GamePanel(DungeonKeepGUI d){
        try {
            background = ImageIO.read(new File("images/background.png"));
            wall = ImageIO.read(new File("images/wall.png"));
            guard = ImageIO.read(new File ("images/guard.png"));
            hero = ImageIO.read(new File ("images/hero.png"));
            closedDoor = ImageIO.read(new File ("images/closedDoor.png"));
            openedDoor = ImageIO.read(new File ("images/openedDoor.png"));
            lever = ImageIO.read(new File ("images/lever.png"));
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
                            g.drawImage(wall, j * 25, i * 25, null);
                            break;
                        case 'H':
                            g.drawImage(hero, j * 25, i * 25, null);
                            break;
                        case 'G':
                            g.drawImage(guard, j * 25, i * 25, null);
                            break;
                        case 'I':
                            g.drawImage(closedDoor, j* 25 , i*25 ,null);
                            break;
                        case 'S':
                            g.drawImage(openedDoor, j* 25 , i*25 ,null);
                            break;
                        case 'k':
                            g.drawImage(lever, j* 25 , i*25 ,null);
                            break;
                    }
            }
        }
    }
}
