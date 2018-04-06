package dkeep.Hardcoded;

import dkeep.logic.*;

public class GameLevels {

    public static void loadLevel1(String guardType, Game game){
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
        game.loadLevel((new Level(map1, h)));

        int index = game.getLevels().size() - 1;

        Character[] route = new Character[]{'l', 'd', 'd', 'd', 'd', 'l', 'l', 'l', 'l', 'l', 'l', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'u', 'u', 'u', 'u', 'u'};
        game.loadGuardToLevel(guardType, route, index);

        Door d1 = new Door(5, 0);
        game.getLevels().get(index).addDoor(d1);
        Door d2 = new Door(6, 0);
        game.getLevels().get(index).addDoor(d2);

        Lever l = new Lever(8, 7);
        game.getLevels().get(index).addLever(l);

        game.getLevelsCopy().add(new Level(game.getLevels().get(index)));
    }

    public static void loadLevel2(int ogreNumber, Game game){
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

        game.getLevels().add(new Level(map2, h2));

        int index = game.getLevels().size() - 1;

        Door d3 = new Door(1, 0);
        game.getLevels().get(index).addDoor(d3);

        Key k = new Key(1, 7);
        game.getLevels().get(index).addKey(k);

        for(int i = 0; i < ogreNumber; ++i) {
            Ogre o = new Ogre(1, 1+i, 2, 1+i);
            game.getLevels().get(index).addOgre(o);
        }

        game.getLevelsCopy().add(new Level(game.getLevels().get(index)));
    }
}
