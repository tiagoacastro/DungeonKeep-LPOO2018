package logic;

import java.util.ArrayList;

public class RookieGuard extends Guard{

    public RookieGuard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord, p);
    }

    public void draw(Character[][] map){
        switch (path.get(mov)) {
            case 'u':
                move(map, x - 1, y);
                break;
            case 'l':
                move(map, x, y - 1);
                break;
            case 'd':
                move(map, x + 1, y);
                break;
            case 'r':
                move(map, x, y + 1);
                break;
        }
        mov += 1;
        if (mov == 24)
            mov = 0;
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
