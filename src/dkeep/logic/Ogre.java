package dkeep.logic;
import java.util.Random;

public class Ogre extends GameCharacter {

    private int xClub;
    private int yClub;
    private char symbol;
    private char clubSymbol;
    private boolean stunned;
    private int stunTimer;

    //constructor
    public Ogre(int xCoord, int yCoord, int xClubCoord, int yClubCoord) {
        super(xCoord, yCoord);
        xClub = xClubCoord;
        yClub = yClubCoord;
        symbol = '0';
        clubSymbol = '*';
        stunned = false;
        stunTimer = 0;
    }

    public boolean isStunned() {
        return stunned;
    }

    public int getClubX() {
        return xClub;
    }

    public int getClubY() {
        return yClub;
    }

    public void stun(Character[][] map){
        stunned = true;
        stunTimer = 0;
        symbol = '8';
    }

    public void update(Character[][] map){
        if(!stunned) {
            Random rand = new Random();
            int n = 4;
            int randnum = rand.nextInt(n);
            ogreWhereToMove(map, randnum);

        } else {
            updateClub(map);
            switch (stunTimer){
                case 0:
                    stunTimer++;
                    break;
                case 1:
                    stunTimer = 0;
                    stunned = false;
                    symbol = '0';
                    break;
            }
        }
    }

    public void ogreWhereToMove(Character[][] map, int randnum) {
        switch (randnum) {
            case 0:
                move(map, x + 1, y);
                break;
            case 1:
                move(map, x, y + 1);
                break;
            case 2:
                move(map, x - 1, y);
                break;
            case 3:
                move(map, x, y - 1);
                break;
        }
    }

    private void move(Character[][] map, int next_x, int next_y) {
        if (map[next_x][next_y] == ' ' || map[next_x][next_y] == '*') {
            x=next_x;
            y=next_y;
            updateClub(map);
            symbol = '0';
        } else if (map[next_x][next_y] == 'k') {
            x = next_x;
            y = next_y;
            updateClub(map);
            symbol = '$';
        } else if (map[next_x][next_y] == 'X' || map[next_x][next_y] == 'I' || map[next_x][next_y] == 'S') {
            update(map);
        }
    }


    public void updateClub (Character[][] map){
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
            clubSymbol = '*';
            xClub=next_x;
            yClub=next_y;
        }

       else if (map[next_x][next_y] == 'k') {
            clubSymbol = '$';
            xClub=next_x;
            yClub=next_y;
        }

       else if (map[next_x][next_y] == 'X' || map[next_x][next_y] == 'I' || map[next_x][next_y] == 'S') {
            updateClub(map);
        }
    }

    public void draw(Character[][] map){
        map[x][y] = symbol;
    }

    public void drawClub(Character[][] map){
        map[xClub][yClub] = clubSymbol;
    }
}