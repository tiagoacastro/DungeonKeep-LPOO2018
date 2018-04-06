package dkeep.logic;
import java.util.ArrayList;

public class RookieGuard extends Guard {

    /**
     * Rookie Guard constructor
     * @param xCoord    x coordinate
     * @param yCoord    y coordinate
     * @param p         path
     */
    public RookieGuard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord, p);
    }

    RookieGuard(RookieGuard g) {
        super(g.getX(), g.getY(), g.getPath());
    }

    /**
     * moves the guard (implements abstract method)
     * @param map   map where it will be moved
     */
    public void update(Character[][] map) {
        switch (path.get(mov)) {
            case 'u':
                move(map, x - 1, y);
                break;
            case 'l':
                move(map, x, y - 1);
                break;
            case 'd':
                move(map, x + 1, y);
                break;
            case 'r':
                move(map, x, y + 1);
                break;
        }
        mov += 1;
        if (mov == 24)
            mov = 0;
    }
}
