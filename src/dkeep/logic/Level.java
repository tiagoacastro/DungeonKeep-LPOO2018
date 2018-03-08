package dkeep.logic;

import dkeep.cli.UserInterface;

import java.util.ArrayList;

public class Level {

    private Character[][] lvlMap;
    private Hero hero;
    private ArrayList<GameCharacter> chars = new ArrayList<GameCharacter>();
    private ArrayList<Door> doors = new ArrayList<Door>();
    private GameObject object;
    private boolean moved;

    public Level(Character[][] m, Hero h) {
        lvlMap = m;
        hero = h;
    }

    public void addGuard(Guard g) {
        chars.add(g);
    }

    public void addLever(Lever l) {
        object = l;
    }

    public void addKey(Key k){
        object = k;
    }

    public void addDoor(Door d){
        doors.add(d);
    }

    public void addOgre(Ogre o) {
        chars.add(o);
    }

    public Character[][] getMap() {
        return lvlMap;
    }

    public Game.levelState userMove(UserInterface.Direction input) {

            Character[][] map = mapCopy();

            drawImovable(map);

            moved = false;

            switch (input) {

                case UP:
                    if( heroMove(map,hero.getX()-1,hero.getY()) ) {
                        win(map);
                        return Game.levelState.WIN;
                    }
                    break;
                case LEFT:
                    if( heroMove(map, hero.getX(),hero.getY()-1) ) {
                        win(map);
                        return Game.levelState.WIN;
                    }
                    break;
                case DOWN:
                    if( heroMove(map, hero.getX()+1,hero.getY()) ) {
                        win(map);
                        return Game.levelState.WIN;
                    }
                    break;
                case RIGHT:
                    if( heroMove(map, hero.getX(),hero.getY()+1) ) {
                        win(map);
                        return Game.levelState.WIN;
                    }
                    break;
            }

            if(moved)
            for(GameCharacter c : chars)
                c.update(map);

            if(hero.getX() != 0 && hero.getY() != 0){
                for(int i = 0; i < chars.size() ; i++)
                    if (checkCollision(map, hero, chars.get(i))) {
                        drawMovable(map);
                        UserInterface.printMap(map);
                        System.out.println("The villain has restrained you, you LOST ! :( ");
                        return Game.levelState.LOSE;
                    }
                for (int i = 0; i < chars.size(); ++i) {
                    if(chars.get(i).getX() == hero.getX() && chars.get(i).getY() == hero.getY()){
                        drawMovable(map);
                        UserInterface.printMap(map);
                        System.out.println("The villain has restrained you, you LOST ! :( ");
                        return Game.levelState.LOSE;
                    }
                }
            }

            drawMovable(map);

            UserInterface.printMap(map);

            return Game.levelState.NONE;
    }

    private void win(Character map[][]){
        if(moved)
            for(GameCharacter c : chars)
                c.update(map);
        drawMovable(map);
        UserInterface.printMap(map);
        System.out.println("You won the game! Congrats ");
    }

    public boolean checkCollision(Character[][] map, Hero h, GameCharacter v){
        //Checks if a Collision occured between the hero and another game character (ogre or guard)
        boolean heroAndVillain =  ((h.getX()+1 == v.getX() && h.getY() == v.getY()) || (h.getX()-1 == v.getX() && h.getY() == v.getY()) || (h.getX() == v.getX() && h.getY()+1 == v.getY()) || (h.getX() == v.getX() && h.getY()-1 == v.getY()));
        if (v instanceof Ogre) {
            if(h.armed() && heroAndVillain) {
                ((Ogre) v).stun(map);
            }
            boolean heroAndClub = ((h.getX() == ((Ogre) v).getClubX() && h.getY() == ((Ogre) v).getClubY() ) || (h.getX()+1 == ((Ogre) v).getClubX()  && h.getY() == ((Ogre) v).getClubY()) || (h.getX()-1 == ((Ogre) v).getClubX()  && h.getY() == ((Ogre) v).getClubY()) || (h.getX() == ((Ogre) v).getClubX()  && h.getY()+1 == ((Ogre) v).getClubY()) || (h.getX() == ((Ogre) v).getClubX()  && h.getY()-1 == ((Ogre) v).getClubY()));

            return heroAndClub;
        }
        if (v instanceof DrunkenGuard) {
            if(((DrunkenGuard) v).sleeping())
                return false;
        }
        return heroAndVillain;
    }

    public boolean heroMove(Character[][] map, int nextX, int nextY) {
            if (map[nextX][nextY] == ' ') {

                hero.setX(nextX);
                hero.setY(nextY);

                moved = true;

                return false;
            }


            if (map[nextX][nextY] == 'k') {
                hero.setX(nextX);
                hero.setY(nextY);

                if (object instanceof Lever)
                    for (Door d : doors) {
                        d.open(map);
                     }

                if (object instanceof Key) {
                    ((Key) object).grab();
                    hero.grabsKey();
                }

                moved = true;

                return false;
            }


            if (map[nextX][nextY] == 'S') {

                hero.setX(nextX);
                hero.setY(nextY);

                moved = true;

                return true;
            }

            if (map[nextX][nextY] == 'I') {
                if (object instanceof Key)
                    if (((Key)object).check()) {
                        for (Door d : doors) {
                            if (d.getX() == nextX && d.getY() == nextY) {
                                d.open(map);
                            }
                        }

                        if (hero.armed())
                            hero.setSymbol('A');
                        else
                            hero.setSymbol('H');

                        moved = true;
                    }
                return false;
            }

            return false;
    }

    public void draw(){
        Character[][] map = mapCopy();
        drawImovable(map);
        drawMovable(map);
        UserInterface.printMap(map);
    }

    public Character[][] mapCopy(){
        Character[][] map = new Character[lvlMap.length][];
        for(int i = 0; i < lvlMap.length; i++)
        {
            Character[] aMatrix = lvlMap[i];
            int aLength = aMatrix.length;
            map[i] = new Character[aLength];
            System.arraycopy(aMatrix, 0, map[i], 0, aLength);
        }
        return map;
    }

    private void drawImovable(Character[][] map){
        object.draw(map);

        for(Door d : doors)
            d.draw(map);
    }

    private void drawMovable(Character[][] map){
        hero.draw(map);

        for(GameCharacter c : chars)
            if(c instanceof Ogre) {
                ((Ogre)c).drawClub(map);
            }
        for(GameCharacter c : chars)
            c.draw(map);
    }

}