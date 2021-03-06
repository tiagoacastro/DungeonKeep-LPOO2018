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

    private HashMap<String, BufferedImage> loadedImages;

    GamePanel(Game game){
        loadedImages = new HashMap<>();
        try {
            loadedImages.put("background",ImageIO.read(new File("images/background.png")));
            loadedImages.put("wall",ImageIO.read(new File("images/wall.png")));
            loadedImages.put("guard",ImageIO.read(new File ("images/guard.png")));
            loadedImages.put("guardSleeping",ImageIO.read(new File ("images/guardSleeping.png")));
            loadedImages.put("hero",ImageIO.read(new File ("images/hero.png")));
            loadedImages.put("heroArmed", ImageIO.read(new File ("images/heroArmed.png")));
            loadedImages.put("closedDoor",ImageIO.read(new File ("images/closedDoor.png")));
            loadedImages.put("openedDoor",ImageIO.read(new File ("images/openedDoor.png")));
            loadedImages.put("lever", ImageIO.read(new File ("images/lever.png")));
            loadedImages.put("ogre", ImageIO.read(new File ("images/ogre.png")));
            loadedImages.put("ogreStunned", ImageIO.read(new File ("images/ogreStunned.png")));
            loadedImages.put("ogreKey", ImageIO.read(new File ("images/ogreKey.png")));
            loadedImages.put("club", ImageIO.read(new File ("images/club.png")));
            loadedImages.put("clubWithKey", ImageIO.read(new File ("images/clubWithKey.png")));
            loadedImages.put("key", ImageIO.read(new File("images/key.png")));
            loadedImages.put("heroArmedWithKey", ImageIO.read(new File("images/heroArmedWithKey.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Character[][] map = game.getLevel().getPlayMap();

        drawImagePanel(g, map);
    }

    private void drawBackground(Graphics g, Character[][] map) {

        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[i].length; ++j) {
                g.drawImage(loadedImages.get("background"), j * 32, i * 32, null);
            }
    }

    private void drawImagePanel(Graphics g, Character[][] map) {
        drawBackground(g,map);
        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[i].length; ++j){
                chooseImage(g, map[i][j], i, j);
            }
    }

    private void chooseImage(Graphics g, Character character, int i, int j) {
        chooseImageImovable(g, character, i, j);
        chooseImageMovable(g, character, i, j);
    }

    private void chooseImageMovable(Graphics g, Character character, int i, int j) {
        switch(character) {
            case 'H':
                g.drawImage(loadedImages.get("hero"), j * 32, i * 32, null);
                break;
            case 'A':
                g.drawImage(loadedImages.get("heroArmed"), j * 32, i * 32, null);
                break;
            case 'K':
                g.drawImage(loadedImages.get("heroArmedWithKey"), j * 32, i * 32, null);
                break;
            case 'G':
                g.drawImage(loadedImages.get("guard"), j * 32, i * 32, null);
                break;
            case 'g':
                g.drawImage(loadedImages.get("guardSleeping"), j * 32, i * 32, null);
                break;
            case '0':
                g.drawImage(loadedImages.get("ogre"), j * 32, i * 32, null);
                break;
            case '8':
                g.drawImage(loadedImages.get("ogreStunned"), j * 32, i * 32, null);
                break;
            case '$':
                g.drawImage(loadedImages.get("ogreKey"), j * 32, i * 32, null);
                break;
            case '*':
                g.drawImage(loadedImages.get("club"), j *32, i* 32, null);
                break;
        }
    }

    private void chooseImageImovable(Graphics g, Character character, int i, int j) {
        switch(character) {
            case 'X':
                g.drawImage(loadedImages.get("wall"), j * 32, i * 32, null);
                break;
            case 'I':
                g.drawImage(loadedImages.get("closedDoor"), j * 32, i * 32, null);
                break;
            case 'k':
                if (game.getLevel().getObject() instanceof Key)
                    g.drawImage(loadedImages.get("key"), j * 32, i * 32, null);
                else g.drawImage(loadedImages.get("lever"), j * 32, i * 32, null);
                break;
            case 'C':
                    g.drawImage(loadedImages.get("clubWithKey"), j * 32, i * 32, null);
                break;
            case 'S':
                g.drawImage(loadedImages.get("openedDoor"), j * 32, i * 32, null);
                break;
        }
    }

}
