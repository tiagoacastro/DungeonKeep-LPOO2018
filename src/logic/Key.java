package logic;

public class Key extends GameObject {

    private boolean grabbed;

    public Key (int xcoord, int ycoord){
        x = xcoord;
        y = ycoord;
        grabbed = false;
    }

    public void draw(Character[][] map){
        if(!grabbed)
        map[x][y] = 'k';
    }

    public boolean check(){
        return grabbed;
    }

    public void grab(){
        grabbed = true;
    }

    public void use() {
        grabbed = false;
    };
}
