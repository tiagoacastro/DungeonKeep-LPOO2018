package dkeep.logic;

public class Lever extends GameObject {

    /**
     * Lever constructor
     * @param xCoord    x coordinate
     * @param yCoord    y coordinate
     */
    public Lever (int xCoord, int yCoord){
        super(xCoord, yCoord);
    }

    Lever (Lever l){
        super(l.getX(), l.getY());
    }

    /**
     * Lever draw(implements abstract function)
     * @param map   map where it will be drawn
     */
    public void draw(Character[][] map){
        map[x][y] = 'k';
    }
}
