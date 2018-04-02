package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.*;
import java.io.*;
import java.util.*;

public class UserInterface {

        public enum Direction {
            UP, LEFT, DOWN, RIGHT, NONE
        }

        public static Direction userInput() {

            char input;
            String s;
            //Asking the user for a direction input to move the hero
            System.out.print("Enter a direction with 'w'(up), 'a'(left), 's'(down), 'd'(right), 'e' to exit the logic : ");
            while(true) {
                Scanner reader = new Scanner(System.in);
                //saving the first char of the input to a variable c
                s = reader.next();

                if (s.matches("[A-Z | a-z]{1}")) {
                    break;
                } else {
                    System.out.print("Invalid input, try again : ");
                }
            }

            s.toLowerCase();
            input = s.charAt(0);

            switch (input) {
                case 'w':
                    return Direction.UP;
                case 'a':
                    return Direction.LEFT;
                case 's':
                    return Direction.DOWN;
                case 'd':
                    return Direction.RIGHT;
                default:
                    return Direction.NONE;
            }
        }

        public static void printMap(Character[][] map) {
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        if (j != 0) {
                            System.out.print(' ');
                        }
                        System.out.print(map[i][j]);
                    }
                    System.out.print("\n");
                }
        }

    public static void saveLevel(String filename, Level level) {
        try (PrintWriter writer = new PrintWriter("levels/" + filename + ".txt", "UTF-8")) {
            for(int i = 0; i < level.getMap().length; ++i) {
                for(int j = 0; j < level.getMap()[i].length; ++j) {
                    writer.println(level.getMap()[i][j]);
                }
                writer.println('\n');
            }
            writer.println("-\n");

            writer.println("h;"+ level.getHero().getX() + ";" + level.getHero().getY() + "\n");
            writer.println("k;"+ level.getObject().getX() + ";" + level.getObject().getY() + "\n");

            for(GameCharacter c : level.getChars())
                if(c instanceof Ogre) {
                    writer.println("o;"+ c.getX() + ";" + c.getY() + ";" + ((Ogre) c).getClubX() + ";" + ((Ogre) c).getClubY() + "\n");
                }

            for(Door d : level.getDoors())
                writer.println("d;" + d.getX() + ";" + d.getY() + "\n");

            writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadLevel(Game game, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("map/" + filename + ".txt"))) {
            String sCurrentLine;
            boolean readingMap = true;
            ArrayList<Character[]>lines = new ArrayList<>();
            Level level = new Level();
            int x, y, clubX, clubY;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);

                if(readingMap) {
                    if(sCurrentLine.charAt(0) == '-'){
                        Character[][] map = new Character[lines.size()][];
                        for (int i = 0; i < lines.size(); ++i)
                            map[i] = lines.get(i);
                        level.setMap(map);
                        readingMap = false;
                        break;
                    }

                    Character[] line = new Character[sCurrentLine.length()];

                    for (int i = 0, n = sCurrentLine.length(); i < n; ++i) {
                        line[i] = sCurrentLine.charAt(i);
                    }

                    lines.add(line);
                }

                else {
                    switch(sCurrentLine.charAt(0)){
                        case 'h':
                            sCurrentLine = sCurrentLine.substring(2);
                            x = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';')-1));
                            sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';')+1);
                            y = Integer.parseInt(sCurrentLine);
                            level.setHero(new Hero(x,y));
                            break;

                        case 'k':
                            sCurrentLine = sCurrentLine.substring(2);
                            x = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';')-1));
                            sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';')+1);
                            y = Integer.parseInt(sCurrentLine);
                            level.addKey(new Key(x,y));
                            break;

                        case 'o':
                            sCurrentLine = sCurrentLine.substring(2);
                            x = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';')-1));
                            sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';')+1);
                            y = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';')-1));
                            sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';')+1);
                            clubX = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';')-1));
                            sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';')+1);
                            clubY = Integer.parseInt(sCurrentLine);
                            level.addOgre(new Ogre(x,y,clubX,clubY));
                            break;

                        case 'd':
                            sCurrentLine = sCurrentLine.substring(2);
                            x = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';')-1));
                            sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';')+1);
                            y = Integer.parseInt(sCurrentLine);
                            level.addDoor(new Door(x,y));
                            break;
                    }
                }
            }

            game.loadLevel(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}