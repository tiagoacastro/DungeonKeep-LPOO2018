package logic;

import cli.UserInterface;

import java.util.ArrayList;

public class Level {

    private Character[][] map;
    private Hero hero;
    private ArrayList<Guard> guards = new ArrayList<Guard>();
    private ArrayList<Ogre> ogres = new ArrayList<Ogre>();
    private ArrayList<Door> doors = new ArrayList<Door>();
    private Lever lever;
    private Key key;
    private int type; //0 is lever level, 1 is key level

    public Level(Character[][] m, Hero h, int t) {
        map = m;
        hero = h;
        type = t;
        map[hero.getX()][hero.getY()] = hero.getSymbol();
    }

    public void addGuard(Guard g) {
        guards.add(g);
        map[g.getX()][g.getY()] = g.getSymbol();
    }

    public void addLever(Lever l) {
        lever = l;
        l.draw(map);
    }

    public void addKey(Key k){
        key =k;
        k.draw(map);
    }

    public void addDoor(Door d){
        doors.add(d);
        d.draw(map);
    }

    public void addOgre(Ogre o) {
        ogres.add(o);
        map[o.getX()][o.getY()] = o.getSymbol();
        map[o.getClubX()][o.getClubY()] = o.getClubSymbol();
    }

    public ArrayList<Guard> getGuards() {
        return guards;
    }

    public ArrayList<Ogre> getOgres() {
        return ogres;
    }

    public Character[][] getMap() {
        return map;
    }

    public boolean userMove() {

        UserInterface.Direction input;
        do {

            input = UserInterface.userInput();

            switch (input) {

                case UP:
                    if( move(hero.getX()-1,hero.getY()) ) {
                        System.out.println("You won the game! Congrats ");
                        return true;
                    }
                    break;
                case LEFT:
                    if( move(hero.getX(),hero.getY()-1) ) {
                        System.out.println("You won the game! Congrats ");
                        return true;
                    }
                    break;
                case DOWN:
                    if( move(hero.getX()+1,hero.getY()) ) {
                        System.out.println("You won the game! Congrats ");
                        return true;
                    }
                    break;
                case RIGHT:
                    if( move(hero.getX(),hero.getY()+1) ) {
                        System.out.println("You won the game! Congrats ");
                        return true;
                    }
                    break;
                default:
                    continue;
            }

            if(hero.getX() != 0 && hero.getY() != 0){
                if(type==0){
                    if ((map[hero.getX() + 1][hero.getY()] == 'G') || (map[hero.getX() - 1][hero.getY()] == 'G') || (map[hero.getX()][hero.getY() +1] == 'G') || (map[hero.getX()][hero.getY()-1]== 'G')) {
                        UserInterface.printMap(map);
                        System.out.println("The guard has restrained you, you LOST ! :( ");
                        return false;
                    }
                    for (int i = 0; i < guards.size(); ++i) {
                        if(guards.get(i).getX() == hero.getX() && guards.get(i).getY() == hero.getY()){
                            UserInterface.printMap(map);
                            System.out.println("The guard has restrained you, you LOST ! :( ");
                            return false;
                        }
                    }
                } else {
                    for (Ogre o : ogres){
                        if(hero.armed())
                            if ((hero.getX() + 1 == o.getX() && hero.getY() == o.getY()) || (hero.getX() - 1 == o.getX() && hero.getY() == o.getY()) || (hero.getX() == o.getX() && hero.getY() + 1 == o.getY()) || (hero.getX() == o.getX() && hero.getY() - 1 == o.getY()))
                                o.stun(map);
                    }
                    if ((map[hero.getX() + 1][hero.getY()] == '*') || (map[hero.getX() - 1][hero.getY()] == '*') || (map[hero.getX()][hero.getY() +1] == '*') || (map[hero.getX()][hero.getY()-1]== '*')) {
                        UserInterface.printMap(map);
                        System.out.println("The ogre has slaughtered you, you LOST ! :( ");
                        return false;
                    }
                    for (int i = 0; i < ogres.size(); ++i) {
                        if(ogres.get(i).getClubX() == hero.getX() && ogres.get(i).getClubY() == hero.getY()){
                            UserInterface.printMap(map);
                            System.out.println("The ogre has slaughtered you, you LOST ! :( ");
                            return false;
                        }
                    }
                }
            }

            UserInterface.printMap(map);

        } while (true);
    }

    public boolean move(int nextX, int nextY) {

        if(type == 0) {


            if (map[nextX][nextY] == ' ') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (Door d : doors) {
                    d.draw(map);
                }

                for (Guard g : guards) {
                    g.draw(map);
                }

                map[nextX][nextY] = hero.getSymbol();

                lever.draw(map);

                return false;
            }


            if (map[nextX][nextY] == 'k') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (Door d : doors) {
                    if (type == 0)
                        d.open();
                    d.draw(map);
                }

                for (Guard g : guards) {
                    g.draw(map);
                }

                map[nextX][nextY] = hero.getSymbol();

                return false;
            }


            if (map[nextX][nextY] == 'S') {
                for (Door d : doors) {
                    d.draw(map);
                }

                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (Guard g : guards) {
                    g.draw(map);
                }

                map[nextX][nextY] = hero.getSymbol();

                lever.draw(map);

                return true;
            }




        } else {



            if (map[nextX][nextY] == ' ') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (Door d : doors) {
                    d.draw(map);
                }

                map[nextX][nextY] = hero.getSymbol();

                for (Ogre o : ogres) {
                    if(o.getX() == key.getX() && o.getY() == key.getY() || o.getClubX() == key.getX() && o.getClubY() == key.getY()){
                        o.draw(map);
                        key.draw(map);
                    } else {
                        key.draw(map);
                        o.draw(map);
                    }
                }

                return false;
            }


            if (map[nextX][nextY] == 'k') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (Door d : doors) {
                    if (type == 0)
                        d.open();
                    d.draw(map);
                }

                for (Ogre o : ogres) {
                    o.draw(map);
                }

                key.grab();
                hero.setSymbol('K');
                map[nextX][nextY] = hero.getSymbol();

                return false;
            }


            if (map[nextX][nextY] == 'S') {
                for (Door d : doors) {
                    d.draw(map);
                }

                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                map[nextX][nextY] = hero.getSymbol();

                for (Ogre o : ogres) {
                    o.draw(map);
                }

                return true;
            }


            if (map[nextX][nextY] == 'I') {
                if(key.check()){
                    for (Door d : doors) {
                        if (d.getX() == nextX && d.getY() == nextY) {
                            d.open();
                        }
                        d.draw(map);
                    }
                    if(hero.armed())
                        hero.setSymbol('A');
                    else
                        hero.setSymbol('H');
                    map[hero.getX()][hero.getY()] = hero.getSymbol();
                }
                return false;
            }
        }

        return false;
    }

}