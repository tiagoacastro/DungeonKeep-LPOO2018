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

        print_map();

        user_move(h, g);


    }

    public static void create_map() {
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
