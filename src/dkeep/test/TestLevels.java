package dkeep.test;

import dkeep.logic.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TestLevels {

    public void loadTestLevel1(Game g, String guardType, boolean hero){
        Character[][] map = new Character[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', ' ', ' ', 'G', 'X'},
                {'I', ' ', ' ', ' ', 'X'},
                {'I', 'k', ' ', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X'}};

        Hero h1 = new Hero(1, 1);
        Level l1;
        if (hero) {
            l1 = (new Level(map, h1));
        }
        else {
            l1 = (new Level(map));
        }

        Character[] route = new Character[]{'d', 'd', 'l', 'u', 'u', 'r'};
        switch(guardType) {
            case "Rookie":
                Guard g1 = new RookieGuard(1, 3, new ArrayList<Character>(Arrays.asList(route)));
                l1.addGuard(g1);
                break;
            case "Drunken":
                Guard g2 = new DrunkenGuard(1, 3, new ArrayList<Character>(Arrays.asList(route)));
                l1.addGuard(g2);
                break;
            case "Suspicious":
                Guard g3 = new SuspiciousGuard(1, 3, new ArrayList<Character>(Arrays.asList(route)));
                l1.addGuard(g3);
                break;
        }

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


        Hero h2 = new Hero(7, 1);
        h2.arm();

        Level l2 = (new Level(map2, h2));

        Door d2 = new Door(1, 0);
        l2.addDoor(d2);

        Key k = new Key(1, 7);
        l2.addKey(k);

        Ogre o = new Ogre(1, 4, 1, 3);
        l2.addOgre(o);

        g.loadLevel(l2);
    }

    public void loadTestLevel3(Game g){
        Character[][] map3 = new Character[][]{
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

        Level l3 = new Level(map3);

        Door d3 = new Door(1, 0);
        l3.addDoor(d3);

        Key k = new Key(1, 7);
        l3.addKey(k);

        Ogre o = new Ogre(1, 4, 1, 3);
        l3.addOgre(o);

        g.loadLevel(l3);
    }
}
