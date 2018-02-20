package game;

public class Guard extends Game_Character{

    public char path[];
    public int mov;

    //constructor
    public Guard(int xcoord, int ycoord) {
        x = xcoord;
        y = ycoord;
        path = new char[]{'l','d','d','d','d','l','l','l','l','l','l','d','r','r','r','r','r','r','r','u','u','u','u','u'};
        mov = 0;
    }
}

