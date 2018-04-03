package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.*;
import java.io.*;
import java.util.*;

//TODO

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

    public static void saveState(String filename, Game game) {
            Level level = game.getLevel();
        try (PrintWriter writer = new PrintWriter("saves/" + filename + ".txt", "UTF-8")) {
            writer.println(game.getCurrentLevel() + "\n");
            for(int i = 0; i < level.getMap().length; ++i) {
                for(int j = 0; j < level.getMap()[i].length; ++j)
                    writer.println(level.getMap()[i][j]);
                writer.println('\n');}
            writer.println("-\n");
            writer.println("h;"+ level.getHero().getX() + ";" + level.getHero().getY() + "\n");
            writer.println("k;"+ level.getObject().getX() + ";" + level.getObject().getY() + "\n");
            for(GameCharacter c : level.getChars())
                if(c instanceof Ogre)
                    writer.println("o;"+ c.getX() + ";" + c.getY() + ";" + ((Ogre) c).getClubX() + ";" + ((Ogre) c).getClubY() + "\n");
            for(Door d : level.getDoors())
                writer.println("d;" + d.getX() + ";" + d.getY() + "\n");
            writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadState(Game game, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("saves/" + filename + ".txt"))) {
            String sCurrentLine;
            boolean readingMap = true;
            ArrayList<Character[]>lines = new ArrayList<>();
            Level level = new Level();
            boolean levelGet = true;
            int levelNumber=0;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                if(levelGet) {
                    levelGet = false;
                    levelNumber = Integer.parseInt(sCurrentLine);
                    game.setLevel(levelNumber);
                }else if (readingMap) {
                        if (sCurrentLine.charAt(0) == '-') {
                            Character[][] map = new Character[lines.size()][];
                            for (int i = 0; i < lines.size(); ++i)
                                map[i] = lines.get(i);
                            level.setMap(map);
                            readingMap = false;
                            break;}
                        readMap(sCurrentLine, lines);
                    } else readObject(sCurrentLine, level);}
            game.substLevel(level,levelNumber);
        } catch (IOException e){
            e.printStackTrace();}
    }

    private static void readMap(String sCurrentLine, ArrayList<Character[]> lines) {
        Character[] line = new Character[sCurrentLine.length()];

        for (int i = 0, n = sCurrentLine.length(); i < n; ++i) {
            line[i] = sCurrentLine.charAt(i);
        }

        lines.add(line);
    }

    private static void readObject(String sCurrentLine, Level level) {
        switch (sCurrentLine.charAt(0)) {
            case 'h':
                level.setHero(new Hero(readX(sCurrentLine), readY(sCurrentLine)));
                break;

            case 'k':
                level.addKey(new Key(readX(sCurrentLine), readY(sCurrentLine)));
                break;

            case 'o':
                readOgre(sCurrentLine, level);
                break;

            case 'd':
                level.addDoor(new Door(readX(sCurrentLine), readY(sCurrentLine)));
                break;
        }
    }

    private static void readOgre(String sCurrentLine, Level level) {
            int x, y, clubX, clubY;
        sCurrentLine = sCurrentLine.substring(2);
        x = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';') - 1));
        sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';') + 1);
        y = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';') - 1));
        sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';') + 1);
        clubX = Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';') - 1));
        sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';') + 1);
        clubY = Integer.parseInt(sCurrentLine);
        level.addOgre(new Ogre(x, y, clubX, clubY));
    }

    private static int readX(String sCurrentLine){
        sCurrentLine = sCurrentLine.substring(2);
        return Integer.parseInt(sCurrentLine.substring(0, sCurrentLine.indexOf(';') - 1));
    }

    private static int readY(String sCurrentLine){
        sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(';') + 1);
        return Integer.parseInt(sCurrentLine);
    }
}