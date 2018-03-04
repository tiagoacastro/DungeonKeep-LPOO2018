package logic;

import java.util.Random;
import java.util.Scanner;

public class GameCharacter {
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
}