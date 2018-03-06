package dkeep.logic;

public class Hero extends GameCharacter {

    private char symbol;
    private boolean armed;

    public Hero(int xCoord, int yCoord) {
        super(xCoord, yCoord);
        symbol = 'H';
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char s) { symbol = s; }

    public void arm() {
        armed = true;
        symbol = 'A';
    }

    public boolean armed(){
        return armed;
    }
}

