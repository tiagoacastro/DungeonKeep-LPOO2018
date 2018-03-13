package dkeep.test;

import dkeep.logic.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TestLevels {

    public void loadTestLevels(Game g) {
        loadTestLevel1(g);

        loadTestLevel2(g);
    }

    public void loadTestLevel1(Game g){
        Character[][] map = new Character[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', ' ', ' ', 'G', 'X'},
                {' ', ' ', ' ', ' ', 'X'},
                {' ', 'k', ' ', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X'}};

        Hero h1 = new Hero(1, 1);
        Level l1 = (new Level(map, h1));

        Character[] route1 = new Character[]{'l', 'd', 'd', 'd', 'd', 'l', 'l', 'l', 'l', 'l', 'l', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'u', 'u', 'u', 'u', 'u'};
        Guard g0 = new RookieGuard(1, 3, new ArrayList<Character>(Arrays.asList(route1)));
        l1.addGuard(g0);

        Door d1 = new Door(2, 0);
        l1.addDoor(d1);
        Door d2 = new Door(3, 0);
        l1.addDoor(d2);

        Lever l = new Lever(4, 1);
        l1.addLever(l);

        g.loadLevel(l1);
    }

    public void loadTestLevel2(Game g){
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


        Level l2 = (new Level(map2));

        Door d3 = new Door(1, 0);
        l2.addDoor(d3);

        Key k = new Key(1, 7);
        l2.addKey(k);

        Ogre o = new Ogre(1, 4, 1, 3);
        l2.addOgre(o);

        g.loadLevel(l2);
    }
}
