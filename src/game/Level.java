package game;

import java.util.Scanner;

public class Level {

    private Character[][] map;
    private Hero hero;
    private Guard guards[];
    private int guardNumber;
    //private Ogre ogres[];
    private int ogreNumber;
    private Door doors[];
    private int doorNumber;
    private Lever lever;
    //private Key key;
    private int type; //0 is lever level, 1 is key level

    Level(Character[][] m, Hero h, int t) {
        map = m;
        hero = h;
        guardNumber=0;
        ogreNumber=0;
        type = t
    }

    void addGuard(Guard g) {
        guards[guardNumber] = g;
        guardNumber++;
    }

    /*
    void addOgre(Ogre o) {
        ogres[ogreNumber] = o;
        ogreNumber++;
    }
    */

    Guard[] getGuards() {
        return guards;
    }

    /*
    Ogre[] getOgres() {
        return ogres;
    }
    */

    Character[][] getMap() {
        return map;
    }

    void user_move() {

        boolean exit = true;
        char input;

        do {

            input = user_input();

            switch (input) {

                case 'w':
                    if( move(hero.getX()-1,hero.getY()) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                case 'a':
                    if( move(hero.getX(),hero.getY()-1) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                case 's':
                    if( move(hero.getX()+1,hero.getY()) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                case 'd':
                    if( move(hero.getX(),hero.getY()+1) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                default:
                    exit = false;
                    break;
            }

            int hero_x = hero.getX();
            int hero_y = hero.getY();

            if(hero_x != 0 && hero_y != 0)
                if ((map[hero_x + 1][hero_y] == 'G') || (map[hero_x - 1][hero_y] == 'G') || (map[hero_x][hero_y +1] == 'G') || (map[hero_x][hero_y-1]== 'G')) {
                    System.out.println("The guard has restrained you, you LOST ! :( ");
                    exit = false;
                }

            print_map();

        } while (exit);
    }

    private boolean move(int next_x, int next_y) {
        if (map[next_x][next_y] == ' ') {
            map[hero.getX()][hero.getY()] = ' ';
            map[next_x][next_y] = 'H';
            hero.setX(next_x);
            hero.setY(next_y);
            for (int j = 0; j < doorNumber; ++j){
                doors[j].draw(map);
            }
            for (int i = 0; i < guardNumber; ++i){
                guards[i].draw(map);
            }
            if(type == 0)
                lever.draw(map);
            return false;
        }

        if (map[next_x][next_y] == 'k' && type == 0) {
            map[hero.getX()][hero.getY()] = ' ';
            map[next_x][next_y] = 'H';
            hero.setX(next_x);
            hero.setY(next_y);
            for (int j = 0; j < doorNumber; ++j){
                doors[j].open();
                doors[j].draw(map);
            }
            for (int i = 0; i < guardNumber; ++i){
                guards[i].draw(map);
            }
            return false;
        }

        if (map[next_x][next_y] == 'S') {
            for (int j = 0; j < doorNumber; ++j){
                doors[j].draw(map);
            }
            map[hero.getX()][hero.getY()] = ' ';
            map[next_x][next_y] = 'H';
            hero.setX(next_x);
            hero.setY(next_y);
            for (int i = 0; i < guardNumber; ++i){
                guards[i].draw(map);
            }
            if(type == 0)
                lever.draw(map);
            return true;
        }

        return false;
    }

    private char user_input() {

        //Asking the user for a direction input to move the hero
        System.out.print("Enter a direction with 'w'(up), 'a'(left), 's'(down), 'd'(right), 'e' to exit the game : ");
        Scanner reader = new Scanner(System.in);
        //saving the first char of the input to a variable c
        char input = reader.findInLine(".").charAt(0);


        return input;
    }


    private void print_map() {

        //Printing the map
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                if(j != 0) {
                    System.out.print(' ');
                }
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        }

    }
}
