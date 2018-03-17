package dkeep.logic;

import dkeep.cli.UserInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private ArrayList<Level> levels = new ArrayList<Level>();
    private int level;
    private gameState state;
    private levelState levelState;

    public boolean isGameOver(){
        if (this.state == gameState.LOSE)
            return true;
        else return false;
    }

    public gameState getState(){
        return this.state;
    }
    
    public levelState getLevelState(){
        return this.levelState;
    }

    public Game(){
        level = 0;
        state = gameState.NONE;
    }
    
    public void loadLevel1(String guardType){
        Character[][] map1 = new Character[][]{
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
                {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
                {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        Hero h = new Hero(1, 1);

        levels.add(new Level(map1, h));

        Character[] route = new Character[]{'l', 'd', 'd', 'd', 'd', 'l', 'l', 'l', 'l', 'l', 'l', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'u', 'u', 'u', 'u', 'u'};
        switch(guardType) {
        	case "Rookie":
                Guard g1 = new RookieGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
                levels.get(0).addGuard(g1);
                break;
        	case "Drunken":
                Guard g2 = new DrunkenGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
                levels.get(0).addGuard(g2);
                break;
        	case "Suspicious":
                Guard g3 = new SuspiciousGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
                levels.get(0).addGuard(g3);
                break;
        }

        Door d1 = new Door(5, 0);
        levels.get(0).addDoor(d1);
        Door d2 = new Door(6, 0);
        levels.get(0).addDoor(d2);

        Lever l = new Lever(8, 7);
        levels.get(0).addLever(l);
    }

    public void loadLevel2(int ogreNumber){
        Character[][] map2 = new Character[][]{
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        Hero h2 = new Hero(7, 1);
        h2.arm();

        levels.add(new Level(map2, h2));

        Door d3 = new Door(1, 0);
        levels.get(1).addDoor(d3);

        Key k = new Key(1, 7);
        levels.get(1).addKey(k);

        for(int i = 0; i < ogreNumber; ++i) {
            Ogre o = new Ogre(1, 1+i, 2, 1+i);
            levels.get(1).addOgre(o);
        }
    }

    public void loadLevel(Level l){
        levels.add(l);
    }

    public enum levelState {
        WIN, LOSE, NONE
    }

    public enum gameState {
        WIN, LOSE, NONE
    }

    public void start(){
        UserInterface.Direction input;
        boolean stop;
        boolean lose;

        for (Level lvl : levels) {
            lvl.draw();
            UserInterface.printMap(lvl.getMapCopy());

            stop = false;
            lose = false;

            while (true){
                input = UserInterface.userInput();
                userMove(input);
                UserInterface.printMap(lvl.getMapCopy());
                switch (levelState) {
                    case WIN:
                    	System.out.println("You won the game! Congrats ");
                        stop = true;
                        break;
                    case LOSE:
                    	System.out.println("The villain has restrained you, you LOST ! :( ");
                        stop = true;
                        lose = true;
                        this.state = gameState.LOSE;
                        break;
                    case NONE:
                        break;
                }

                if(stop)
                    break;
            }

            if(lose)
                return;

            incLevel();
        }
        
        this.state = gameState.WIN;
    }

    public void setState(gameState state) {
		this.state = state;
	}

	public void setLevelState(levelState levelState) {
		this.levelState = levelState;
	}

	public void incLevel() {
        level++;
    }

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

    public ArrayList<Level> getLevels() { return levels;}

    public Level getLevel(){
        return levels.get(level);
    }
    
    public int getCurrentLevel() {
    	return level+1;
    }
}
