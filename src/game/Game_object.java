package game;

public class Game_object {

    public int x;
    public int y;
    public boolean opened;

    Game_object() {}

    Game_object(int xcoord, int ycoord) {
        x = xcoord;
        y = ycoord;
        opened = false;
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    void setX(int xcoord){
        x = xcoord;
    }
    void setY(int ycoord){
        y = ycoord;
    }
}
