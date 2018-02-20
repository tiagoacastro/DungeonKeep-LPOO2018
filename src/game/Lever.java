package game;

public class Lever extends Game_object {

    public int IDgroup;

    public Lever (int xcoord, int ycoord, int id){
        x = xcoord;
        y = ycoord;
        IDgroup = id;
        opened = false;
    }
}
