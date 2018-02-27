package logic;

public class Lever extends GameObject {

    public Lever (int xcoord, int ycoord){
        x = xcoord;
        y = ycoord;
    }

    public void draw(Character[][] map){
        map[x][y] = 'k';
    }
}
