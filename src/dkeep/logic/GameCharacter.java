package dkeep.logic;

import java.util.Random;
import java.util.Scanner;

public abstract class GameCharacter {
    protected int x;
    protected int y;

    GameCharacter() {

    }

    //constructor
    GameCharacter(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int xCoord) {
        x = xCoord;
    }

    public void setY(int yCoord) {
        y = yCoord;
    }

    public abstract void update(Character[][] map);

    public abstract void draw(Character[][] map);
}