package logic;

import cli.UserInterface;

public class Level {

    private Character[][] map;
    private Hero hero;
    private Guard guards[] = new Guard[1];
    private int guardNumber;
    private Ogre ogres[] = new Ogre[1];
    private int ogreNumber;
    private Door doors[] = new Door[2];
    private int doorNumber;
    private Lever lever;
    private Key key;
    private int type; //0 is lever level, 1 is key level

    public Level(Character[][] m, Hero h, int t) {
        map = m;
        hero = h;
        guardNumber=0;
        ogreNumber=0;
        type = t;
    }

    public void addGuard(Guard g) {
        guards[guardNumber] = g;
        guardNumber++;
    }

    public void addLever(Lever l) {
        lever = l;
    }

    public void addKey(Key k){
        key =k;
    }

    public void addDoor(Door d){
        doors[doorNumber] = d;
        doorNumber++;
    }

    public void addOgre(Ogre o) {
        ogres[ogreNumber] = o;
        ogreNumber++;
    }

    public int getType(){
        return type;
    }

    public Guard[] getGuards() {
        return guards;
    }

    public Ogre[] getOgres() {
        return ogres;
    }

    public Character[][] getMap() {
        return map;
    }

    public boolean userMove() {

        UserInterface.LPOO.Direction input;
        do {

            input = UserInterface.LPOO.userInput();

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
                        UserInterface.LPOO.printMap(type, map);
                        System.out.println("The guard has restrained you, you LOST ! :( ");
                        return false;
                    }
                    for (int i = 0; i < guardNumber; ++i) {
                        if(guards[i].getX() == hero.getX() && guards[i].getY() == hero.getY()){
                            UserInterface.LPOO.printMap(type, map);
                            System.out.println("The guard has restrained you, you LOST ! :( ");
                            return false;
                        }
                    }
                } else {
                    if ((map[hero.getX() + 1][hero.getY()] == '0') || (map[hero.getX() - 1][hero.getY()] == '0') || (map[hero.getX()][hero.getY() +1] == '0') || (map[hero.getX()][hero.getY()-1]== '0')) {
                        UserInterface.LPOO.printMap(type, map);
                        System.out.println("The ogre has slaughtered you, you LOST ! :( ");
                        return false;
                    }
                    if ((map[hero.getX() + 1][hero.getY()] == '*') || (map[hero.getX() - 1][hero.getY()] == '*') || (map[hero.getX()][hero.getY() +1] == '*') || (map[hero.getX()][hero.getY()-1]== '*')) {
                        UserInterface.LPOO.printMap(type, map);
                        System.out.println("The ogre has slaughtered you, you LOST ! :( ");
                        return false;
                    }
                    for (int i = 0; i < ogreNumber; ++i) {
                        if(ogres[i].getX() == hero.getX() && ogres[i].getY() == hero.getY()){
                            UserInterface.LPOO.printMap(type, map);
                            System.out.println("The ogre has slaughtered you, you LOST ! :( ");
                            return false;
                        }
                        if(ogres[i].getClubX() == hero.getX() && ogres[i].getClubY() == hero.getY()){
                            UserInterface.LPOO.printMap(type, map);
                            System.out.println("The ogre has slaughtered you, you LOST ! :( ");
                            return false;
                        }
                    }
                }
            }

            UserInterface.LPOO.printMap(type, map);

        } while (true);
    }

    public boolean move(int nextX, int nextY) {

        if(type == 0) {


            if (map[nextX][nextY] == ' ') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (int j = 0; j < doorNumber; ++j) {
                    doors[j].draw(map);
                }

                for (int i = 0; i < guardNumber; ++i) {
                    guards[i].draw(map);
                }

                map[nextX][nextY] = hero.getSymbol();

                lever.draw(map);

                return false;
            }


            if (map[nextX][nextY] == 'k') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (int j = 0; j < doorNumber; ++j) {
                    if (type == 0)
                        doors[j].open();
                    doors[j].draw(map);
                }

                for (int i = 0; i < guardNumber; ++i) {
                    guards[i].draw(map);
                }

                map[nextX][nextY] = hero.getSymbol();

                return false;
            }


            if (map[nextX][nextY] == 'S') {
                for (int j = 0; j < doorNumber; ++j) {
                    doors[j].draw(map);
                }

                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (int i = 0; i < guardNumber; ++i) {
                    guards[i].draw(map);
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

                for (int j = 0; j < doorNumber; ++j) {
                    doors[j].draw(map);
                }

                map[nextX][nextY] = hero.getSymbol();

                for (int i = 0; i < ogreNumber; ++i) {
                    if(ogres[i].getX() == key.getX() && ogres[i].getY() == key.getY() || ogres[i].getClubX() == key.getX() && ogres[i].getClubY() == key.getY()){
                        ogres[i].draw(map);
                        key.draw(map);
                    } else {
                        key.draw(map);
                        ogres[i].draw(map);
                    }
                }

                return false;
            }


            if (map[nextX][nextY] == 'k') {
                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                for (int j = 0; j < doorNumber; ++j) {
                    if (type == 0)
                        doors[j].open();
                    doors[j].draw(map);
                }

                for (int i = 0; i < ogreNumber; ++i) {
                    ogres[i].draw(map);
                }

                key.grab();
                hero.setSymbol('K');
                map[nextX][nextY] = hero.getSymbol();

                return false;
            }


            if (map[nextX][nextY] == 'S') {
                for (int j = 0; j < doorNumber; ++j) {
                    doors[j].draw(map);
                }

                map[hero.getX()][hero.getY()] = ' ';

                hero.setX(nextX);
                hero.setY(nextY);

                map[nextX][nextY] = hero.getSymbol();

                for (int i = 0; i < ogreNumber; ++i) {
                    ogres[i].draw(map);
                }

                return true;
            }


            if (map[nextX][nextY] == 'I') {
                if(key.check()){
                    for (int j = 0; j < doorNumber; ++j) {
                        if (doors[j].getX() == nextX && doors[j].getY() == nextY) {
                            doors[j].open();
                        }
                        doors[j].draw(map);
                    }
                    hero.setSymbol('H');
                    map[hero.getX()][hero.getY()] = hero.getSymbol();
                }
                return false;
            }
        }

        return false;
    }

}