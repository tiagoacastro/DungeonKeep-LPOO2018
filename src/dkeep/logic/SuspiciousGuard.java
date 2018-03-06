package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

public class SuspiciousGuard extends Guard{

    private boolean way;

    public SuspiciousGuard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord, p);
        way = true;
    }

    public void draw(Character[][] map){
        Random rand = new Random();
        int n = 10;
        int randnum = rand.nextInt(n);

        switch(randnum){
            case 0:
                if(way) {
                    mov -= 1;
                    if (mov == -1)
                        mov = path.size() - 1;
                    way = false;
                }else {
                    mov += 1;
                    if (mov == path.size())
                        mov = 0;
                    way = true;
                }
                break;
            default:
                break;
        }
        switch (path.get(mov)) {
            case 'u':
                if(way)
                    move(map, x - 1, y);
                else
                    move(map, x + 1, y);
                break;
            case 'l':
                if(way)
                    move(map, x, y - 1);
                else
                    move(map, x, y + 1);
                break;
            case 'd':
                if(way)
                    move(map, x + 1, y);
                else
                    move(map, x - 1, y);
                break;
            case 'r':
                if(way)
                    move(map, x, y + 1);
                else
                    move(map, x, y - 1);
                break;
        }
        if(way) {
            mov += 1;
            if (mov == path.size())
                mov = 0;
        } else {
            mov -= 1;
            if (mov == -1)
                mov = path.size()-1;
        }
    }

    private void move(Character[][] map,int nextX, int nextY) {
        if (map[nextX][nextY] == ' ') {
            map[x][y] = ' ';
            map[nextX][nextY] = symbol;
            x = nextX;
            y = nextY;
        }
    }
}

