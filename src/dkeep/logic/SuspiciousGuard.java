package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

public class SuspiciousGuard extends Guard{

    /**
     * Suspicious guard constructor
     * @param xCoord    x coordinate
     * @param yCoord    y coordinate
     * @param p         path
     */
    public SuspiciousGuard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord, p);
    }

    SuspiciousGuard(SuspiciousGuard g) { super(g.getX(), g.getY(), g.getPath()); }

    /**
     * Update the guard position (implements abstract method)
     * @param map   map where it will be moved
     */
    public void update(Character[][] map){
        Random rand = new Random();
        int n = 10;
        int randnum = rand.nextInt(n);

        wayDecision(randnum);

        bidirectionalMovement(map);

        movementIncrementation();
    }
}

