package logic;

import java.util.Scanner;
import cli.UserInput;

public class Level {

    private Character[][] map;
    private Hero hero;
    private Guard guards[] = new Guard[10];
    private int guardNumber;
    private Ogre ogres[];
    private int ogreNumber;
    private Door doors[] = new Door[10];
    private int doorNumber;
    private Lever lever;
    private Key key;
    private int type; //0 is lever level, 1 is key level

    Level(Character[][] m, Hero h, int t) {
        map = m;
        hero = h;
        guardNumber=0;
        ogreNumber=0;
        type = t;
    }

    void addGuard(Guard g) {
        guards[guardNumber] = g;
        guardNumber++;
    }

    void addLever(Lever l) {
        lever = l;
    }

    void addKey(Key k){
        key =k;
    }

    void addDoor(Door d){
        doors[doorNumber] = d;
        doorNumber++;
    }

    void addOgre(Ogre o) {
        ogres[ogreNumber] = o;
        ogreNumber++;
    }

    int getType(){
        return type;
    }

    Guard[] getGuards() {
        return guards;
    }

    Ogre[] getOgres() {
        return ogres;
    }

    Character[][] getMap() {
        return map;
    }

    void user_move() {

        boolean exit = true;
        UserInput.Direction input;
        do {

            input = UserInput.user_input();

            switch (input) {

                case UP:
                    if( move(hero.getX()-1,hero.getY()) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                case LEFT:
                    if( move(hero.getX(),hero.getY()-1) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                case DOWN:
                    if( move(hero.getX()+1,hero.getY()) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                case RIGHT:
                    if( move(hero.getX(),hero.getY()+1) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                default:
                    continue;
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

        if(type == 0) {


            if (map[next_x][next_y] == ' ') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(next_x);
                hero.setY(next_y);

                for (int j = 0; j < doorNumber; ++j) {
                    doors[j].draw(map);
                }

                for (int i = 0; i < guardNumber; ++i) {
                    guards[i].draw(map);
                }

                map[next_x][next_y] = 'H';

                lever.draw(map);

                return false;
            }


            if (map[next_x][next_y] == 'k') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(next_x);
                hero.setY(next_y);

                for (int j = 0; j < doorNumber; ++j) {
                    if (type == 0)
                        doors[j].open();
                    doors[j].draw(map);
                }

                for (int i = 0; i < guardNumber; ++i) {
                    guards[i].draw(map);
                }

                map[next_x][next_y] = 'H';

                return false;
            }


            if (map[next_x][next_y] == 'S') {
                for (int j = 0; j < doorNumber; ++j) {
                    doors[j].draw(map);
                }

                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(next_x);
                hero.setY(next_y);

                for (int i = 0; i < guardNumber; ++i) {
                    guards[i].draw(map);
                }

                map[next_x][next_y] = 'H';

                lever.draw(map);

                return true;
            }




        } else {



            if (map[next_x][next_y] == ' ') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(next_x);
                hero.setY(next_y);

                for (int j = 0; j < doorNumber; ++j) {
                    doors[j].draw(map);
                }

                for (int i = 0; i < guardNumber; ++i) {
                    ogres[i].draw(map);
                }

                if (key.check()) {
                    map[next_x][next_y] = 'K';
                } else {
                    map[next_x][next_y] = 'H';
                }

                key.draw(map);

                return false;
            }

            if (map[next_x][next_y] == 'k') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(next_x);
                hero.setY(next_y);

                for (int j = 0; j < doorNumber; ++j) {
                    if (type == 0)
                        doors[j].open();
                    doors[j].draw(map);
                }

                for (int i = 0; i < guardNumber; ++i) {
                    ogres[i].draw(map);
                }

                key.grab();
                map[next_x][next_y] = 'K';

                return false;
            }

            if (map[next_x][next_y] == 'S') {
                for (int j = 0; j < doorNumber; ++j) {
                    doors[j].draw(map);
                }

                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(next_x);
                hero.setY(next_y);

                for (int i = 0; i < guardNumber; ++i) {
                    ogres[i].draw(map);
                }

                if (key.check()) {
                    map[next_x][next_y] = 'K';
                } else {
                    map[next_x][next_y] = 'H';
                }

                key.draw(map);

                return true;
            }

            if (map[next_x][next_y] == 'I') {
                for (int j = 0; j < doorNumber; ++j) {
                    if (doors[j].getX() == next_x && doors[j].getY() == next_y) {
                        doors[j].open();
                    }
                    doors[j].draw(map);
                }

                for (int i = 0; i < guardNumber; ++i) {
                    ogres[i].draw(map);
                }
                return false;
            }
        }

        return false;
    }
    /*
    private void hero_caught_key(int next_x,int next_y) {

        if (key.check()) {
            map[next_x][next_y] = 'K';
        } else
            map[next_x][next_y] = 'H';

        return;

    }
    */

    void print_map() {

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