package dkeep.logic;

public class Key extends GameObject {

    private boolean grabbed;

    /**
     * Key constructor
     * @param xCoord    x coordinate
     * @param yCoord    y coordinate
     */
    public Key (int xCoord, int yCoord){
        super(xCoord, yCoord);
        grabbed = false;
    }

    /**
     * Key copy constructor
     * @param k     key that will be copied
     */
    public Key (Key k){
        super(k.getX(), k.getY());
        grabbed = false;
    }

    /**
     * Draws the door (implements abstract function)
     * @param map   map where it will be drawn
     */
    public void draw(Character[][] map){
        if(!grabbed)
            map[x][y] = 'k';
    }

    /**
     * Checks if the key was grabbed or not
     * @return  grabbed
     */
    public boolean check(){
        return grabbed;
    }

    /**
     * grabs the key
     */
    public void grab(){
        grabbed = true;
    }
}
