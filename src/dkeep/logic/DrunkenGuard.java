package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

public class DrunkenGuard extends Guard{

    private boolean sleeping;

    public DrunkenGuard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord, p);
        sleeping = false;
    }

    public boolean sleeping(){
        return sleeping;
    }

    public void update(Character[][] map){
        Random rand = new Random();
        int n = 5;
        int randnum = rand.nextInt(n);

        switch(randnum){
            case 0:
                if(!sleeping)
                    sleep();
                break;
            default:
                if(sleeping)
                    wake();
                break;
        }

        if(!sleeping) {
            bidirectionalMovement(map);

            movementIncrementation();
        }
    }

    public void move(Character[][] map,int nextX, int nextY) {
        if (map[nextX][nextY] == ' ') {
            x = nextX;
            y = nextY;
        }
    }

    private void sleep(){
        sleeping = true;
        symbol = 'g';
    }

    private void wake(){
        Random rand = new Random();
        int n = 2;
        int randnum = rand.nextInt(n);

        wayDecision(randnum);

        sleeping = false;
        symbol = 'G';
    }

    public void draw(Character[][] map){
        map[x][y] = symbol;
    }
}
