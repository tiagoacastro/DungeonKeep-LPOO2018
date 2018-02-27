package logic;

public class Door extends GameObject {

    private char symbol;

    public Door (int xcoord, int ycoord){
        x = xcoord;
        y = ycoord;
        symbol = 'I';
    }

    public void open() {
        symbol = 'S';
    }

    public void draw(Character[][] map){
        map[x][y] = symbol;
    }
}
