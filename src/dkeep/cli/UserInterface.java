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
            try {
            FileOutputStream fileOut =
                    new FileOutputStream("saves/" + filename + ".txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Game loadState(String filename) {
        Game game  = null;
        try {
            FileInputStream fileIn = new FileInputStream("saves/" + filename + ".txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return game;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return game;
        }
        return game;
    }
}