package dkeep.gui;

import dkeep.logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.HashMap;

public class GamePanel extends JPanel{

    private Game game;
    private DungeonKeepGUI gui;

    private HashMap<String, BufferedImage> loadedImages;

    public GamePanel(DungeonKeepGUI d){
        loadedImages = new HashMap<>();
        try {
            loadedImages.put("wall",ImageIO.read(new File("images/wall.png")));
            loadedImages.put("guard",ImageIO.read(new File ("images/guard.png")));
            loadedImages.put("hero",ImageIO.read(new File ("images/hero.png")));
            loadedImages.put("closedDoor",ImageIO.read(new File ("images/closedDoor.png")));
            loadedImages.put("openedDoor",ImageIO.read(new File ("images/openedDoor.png")));
            loadedImages.put("lever", ImageIO.read(new File ("images/lever.png")));
            loadedImages.put("ogre", ImageIO.read(new File ("images/ogre.png")));
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

            drawImagePanel(g, map);
        }
    }

    public void drawImagePanel(Graphics g, Character[][] map) {
        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[i].length; ++j){
                switch(map[i][j]) {
                    case 'X':
                        g.drawImage(loadedImages.get("wall"), j * 25, i * 25, null);
                        break;
                    case 'H':
                        g.drawImage(loadedImages.get("hero"), j * 25, i * 25, null);
                        break;
                    case 'A':
                        g.drawImage(loadedImages.get("hero"), j * 25, i * 25, null);
                        break;
                    case 'G':
                        g.drawImage(loadedImages.get("guard"), j * 25, i * 25, null);
                        break;
                    case 'I':
                        g.drawImage(loadedImages.get("closedDoor"), j * 25, i * 25, null);
                        break;
                    case 'S':
                        g.drawImage(loadedImages.get("openedDoor"), j * 25, i * 25, null);
                        break;
                    case 'k':
                        g.drawImage(loadedImages.get("lever"), j * 25, i * 25, null);
                        break;
                    case '0':
                        g.drawImage(loadedImages.get("ogre"), j * 25, i * 25, null);
                        break;
                        default:
                          break;
                }
        }
    }

}
