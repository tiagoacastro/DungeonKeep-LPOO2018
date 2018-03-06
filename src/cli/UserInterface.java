package cli;

import java.util.Scanner;

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
}