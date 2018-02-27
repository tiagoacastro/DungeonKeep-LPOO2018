package logic;

import java.util.Random;
import java.util.Scanner;

public class GameCharacter {
    protected int x;
    protected int y;

    GameCharacter() {

    }

    //constructor
    GameCharacter(int xcoord, int ycoord) {
        x = xcoord;
        y = ycoord;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int xcoord) {
        x = xcoord;
    }

    public void setY(int ycoord) {
        y = ycoord;
    }
}