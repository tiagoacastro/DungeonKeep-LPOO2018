package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

public class SuspiciousGuard extends Guard{

    public SuspiciousGuard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord, p);
    }

    public void update(Character[][] map){
        Random rand = new Random();
        int n = 10;
        int randnum = rand.nextInt(n);

        wayDecision(randnum);

        bidirectionalMovement(map);

        movementIncrementation();
    }

    public void move(Character[][] map,int nextX, int nextY) {
        if (map[nextX][nextY] == ' ') {
            x = nextX;
            y = nextY;
        }
    }

    public void draw(Character[][] map){
        map[x][y] = symbol;
    }
}

