package dkeep.logic;

import java.io.Serializable;

/**
 * Hero class
 */
public class Hero implements Serializable {

    private char symbol;
    private boolean armed;
    private int x;
    private int y;

    /**
     * Hero constructor
     * @param xCoord    x coordinate
     * @param yCoord    y coordinate
     */
    public Hero(int xCoord, int yCoord) {
        x=xCoord;
        y=yCoord;

        symbol = 'H';
    }

    /**
     * Hero copy constructor
     * @param h     Hero that will be copied
     */
    public Hero(Hero h) {
        x=h.getX();
        y=h.getY();

        symbol = 'H';

        if(h.armed())
            arm();
    }

    /**
     * Setter for the hero symbol
     * @param s     new symbol
     */
    public void setSymbol(char s) { symbol = s; }

    /**
     * Arms the hero
     */
    public void arm() {
        armed = true;
        symbol = 'A';
    }

    /**
     * Checks if the hero is armed
     * @return  if it is armed or not
     */
    public boolean armed(){
        return armed;
    }

    /**
     * changes the Symbol for K
     */
    public void grabsKey() {symbol = 'K';}

    /**
     * Getter for the x coordinate
     * @return  x coord
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for the x coordinate
     * @param x     x coord
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for the y coordinate
     * @return  y coord
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for the y coordinate
     * @param y     y coord
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * draw for the hero
     * @param map   map where it will be drawn
     */
    public void draw(Character[][] map){
        map[x][y] = symbol;
    }

    /**
     * Getter for the hero symbol
     * @return  symbol
     */
    public char getSymbol() {
        return this.symbol;
    }
}

