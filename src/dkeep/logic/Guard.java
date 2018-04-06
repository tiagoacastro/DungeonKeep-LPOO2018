package dkeep.logic;

import java.util.ArrayList;

/**
 * Guard class
 */
public abstract class Guard extends GameCharacter {

    ArrayList<Character> path = new ArrayList<Character>();
    int mov;
    char symbol;
    private boolean way;

    /**
     * Guard Constructor
     * @param xCoord    x coordinate
     * @param yCoord    y coordinate
     * @param p         path
     */
    public Guard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord);
        mov = 0;
        path = p;
        symbol = 'G';
        way = true;
    }

    /**
     * setter for the current movement
     * @param mov   current movement
     */
    public void setMov(int mov ){
        this.mov = mov;
    }

    /**
     * Getter for the current movement
     * @return  current movement
     */
    public int getMov(){
        return mov;
    }

    /**
     * Getter for the path
     * @return  Path
     */
    public ArrayList<Character> getPath() {
        return path;
    }

    /**
     * Getter for the way on which it is moving
     * @return  way
     */
    public boolean getWay() {
        return way;
    }

    /**
     * Getter for the symbol
     * @return  symbol
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * abstract function to move the Guard
     * @param map   map where it will be moved
     */
    public abstract void update(Character[][] map);

    /**
     * draw for the guard (implementation from abstract function)
     * @param map   map where it will be drawn
     */
    public void draw(Character[][] map){
        map[x][y] = symbol;
    }

    void move(Character[][] map,int nextX, int nextY) {
        if (map[nextX][nextY] == ' ') {
            x = nextX;
            y = nextY;
        }
    }

    void bidirectionalMovement( Character[][] map){
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

    void wayDecision(int randnum){
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

    void movementIncrementation(){
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

