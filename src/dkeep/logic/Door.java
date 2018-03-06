package dkeep.logic;

public class Door extends GameObject {

    private char symbol;

    public Door (int xCoord, int yCoord){
        super(xCoord, yCoord);
        symbol = 'I';
    }

    public void open() {
        symbol = 'S';
    }

    public void draw(Character[][] map){
        map[x][y] = symbol;
    }
}
