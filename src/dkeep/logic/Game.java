package dkeep.logic;

import dkeep.cli.UserInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private ArrayList<Level> levels = new ArrayList<Level>();
    private int level;

    public Game(){
        level = 0;

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
        Guard g = new RookieGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
        levels.get(0).addGuard(g);

        Door d1 = new Door(5, 0);
        levels.get(0).addDoor(d1);
        Door d2 = new Door(6, 0);
        levels.get(0).addDoor(d2);

        Lever l = new Lever(8, 7);
        levels.get(0).addLever(l);

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

        Ogre o = new Ogre(1, 4, 1, 3);
        levels.get(1).addOgre(o);

        Ogre o2 = new Ogre(3, 6, 3, 5);
        //levels.get(1).addOgre(o2);

    }

    public enum levelState {
        WIN, LOSE, NONE
    }

    public void start(){
        UserInterface.Direction input;
        boolean stop;
        boolean lose;

        for (Level lvl : levels) {
            lvl.draw();

            stop = false;
            lose = false;

            while (true){
                input = UserInterface.userInput();
                switch (userMove(input)) {
                    case WIN:
                        stop = true;
                        break;
                    case LOSE:
                        stop = true;
                        lose = true;
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
    }

    private void incLevel() {
        level++;
    }

    public levelState userMove(UserInterface.Direction input) {
        return levels.get(level).userMove(input);
    }

    public ArrayList<Level> getLevels() { return levels;}
}
