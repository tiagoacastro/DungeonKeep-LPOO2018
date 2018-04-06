package dkeep.logic;

import java.io.Serializable;

/**
 * GameCharacter class
 */
public abstract class GameCharacter implements Serializable {
    protected int x;
    protected int y;

    GameCharacter(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    /**
     * Getter for the x coordinate
     * @return  x coord
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y coordinate
     * @return  y coord
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for the x coordinate
     * @param xCoord    x coord
     */
    public void setX(int xCoord) {
        x = xCoord;
    }

    /**
     * Setter for the y coordinate
     * @param yCoord   y coord
     */
    public void setY(int yCoord) {
        y = yCoord;
    }

    /**
     * abstract method for moving the character
     * @param map   map where it will be moved
     */
    public abstract void update(Character[][] map);

    /**
     * abstract method for drawing the character
     * @param map   map where it will be drawn
     */
    public abstract void draw(Character[][] map);
}