package game;

public class LPOO {

    static level[] levels;

    public static void main(String[] args) {

        maps[0]=new Character[][]
        {
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

        maps[1]=new Character[][]
                {
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

        map = maps[0];

        Hero h = new Hero(1, 1);
        map[h.getX()][h.getY()] = 'H';
        Guard g = new Guard(1,8);
        map[g.getX()][g.getY()] = 'G';

        Door d1 = new Door(5,0,1);
        map[d1.getX()][d1.getY()] = 'I';
        Door d2 = new Door(6,0,1);
        map[d2.getX()][d2.getY()] = 'I';
        Lever l = new Lever(8,7,1);
        map[l.getX()][l.getY()] = 'k';

        print_map();

        user_move(h, g);

        map = maps[1];

        print_map();

        Hero h1 = new Hero(1, 7);

        user_move2(h, o);
    }


}
