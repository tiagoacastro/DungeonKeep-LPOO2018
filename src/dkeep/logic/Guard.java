package dkeep.logic;

import java.util.ArrayList;

public abstract class Guard extends GameCharacter {

    protected ArrayList<Character> path = new ArrayList<Character>();
    protected int mov;
    protected char symbol;

    //constructor
    public Guard(int xCoord, int yCoord, ArrayList<Character> p) {
        super(xCoord, yCoord);
        mov = 0;
        path = p;
        symbol = 'G';
    }

    public char getSymbol() {
        return symbol;
    };

    public abstract void update(Character[][] map);

    public abstract void draw(Character[][] map);

}

