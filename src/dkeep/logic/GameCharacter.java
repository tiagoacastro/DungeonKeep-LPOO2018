package dkeep.logic;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public abstract class GameCharacter implements Serializable {
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

    public int getxClub() {
        return 0;
    }

    public int getyClub() {
        return 0;
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