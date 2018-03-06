package dkeep.logic;

import java.util.ArrayList;

public class Game {

    public enum State {
        PLAY
    }

    private ArrayList<Level> levels = new ArrayList<Level>();
    private State state;
    private int level;

    public Game(){level = 0;}

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void addLevel(Character[][] map, Hero h, int type){
        levels.add(new Level(map, h, type));
    }

    public int getLevel() {
        return level;
    }

    public void incLevel() {
        level++;
    }

    public boolean userMove() {
        return levels.get(level).userMove();
    }
}
