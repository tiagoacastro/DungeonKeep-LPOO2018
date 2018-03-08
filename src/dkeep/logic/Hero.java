package dkeep.logic;

public class Hero {

    private char symbol;
    private boolean armed;
    private int x;
    private int y;

    public Hero(int xCoord, int yCoord) {
        x=xCoord;
        y=yCoord;

        symbol = 'H';
    }

    public void setSymbol(char s) { symbol = s; }

    public void arm() {
        armed = true;
        symbol = 'A';
    }

    public boolean armed(){
        return armed;
    }

    public void grabsKey() {symbol = 'K';}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Character[][] map){
        map[x][y] = symbol;
    }
}

