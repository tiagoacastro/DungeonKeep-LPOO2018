package dkeep.logic;

public class Lever extends GameObject {

    public Lever (int xCoord, int yCoord){
        super(xCoord, yCoord);
    }

    public Lever (Lever l){
        super(l.getX(), l.getY());
    }

    public void draw(Character[][] map){
        map[x][y] = 'k';
    }
}
