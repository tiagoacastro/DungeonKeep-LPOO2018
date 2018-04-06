package dkeep.logic;

public class Door extends GameObject {

    private char symbol;

    /**
     * Door constructor with both coords
     * @param xCoord    x coordinate in the map
     * @param yCoord    y coordinate in the map
     */
    public Door (int xCoord, int yCoord){
        super(xCoord, yCoord);
        symbol = 'I';
    }

    /** Door copy constructor
     * @param d     door which attributes will be copied
     */
    public Door (Door d){
        super(d.getX(), d.getY());
        symbol = 'I';
    }

    /**
     * Opens door changing its symbol
     * @param map   map where the Door will be opened
     */
    public void open(Character[][] map) {
        symbol = 'S';
        draw(map);
    }

    /**
     * Draws the door
     * @param map   map where the Door will be drawn
     */
    public void draw(Character[][] map){
        map[x][y] = symbol;
    }

    /**
     * Get Door symbol
     * @return  symbol
     */
    public char getSymbol() {
        return this.symbol;
    }
}
