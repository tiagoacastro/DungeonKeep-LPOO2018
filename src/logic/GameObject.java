package logic;

public class GameObject {

    protected int x;
    protected int y;

    GameObject() {
    }

    GameObject(int xcoord, int ycoord) {
        x = xcoord;
        y = ycoord;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int xcoord) {
        x = xcoord;
    }

    public void setY(int ycoord) {
        y = ycoord;
    }

}