package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

public abstract class Guard extends GameCharacter {

    protected ArrayList<Character> path = new ArrayList<Character>();
    protected int mov;
    protected char symbol;
    private boolean way;

    //constructor
    public Guard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord);
        mov = 0;
        path = p;
        symbol = 'G';
        way = true;
    }

    public void setMov(int mov ){
        this.mov = mov;
    }

    public int getMov(){
        return mov;
    }

    public ArrayList<Character> getPath() {
        return path;
    }

    public boolean getWay() {
        return way;
    }

    public char getSymbol() {
        return symbol;
    };

    public abstract void update(Character[][] map);

    public void draw(Character[][] map){
        map[x][y] = symbol;
    }

    public void move(Character[][] map,int nextX, int nextY) {
        if (map[nextX][nextY] == ' ') {
            x = nextX;
            y = nextY;
        }
    }

    public void bidirectionalMovement( Character[][] map){
        switch (path.get(mov)) {
            case 'u':
                if(way) move(map, x - 1, y);
                else move(map, x + 1, y);
                break;
            case 'l':
                if(way) move(map, x, y - 1);
                else move(map, x, y + 1);
                break;
            case 'd':
                if(way) move(map, x + 1, y);
                else move(map, x - 1, y);
                break;
            case 'r':
                if(way)move(map, x, y + 1);
                else move(map, x, y - 1);
                break;
        }
    }

    public void wayDecision(int randnum){
        switch(randnum){
            case 0:
                if(way) {
                    mov -= 1;
                    if (mov == -1)
                        mov = path.size() - 1;
                    way = false;
                }else {
                    mov += 1;
                    if (mov == path.size())
                        mov = 0;
                    way = true;
                }
                break;
            default:
                break;
        }
    }

    public void movementIncrementation(){
        if(way) {
            mov += 1;
            if (mov == path.size())
                mov = 0;
        } else {
            mov -= 1;
            if (mov == -1)
                mov = path.size() - 1;
        }
    }

}

