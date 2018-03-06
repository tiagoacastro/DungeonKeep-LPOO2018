package logic;

import cli.UserInterface;

import java.util.ArrayList;

import java.util.Arrays;

//TODO: Arranjar colisoes, meter ogres a dar overlap bem (primeiro desenhar unmovables, depois movables no fim)

public class Main {

    public static void main(String[] args) {

        Game game = new Game();

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

        game.addLevel(map1, h, 0);

        Character[] route = new Character[]{'l', 'd', 'd', 'd', 'd', 'l', 'l', 'l', 'l', 'l', 'l', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'u', 'u', 'u', 'u', 'u'};
        Guard g = new DrunkenGuard(1, 8, new ArrayList<Character>(Arrays.asList(route)));
        game.getLevels().get(0).addGuard(g);

        Door d1 = new Door(5, 0);
        game.getLevels().get(0).addDoor(d1);
        Door d2 = new Door(6, 0);
        game.getLevels().get(0).addDoor(d2);

        Lever l = new Lever(8, 7);
        game.getLevels().get(0).addLever(l);

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

        game.getLevels().add(new Level(map2, h2, 1));

        Door d3 = new Door(1, 0);
        game.getLevels().get(1).addDoor(d3);

        Key k = new Key(1, 7);
        game.getLevels().get(1).addKey(k);

        Ogre o = new Ogre(1, 4, 1, 3);
        game.getLevels().get(1).addOgre(o);

        Ogre o2 = new Ogre(3, 6, 3, 5);
        game.getLevels().get(1).addOgre(o2);

        for (Level lvl : game.getLevels()) {
            UserInterface.printMap(lvl.getMap());

            if (!game.userMove())
                break;

            game.incLevel();
        }
    }
}
