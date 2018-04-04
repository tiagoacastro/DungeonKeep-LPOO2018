package dkeep.logic;

import java.io.Serializable;

public abstract class GameObject implements Serializable {

    protected int x;
    protected int y;

    GameObject(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int xCoord) {
        x = xCoord;
    }

    public void setY(int yCoord) {
        y = yCoord;
    }

    public abstract void draw(Character[][] map);
}