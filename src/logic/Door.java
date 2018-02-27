package logic;

public class Door extends GameObject {

    private char symbol;

    public Door (int xcoord, int ycoord){
        x = xcoord;
        y = ycoord;
        symbol = 'I';
    }

    void open() {
        symbol = 'S';
    }

    void draw(Character[][] map){
        map[x][y] = symbol;
    }
}
