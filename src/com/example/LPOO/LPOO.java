package com.example.LPOO;
import java.util.Scanner;

public class LPOO {

    //Creating the game map using a bi-dimensional array of integers
    static Character[][] map = new Character[10][10];

    public static class Hero {
        public int x;
        public int y;

        //constructor
        public Hero(int xcoord, int ycoord) {
            x = xcoord;
            y = ycoord;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setX(int xcoord){
            x = xcoord;
        }
        public void setY(int ycoord){
            y = ycoord;
        }


    }

    public static class Guard {
        public int x;
        public int y;

        //constructor
        public Guard(int xcoord, int ycoord) {
            x = xcoord;
            y = ycoord;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setX(int xcoord){
            x = xcoord;
        }
        public void setY(int ycoord){
            y = ycoord;
        }


    }

    public static void main(String[] args) {

        create_map();
        Hero h = new Hero(1, 1);
        Guard g = new Guard(1,8);

        print_map();

        user_move(h, g);


    }

    public static void create_map() {

        //Filling the first line with X's (wall)
        for (int i = 0; i< 10; i++) {

            map[0][i] = 'X';
        }
        //Filling the last line with X's (wall)
        for (int i = 0; i< 10; i++) {

            map[9][i] = 'X';
        }
        //Filling the last column with X's (wall)
        for (int i = 0; i< 10; i++) {

            map[i][9] = 'X';
        }
        //Filling the first column with X's (wall)
        for (int i = 0; i< 10; i++) {

            if(i != 5 && i!=6)
                map[i][0] = 'X';
        }

        map[1][1] = 'H';
        map[1][2] = ' ';
        map[1][3] = ' ';
        map[1][4] = 'I';
        map[1][5] = ' ';
        map[1][6] = 'X';
        map[1][7] = ' ';
        map[1][8] = 'G';

        map[2][1] = 'X';
        map[2][2] = 'X';
        map[2][3] = ' ';
        map[2][4] = 'X';
        map[2][5] = 'X';
        map[2][6] = 'X';
        map[2][7] = ' ';
        map[2][8] = ' ';

        map[3][1] = ' ';
        map[3][2] = 'I';
        map[3][3] = ' ';
        map[3][4] = 'I';
        map[3][5] = ' ';
        map[3][6] = 'X';
        map[3][7] = ' ';
        map[3][8] = ' ';

        map[4][1] = 'X';
        map[4][2] = 'X';
        map[4][3] = ' ';
        map[4][4] = 'X';
        map[4][5] = 'X';
        map[4][6] = 'X';
        map[4][7] = ' ';
        map[4][8] = ' ';

        map[5][0] = 'I';
        map[5][1] = ' ';
        map[5][2] = ' ';
        map[5][3] = ' ';
        map[5][4] = ' ';
        map[5][5] = ' ';
        map[5][6] = ' ';
        map[5][7] = ' ';
        map[5][8] = ' ';

        map[6][0] = 'I';
        map[6][1] = ' ';
        map[6][2] = ' ';
        map[6][3] = ' ';
        map[6][4] = ' ';
        map[6][5] = ' ';
        map[6][6] = ' ';
        map[6][7] = ' ';
        map[6][8] = ' ';

        map[7][1] = 'X';
        map[7][2] = 'X';
        map[7][3] = ' ';
        map[7][4] = 'X';
        map[7][5] = 'X';
        map[7][6] = 'X';
        map[7][7] = 'X';
        map[7][8] = ' ';

        map[8][1] = ' ';
        map[8][2] = 'I';
        map[8][3] = ' ';
        map[8][4] = 'I';
        map[8][5] = ' ';
        map[8][6] = 'X';
        map[8][7] = 'k';
        map[8][8] = ' ';

    }

    public static char user_input() {

        //Asking the user for a direction input to move the hero
        System.out.println("Enter a direction with 'w'(up), 'a'(left), 's'(down), 'd'(right), 'e' to exit the game : ");
        Scanner reader = new Scanner(System.in);
        //saving the first char of the input to a variable c
        char input = reader.findInLine(".").charAt(0);


        return input;

    }
    public static void user_move(Hero h, Guard g) {

        char c;
        boolean exit = true;

        do {
            c = user_input();
            switch (c) {

                case 'w':
                    if (map[h.getX()-1][h.getY()] == ' '){
                    map[h.getX()][h.getY()] = ' ';
                    map[h.getX()-1][h.getY()] = 'H';
                    h.setX(h.getX()-1);
                }
                break;
                case 'a':
                    if (map[h.getX()][h.getY()-1] == ' '){
                        map[h.getX()][h.getY()] = ' ';
                        map[h.getX()][h.getY()-1] = 'H';
                        h.setY(h.getY()-1);
                    }
                    break;
                case 's':
                    if (map[h.getX()+1][h.getY()] == ' '){
                        map[h.getX()][h.getY()] = ' ';
                        map[h.getX()+1][h.getY()] = 'H';
                        h.setX(h.getX()+1);
                    }
                    break;
                case 'd':
                    if (map[h.getX()][h.getY()+1] == ' '){
                    map[h.getX()][h.getY()] = ' ';
                    map[h.getX()][h.getY()+1] = 'H';
                    h.setY(h.getY()+1);
                }
                break;
                case 'e':
                    exit = false;
                    break;
            }

            //Checking if the hero is close to the guard, in case he is, the program will shut down cause the USER lost
            //TODO: use guard coordinates
            int hero_x = h.getX();
            int hero_y = h.getY();
            if ((map[hero_x + 1][hero_y] == 'G') || (map[hero_x - 1][hero_y] == 'G') || (map[hero_x][hero_y +1] == 'G') || (map[hero_x][hero_y-1]== 'G')) {
                System.out.println("The guard has restrained you, you LOST ! :( ");
                exit = false;
            }


            print_map();

        } while (exit);
    }

    public static void print_map() {

        //Printing the map
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        }

    }
}
