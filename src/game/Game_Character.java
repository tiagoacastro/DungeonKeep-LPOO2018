package game;

public class Game_Character {
    public int x;
    public int y;

    Game_Character() {

    }

    //constructor
    Game_Character(int xcoord, int ycoord) {
        x = xcoord;
        y = ycoord;
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
