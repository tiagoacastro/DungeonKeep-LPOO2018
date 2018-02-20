package game;

public class Door extends Game_object {

    public int idgroup;

    public Door (int xcoord, int ycoord, int ID){
        x = xcoord;
        y = ycoord;
        idgroup = ID;
        opened = false;
    }
}
