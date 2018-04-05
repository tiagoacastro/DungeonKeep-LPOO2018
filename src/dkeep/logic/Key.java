package dkeep.logic;

public class Key extends GameObject {

    private boolean grabbed;

    public Key (int xCoord, int yCoord){
        super(xCoord, yCoord);
        grabbed = false;
    }

    public Key (Key k){
        super(k.getX(), k.getY());
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
}
