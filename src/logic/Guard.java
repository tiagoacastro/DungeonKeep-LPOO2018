package logic;

import java.util.Vector;

public abstract class Guard extends GameCharacter {

    protected Vector<Character> path = new Vector<Character>();
    protected int mov;
    protected char symbol;

    //constructor
    public Guard(int xCoord, int yCoord, Vector<Character> p) {
        super(xCoord, yCoord);
        mov = 0;
        path = p;
        symbol = 'G';
    }

    public char getSymbol() {
        return symbol;
    };

    abstract void draw(Character[][] map);
}

