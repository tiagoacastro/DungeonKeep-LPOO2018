package logic;
import java.util.Random;

public class Ogre extends GameCharacter {

    private int xClub;
    private int yClub;
    private char symbol;
    private char clubSymbol;

    //constructor
    public Ogre(int xCoord, int yCoord, int xClubCoord, int yClubCoord) {
        super(xCoord, yCoord);
        xClub = xClubCoord;
        yClub = yClubCoord;
        symbol = '0';
        clubSymbol = '*';
    }

    public int getClubX() {
        return xClub;
    }

    public int getClubY() {
        return yClub;
    }

    public char getSymbol() {
        return symbol;
    }

    public char getClubSymbol() {
        return clubSymbol;
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
            symbol = '0';
            map[next_x][next_y] = symbol;
        }

        if (map[next_x][next_y] == 'k' || map[next_x][next_y] == '$') {
            map[x][y] = ' ';
            x=next_x;
            y=next_y;
            drawClub(map);
            symbol = '$';
            map[next_x][next_y] = symbol;
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
            clubSymbol = '*';
            map[next_x][next_y] = clubSymbol;
            xClub=next_x;
            yClub=next_y;
        }

        if (map[next_x][next_y] == 'k' || map[next_x][next_y] == '$') {
            map[xClub][yClub] = ' ';
            clubSymbol = '$';
            map[next_x][next_y] = clubSymbol;
            xClub=next_x;
            yClub=next_y;
        }

        if (map[next_x][next_y] == 'X') {
            drawClub(map);
        }
    }
}