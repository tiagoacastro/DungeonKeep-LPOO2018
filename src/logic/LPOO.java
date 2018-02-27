package logic;

public class LPOO {

    static Level[] levels = new Level[10];
    static int level = 0;

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
        level++;

        //map = maps[0];
        Guard g = new Guard(1,8, new char[]{'l','d','d','d','d','l','l','l','l','l','l','d','r','r','r','r','r','r','r','u','u','u','u','u'});
        levels[0].addGuard(g);
        g.draw(levels[0].getMap());

        Door d1 = new Door(5,0);
        levels[0].addDoor(d1);
        Door d2 = new Door(6,0);
        levels[0].addDoor(d2);
        Lever l = new Lever(8,7);
        levels[0].addLever(l);

        Character[][] map2 = new Character[][] {
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

        Hero h2 = new Hero(7, 1);
        map2[h.getX()][h.getY()] = 'H';

        levels[1] = new Level(map2, h2, 1);
        level++;

        Door d3 = new Door(1,0);
        levels[1].addDoor(d3);

        Key k = new Key(8,7);
        levels[1].addKey(k);



        for (int i = 0; i < level; ++i){
            levels[i].print_map();

            levels[i].user_move();
        }
    }

}
