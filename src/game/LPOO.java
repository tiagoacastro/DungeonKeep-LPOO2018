package game;
import java.util.Scanner;

public class LPOO {

    //Creating the game map using a bi-dimensional array of integers
    static Character[][] map = new Character[][] {

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
    } ;

    public static void main(String[] args) {

        Hero h = new Hero(1, 1);
        map[h.getX()][h.getY()] = 'H';
        Guard g = new Guard(1,8);
        map[g.getX()][g.getY()] = 'G';

        Door d1 = new Door(5,0,1);
        map[d1.getX()][d1.getY()] = 'I';
        Door d2 = new Door(6,0,1);
        map[d2.getX()][d2.getY()] = 'I';
        Lever l = new Lever(8,7,1);
        map[l.getX()][l.getY()] = 'k';

        print_map();

        user_move(h, g);


    }

    public static char user_input() {

        //Asking the user for a direction input to move the hero
        System.out.print("Enter a direction with 'w'(up), 'a'(left), 's'(down), 'd'(right), 'e' to exit the game : ");
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
                    if( move(h, h.getX()-1,h.getY()) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                break;
                case 'a':
                   if( move(h, h.getX(),h.getY()-1) ) {
                       System.out.println("You won the game! Congrats ");
                       exit = false;
                   }
                    break;
                case 's':
                    if( move(h, h.getX()+1,h.getY()) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
                    }
                    break;
                case 'd':
                    if( move(h, h.getX(),h.getY()+1) ) {
                        System.out.println("You won the game! Congrats ");
                        exit = false;
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

            if(hero_x != 0 && hero_y != 0)
            if ((map[hero_x + 1][hero_y] == 'G') || (map[hero_x - 1][hero_y] == 'G') || (map[hero_x][hero_y +1] == 'G') || (map[hero_x][hero_y-1]== 'G')) {
                System.out.println("The guard has restrained you, you LOST ! :( ");
                exit = false;
            }

            print_map();

        } while (exit);
    }

    public static boolean move(Hero h,int next_x, int next_y) {
        if (map[next_x][next_y] == ' ') {
            map[h.getX()][h.getY()] = ' ';
            map[next_x][next_y] = 'H';
            h.setX(next_x);
            h.setY(next_y);
            return false;
        }

        if (map[next_x][next_y] == 'k') {
            map[h.getX()][h.getY()] = ' ';
            map[next_x][next_y] = 'H';
            h.setX(next_x);
            h.setY(next_y);
            map[5][0] = 'S';
            map[6][0] = 'S';
            return false;

        }

        if (map[next_x][next_y] == 'S') {
            map[h.getX()][h.getY()] = ' ';
            map[next_x][next_y] = 'H';
            h.setX(next_x);
            h.setY(next_y);
            return true;

        }
        return false;
    }

    public static void print_map() {

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
