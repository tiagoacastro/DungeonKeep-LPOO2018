package game;

public class Key extends Game_object {

    private boolean grabbed;

    public Key (int xcoord, int ycoord){
        x = xcoord;
        y = ycoord;
        grabbed = false;
    }

    void draw(Character[][] map){
        if(!grabbed)
        map[x][y] = 'k';
    }

    boolean check(){
        return grabbed;
    }

    void grab(){
        grabbed = true;
    }
}
