package logic;

public class Lever extends GameObject {

    public Lever (int xCoord, int yCoord){
        super(xCoord, yCoord);
    }

    public void draw(Character[][] map){
        map[x][y] = 'k';
    }
}
