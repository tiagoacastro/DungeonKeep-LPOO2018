package dkeep.logic;

import dkeep.cli.UserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

/**
 * Game Class
 */
public class Game implements Serializable{

    private ArrayList<Level> levels = new ArrayList<Level>();
    private ArrayList<Level> levelsCopy = new ArrayList<Level>();
    private int level;
    private gameState state;
    private levelState levelState;

    /**
     * enum for the level state (WIN, LOSE, NONE)
     */
    public enum levelState {
        WIN, LOSE, NONE
    }

    /**
     * enum for the game state (WIN, LOSE, NONE)
     */
    public enum gameState {
        WIN, LOSE, NONE
    }

    /**
     * game default constructor
     */
    public Game(){
        level = 0;
        state = gameState.NONE;
    }

    /**
     * check if the game is lost
     * @return  whether it is lost or not
     */
    public boolean isGameOver(){
        return this.state == gameState.LOSE;
    }

    /**
     * check if the game is won
     * @return  whether it is won or not
     */
    public boolean isGameWon(){
        return this.state == gameState.WIN;
    }

    /**
     * Getter for the game state
     * @return  game state
     */
    public gameState getState(){
        return this.state;
    }

    /**
     * Getter for the level state
     * @return  level state
     */
    public levelState getLevelState(){
        return this.levelState;
    }

    /**
     * Getter for the cached levels
     * @return  copy levels
     */
    public ArrayList<Level> getLevelsCopy() {
        return levelsCopy;
    }

    /**
     * loads a guard to a level
     * @param guardType guard type
     * @param route     patrol path
     * @param i         level to where it will be loaded (index)
     */
    public void loadGuardToLevel(String guardType, Character[] route, int i) {
        switch(guardType) {
        	case "Rookie":
                Guard g1 = new RookieGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
                levels.get(i).addGuard(g1);
                break;
        	case "Drunken":
                Guard g2 = new DrunkenGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
                levels.get(i).addGuard(g2);
                break;
        	case "Suspicious":
                Guard g3 = new SuspiciousGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
                levels.get(i).addGuard(g3);
                break;
        }
    }

    /**
     * loads a level
     * @param l     level to be loaded
     */
    public void loadLevel(Level l){
        levels.add(l);
    }

    /**
     * Setter for the game state
     * @param state     game state to be setted
     */
    public void setState(gameState state) {
        if (state == gameState.WIN) {
            this.state = state;
        }
	}

    /**
     * Setter for the level state
     * @param levelState    level state to be setted
     */
	public void setLevelState(levelState levelState) {
		this.levelState = levelState;
	}

    /**
     * increments the level
     */
	public void incLevel() {
        level++;
    }

    /**
     * Calls the hero mover from the level in question
     * @param input     user input for the hero movement
     */
    public void userMove(UserInterface.Direction input) {

        levelState lvlState = levels.get(level).userMove(input);
        if (lvlState == levelState.LOSE){
             state = gameState.LOSE;
             levelState = levelState.LOSE;
         } else if (lvlState == levelState.WIN){
             levelState = levelState.WIN;
             if (level == levels.size() -1)
                 state = gameState.WIN;
         } else  levelState = levelState.NONE;
    }

    /**
     * decrements level
     */
    public void decLevel() {level--;}

    /**
     * Getter for the Levels
     * @return  levels
     */
    public ArrayList<Level> getLevels() { return levels;}

    /**
     * Getter for the Level in play
     * @return  current Level
     */
    public Level getLevel(){
        return levels.get(level);
    }

    /**
     * Getter for the Level in play's index
     * @return  current level's index
     */
    public int getCurrentLevel() {
    	return level+1;
    }

    /**
     * restarts the game
     */
    public void restart(){
        levels.clear();
        for(Level l : levelsCopy)
            levels.add(new Level(l));

        level = 0;
        state = gameState.NONE;
    }

    /**
     * updates the copy of a level with the current level in the same position
     * @param i     level index
     */
    public void updateLevelCopy(int i) {
        levelsCopy.remove(i);
        levelsCopy.add(new Level(levels.get(i)));
    }
}
