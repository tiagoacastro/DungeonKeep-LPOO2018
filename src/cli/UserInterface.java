package cli;

import logic.*;

import java.io.*;

import java.util.Scanner;

public class UserInterface {

    public static class LPOO {

        static Level[] levels = new Level[10];
        static int level = 0;

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
            map1[h.getX()][h.getY()] = 'H';

            levels[0] = new Level(map1, h, 0);
            level++;

            //map = maps[0];
            Guard g = new Guard(1, 8, new char[]{'l', 'd', 'd', 'd', 'd', 'l', 'l', 'l', 'l', 'l', 'l', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'u', 'u', 'u', 'u', 'u'});
            levels[0].addGuard(g);
            map1[g.getX()][g.getY()] = 'G';

            Door d1 = new Door(5, 0);
            levels[0].addDoor(d1);
            d1.draw(levels[0].getMap());
            Door d2 = new Door(6, 0);
            levels[0].addDoor(d2);
            d2.draw(levels[0].getMap());

            Lever l = new Lever(8, 7);
            levels[0].addLever(l);
            l.draw(levels[0].getMap());

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
            map2[h2.getX()][h2.getY()] = 'H';

            levels[1] = new Level(map2, h2, 1);
            level++;

            Door d3 = new Door(1, 0);
            levels[1].addDoor(d3);
            d3.draw(levels[1].getMap());

            Key k = new Key(1, 7);
            levels[1].addKey(k);
            k.draw(levels[1].getMap());

            Ogre o = new Ogre(1, 4, 1, 3);
            levels[1].addOgre(o);
            map2[o.getX()][o.getY()] = '0';
            map2[o.getClubX()][o.getClubY()] = '*';

            for (int i = 0; i < level; ++i) {
                print_map(levels[i].getType(), levels[i].getMap());

                if (!levels[i].user_move())
                    break;
            }
        }


        public enum Direction {
            UP, LEFT, DOWN, RIGHT, NONE
        }

        public static Direction user_input() {

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

        public static void print_map(int type, Character[][] map) {

            //Printing the map
            if (type == 0)
                for (int i = 0; i < 10; i++) {

                    for (int j = 0; j < 10; j++) {
                        if (j != 0) {
                            System.out.print(' ');
                        }
                        System.out.print(map[i][j]);
                    }
                    System.out.print("\n");
                }
            else
                for (int i = 0; i < 9; i++) {

                    for (int j = 0; j < 9; j++) {
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