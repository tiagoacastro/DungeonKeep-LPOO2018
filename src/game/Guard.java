package game;

public class Guard extends Game_Character{

    private char path[];
    private int mov;

    //constructor
    public Guard(int xcoord, int ycoord, char[] p) {
        x = xcoord;
        y = ycoord;
        path = p;
        mov = 0;
    }

    void draw(Character[][] map){
        switch (path[mov]) {
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
        mov = mov + 1;
        if (mov == 24)
            mov = 0;
    }

    private void move(Character[][] map,int next_x, int next_y) {
        if (map[next_x][next_y] == ' ') {
            map[x][y] = ' ';
            map[next_x][next_y] = 'G';
            x = next_x;
            y = next_y;
        }
    }
}

