package game;

public class Lever extends Game_object {

    public Lever (int xcoord, int ycoord){
        x = xcoord;
        y = ycoord;
    }

    void draw(Character[][] map){
        map[x][y] = 'k';
    }
}
