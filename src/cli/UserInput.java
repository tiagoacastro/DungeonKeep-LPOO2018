package cli;

import java.util.Scanner;
import logic.*;

public class UserInput {

    public enum Direction {
        UP, LEFT, DOWN, RIGHT, NONE
    }

    public static Direction user_input() {


        //Asking the user for a direction input to move the hero
        System.out.print("Enter a direction with 'w'(up), 'a'(left), 's'(down), 'd'(right), 'e' to exit the logic : ");
        Scanner reader = new Scanner(System.in);
        //saving the first char of the input to a variable c
        char input = reader.findInLine(".").charAt(0);

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
}