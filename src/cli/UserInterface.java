package cli;

import logic.*;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Scanner;

//TODO: Arranjar colisoes, meter ogres a dar overlap bem (primeiro desenhar unmovables, depois movables no fim),  criar classe game

public class UserInterface {

    public static class Game {

        static ArrayList<Level> levels = new ArrayList<Level>();

        public static void main(String[] args) {

            Character[][] map1 = new Character[][]{
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                    {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                    {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
                    {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                    {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
                    {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
            };

            Hero h = new Hero(1, 1);

            levels.add(new Level(map1, h, 0));

            Character[] route = new Character[]{'l', 'd', 'd', 'd', 'd', 'l', 'l', 'l', 'l', 'l', 'l', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'u', 'u', 'u', 'u', 'u'};
            Guard g = new DrunkenGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
            levels.get(0).addGuard(g);

            Door d1 = new Door(5, 0);
            levels.get(0).addDoor(d1);
            Door d2 = new Door(6, 0);
            levels.get(0).addDoor(d2);

            Lever l = new Lever(8, 7);
            levels.get(0).addLever(l);

            Character[][] map2 = new Character[][]{
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
            };

            Hero h2 = new Hero(7, 1);
            h2.arm();

            levels.add(new Level(map2, h2, 1));

            Door d3 = new Door(1, 0);
            levels.get(1).addDoor(d3);

            Key k = new Key(1, 7);
            levels.get(1).addKey(k);

            Ogre o = new Ogre(1, 4, 1, 3);
            levels.get(1).addOgre(o);

            Ogre o2 = new Ogre(3, 6, 3, 5);
            levels.get(1).addOgre(o2);

            for (Level lvl : levels) {
                printMap(lvl.getMap());

                if (!lvl.userMove())
                    break;
            }
        }


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
    }
}