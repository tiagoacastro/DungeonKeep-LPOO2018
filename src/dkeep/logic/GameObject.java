package dkeep.logic;

public class GameObject {

    protected int x;
    protected int y;

    GameObject() {
    }

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

}