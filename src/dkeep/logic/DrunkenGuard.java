package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * DrunkenGuard class
 */
public class DrunkenGuard extends Guard{

    private boolean sleeping;
    private int timer;

    /**
     * Drunken Guard constructor with path and coordinates
     * @param xCoord    x coordinate
     * @param yCoord    y coordinate
     * @param p         patrol path
     */
    public DrunkenGuard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord, p);
        sleeping = false;
        timer = 3;
    }

    DrunkenGuard(DrunkenGuard g) {
        super(g.getX(), g.getY(), g.getPath());
        sleeping = false;
        timer = 3;
    }

    /**
     * Checks if the guard is sleeping
     * @return      if it's sleeping or not
     */
    public boolean sleeping(){
        return sleeping;
    }

    /**
     * updates guard position
     * @param map   map where guard is moving
     */
    public void update(Character[][] map){
        Random rand = new Random();
        int n = 5;
        int randnum = rand.nextInt(n);

        if(sleeping) {
            timer--;
            if(timer == 0) {
                timer = 3;
                wake();
            }
        }else
            switch(randnum){
                case 0:
                    if(!sleeping)
                      sleep();
                     break;
                default:
                    break;
            }

        if(!sleeping) {
            bidirectionalMovement(map);

            movementIncrementation();
        }
    }

    private void sleep(){
        sleeping = true;
        symbol = 'g';
    }

    /**
     * wake guard up
     */
    public void wake(){
        Random rand = new Random();
        int n = 2;
        int randnum = rand.nextInt(n);

        wayDecision(randnum);

        sleeping = false;
        symbol = 'G';
    }
}
