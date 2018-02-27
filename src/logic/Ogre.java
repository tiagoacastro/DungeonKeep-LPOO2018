package logic;
import java.util.Random;

public class Ogre extends GameCharacter {

    private int xClub;
    private int yClub;

    //constructor
    public Ogre(int xcoord, int ycoord, int xClubCoord, int yClubCoord) {
        x = xcoord;
        y = ycoord;
        xClub = xClubCoord;
        yClub = yClubCoord;
    }

    public int getClubX() {
        return xClub;
    }

    public int getClubY() {
        return yClub;
    }

    void draw(Character[][] map){
        Random rand = new Random();
        int n = 4;
        int randnum = rand.nextInt(n);

        switch(randnum){
            case 0:
                move(map, x+1, y);
                break;
            case 1:
                move(map, x, y+1);
                break;
            case 2:
                move(map, x-1, y);
                break;
            case 3:
                move(map, x, y-1);
                break;
        }
    }

    private void move(Character[][] map, int next_x, int next_y) {
        if (map[next_x][next_y] == ' ' || map[next_x][next_y] == '*') {
            map[x][y] = ' ';
            x=next_x;
            y=next_y;
            drawClub(map);
            map[next_x][next_y] = '0';
        }

        if (map[next_x][next_y] == 'k' || map[next_x][next_y] == '$') {
            map[x][y] = ' ';
            x=next_x;
            y=next_y;
            drawClub(map);
            map[next_x][next_y] = '$';
        }

        if (map[next_x][next_y] == 'X') {
            draw(map);
        }
    }

    private void drawClub (Character[][] map){
        Random rand = new Random();
        int n = 4;
        int randnum = rand.nextInt(n);

        switch(randnum){
            case 0:
                clubMove(map, x+1, y);
                break;
            case 1:
                clubMove(map, x, y+1);
                break;
            case 2:
                clubMove(map, x-1, y);
                break;
            case 3:
                clubMove(map, x, y-1);
                break;
        }
    }

    private void clubMove(Character[][] map, int next_x, int next_y) {
        if (map[next_x][next_y] == ' ') {
            map[xClub][yClub] = ' ';
            map[next_x][next_y] = '*';
            xClub=next_x;
            yClub=next_y;
        }

        if (map[next_x][next_y] == 'k' || map[next_x][next_y] == '$') {
            map[xClub][yClub] = ' ';
            map[next_x][next_y] = '$';
            xClub=next_x;
            yClub=next_y;
        }

        if (map[next_x][next_y] == 'X') {
            drawClub(map);
        }
    }
}