package game;
import java.util.Random;

public class Ogre extends Game_Character {

    //constructor
    public Ogre(int xcoord, int ycoord) {
        x = xcoord;
        y = ycoord;
    }

    void draw(Character[][] map){
        Random rand = new Random();
        int n = 4;
        int randnum = rand.nextInt(n);

        switch(n){
            case 0:

                break;
        }
    }

    private void move(Character[][] map, int next_x, int next_y) {
        if (map[next_x][next_y] == ' ') {
            map[x][y] = ' ';
            map[next_x][next_y] = '0';
            x=next_x;
            y=next_y;
        }

        if (map[next_x][next_y] == 'k') {
            map[x][y] = ' ';
            map[next_x][next_y] = '$';
            x=next_x;
            y=next_y;
        }
    }
}
