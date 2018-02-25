package game;

public class LPOO {

    static Level[] levels = new Level[10];

    public static void main(String[] args) {

        Character[][] map1 = new Character[][] {
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
                } ;

        Hero h = new Hero(1, 1);
        map1[h.getX()][h.getY()] = 'H';

        levels[0] = new Level(map1, h, 0);

        //map = maps[0];
        Guard g = new Guard(1,8, new char[]{'l','d','d','d','d','l','l','l','l','l','l','d','r','r','r','r','r','r','r','u','u','u','u','u'});
        levels[0].addGuard(g);
        g.draw(levels[0].getMap());

        Door d1 = new Door(5,0);
        levels[0].addDoor(d1);
        map1[d1.getX()][d1.getY()] = 'I';
        Door d2 = new Door(6,0);
        levels[0].addDoor(d2);
        map1[d2.getX()][d2.getY()] = 'I';
        Lever l = new Lever(8,7);
        levels[0].addLever(l);
        map1[l.getX()][l.getY()] = 'k';

        levels[0].print_map();

        levels[0].user_move();

        /* Character[][] map2 = new Character[][] {
                        {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                        {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                        {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                        {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                        {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                        {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                        {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                        {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                        {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
                } ;
                */

    }

}
