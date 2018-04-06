package dkeep.logic;

import dkeep.cli.UserInterface;
import java.util.ArrayList;
import java.io.*;

/**
 * Level class
 */
public class Level implements Serializable {

    private Character[][] lvlMap;
    private Character[][] map;
    private Hero hero;
    private ArrayList<GameCharacter> chars = new ArrayList<GameCharacter>();
    private ArrayList<Door> doors = new ArrayList<Door>();
    private GameObject object;
    private boolean moved;
    private boolean frozenLevel;

    /**
     * Level constructor
     *
     * @param m map
     * @param h hero
     */
    public Level(Character[][] m, Hero h) {
        lvlMap = m;
        hero = h;
    }

    /**
     * level constructor without hero
     *
     * @param m map
     */
    public Level(Character[][] m) {
        lvlMap = m;
    }

    /**
     * Level default constructor
     */
    public Level() {
    }

    /**
     * Level copy constructor
     *
     * @param l Level that will be copied
     */
    public Level(Level l) {
        lvlMap = mapCopyWithParam(l.getMap());

        hero = new Hero(l.getHero());

        if (l.getObject() instanceof Key)
            object = new Key((Key) l.getObject());
        else if (l.getObject() instanceof Lever)
            object = new Lever((Lever) l.getObject());

        for (Door d : l.getDoors())
            addDoor(new Door(d));

        charactersCopy(l);
    }

    /**
     * checks if the hero moved
     *
     * @return if it moved or not
     */
    public boolean moved() {
        return moved;
    }

    private void charactersCopy(Level l) {
        for (GameCharacter c : l.getChars()) {
            if (c instanceof RookieGuard)
                addGuard(new RookieGuard((RookieGuard) c));
            else if (c instanceof DrunkenGuard)
                addGuard(new DrunkenGuard((DrunkenGuard) c));
            else if (c instanceof SuspiciousGuard)
                addGuard(new SuspiciousGuard((SuspiciousGuard) c));
            if (c instanceof Ogre)
                addOgre(new Ogre((Ogre) c));
        }
    }

    /**
     * Setter for the hero
     *
     * @param hero new hero
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * adds a guard
     *
     * @param g guard
     */
    public void addGuard(Guard g) {
        chars.add(g);
    }

    /**
     * adds a lever as the object
     *
     * @param l new object (lever)
     */
    public void addLever(Lever l) {
        object = l;
    }

    /**
     * adds a key as the new object
     *
     * @param k new object (key)
     */
    public void addKey(Key k) {
        object = k;
    }

    /**
     * add a door
     *
     * @param d door
     */
    public void addDoor(Door d) {
        doors.add(d);
    }

    /**
     * add an ogre
     *
     * @param o ogre
     */
    public void addOgre(Ogre o) {
        chars.add(o);
    }

    /**
     * freeze all characters except hero
     */
    public void freezeLevel() {
        frozenLevel = true;
    }

    /**
     * Getter for the original map (walls and doors (openable or not) only))
     *
     * @return map
     */
    public Character[][] getMap() {
        return lvlMap;
    }

    /**
     * Getter for the map with objects and chars
     *
     * @return map
     */
    public Character[][] getMapCopy() {
        return map;
    }

    /**
     * Getter for the doors
     *
     * @return doors
     */
    public ArrayList<Door> getDoors() {
        return doors;
    }

    /**
     * Getter for the characters
     *
     * @return chars
     */
    public ArrayList<GameCharacter> getChars() {
        return chars;
    }

    /**
     * Setter for the original map
     *
     * @param map map
     */
    public void setMap(Character[][] map) {
        this.lvlMap = map;
    }

    /**
     * Getter for the hero
     *
     * @return hero
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Getter for  the object
     *
     * @return object
     */
    public GameObject getObject() {
        return object;
    }

    /**
     * Moves the hero and draws everything, it's the level's most important method
     *
     * @param input hero's movement
     * @return how the level  state is after the movement
     */
    public Game.levelState userMove(UserInterface.Direction input) {

        map = mapCopy();
        drawImovable(map);
        moved = false;

        Game.levelState x = checkWhereHeroMove(input);
        if (x != null) return x;

        checkFrozenLevel();

        if (checkCharactersCollision()) return Game.levelState.LOSE;

        drawMovable(map);

        return Game.levelState.NONE;
    }

    private Game.levelState checkWhereHeroMove(UserInterface.Direction input) {
        switch (input) {
            case UP:
                if (heroMove(hero.getX() - 1, hero.getY())) {
                    win();
                    return Game.levelState.WIN;
                }
                break;
            case LEFT:
                if (heroMove(hero.getX(), hero.getY() - 1)) {
                    win();
                    return Game.levelState.WIN;
                }
                break;
            case RIGHT:
                if (heroMove(hero.getX(), hero.getY() + 1)) {
                    win();
                    return Game.levelState.WIN;
                }
                break;
            case DOWN:
                if (heroMove(hero.getX() + 1, hero.getY())) {
                    win();
                    return Game.levelState.WIN;
                }
                break;
        }
        return null;
    }

    private boolean checkCharactersCollision() {
        if (hero.getX() != 0 && hero.getY() != 0) {
            for (int i = 0; i < chars.size(); i++)
                if (checkCollision(hero, chars.get(i))) {
                    drawMovable(map);
                    return true;
                }
            for (int i = 0; i < chars.size(); ++i) {
                if (chars.get(i).getX() == hero.getX() && chars.get(i).getY() == hero.getY()) {
                    drawMovable(map);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check  if the level is frozen and updates chars if it  is not
     */
    public void checkFrozenLevel() {
        if (!frozenLevel) {
            if (moved)
                for (GameCharacter c : chars)
                    c.update(map);
        }
    }

    private void win() {
        if (moved)
            for (GameCharacter c : chars)
                c.update(map);
        drawMovable(map);
    }

    private boolean checkCollision(Hero h, GameCharacter v) {
        //Checks if a Collision occured between the hero and another game character (ogre or guard)
        boolean heroAndVillain = ((h.getX() + 1 == v.getX() && h.getY() == v.getY()) || (h.getX() - 1 == v.getX() && h.getY() == v.getY()) || (h.getX() == v.getX() && h.getY() + 1 == v.getY()) || (h.getX() == v.getX() && h.getY() - 1 == v.getY()));
        Boolean heroAndClub = checkCollisionOgre(h, v, heroAndVillain);
        if (heroAndClub != null) return heroAndClub;
        if (checkCollisionDrunkenGuard(v)) return false;
        return heroAndVillain;
    }

    private boolean checkCollisionDrunkenGuard(GameCharacter v) {
        if (v instanceof DrunkenGuard) {
            return ((DrunkenGuard) v).sleeping();
        }
        return false;
    }

    private Boolean checkCollisionOgre(Hero h, GameCharacter v, boolean heroAndVillain) {
        if (v instanceof Ogre) {
            checkStun(h, (Ogre) v, heroAndVillain);

            return checkCollisionClub(h, (Ogre) v);
        }
        return null;
    }

    private boolean checkCollisionClub(Hero h, Ogre v) {
        return ((h.getX() == v.getClubX() && h.getY() == v.getClubY()) || (h.getX() + 1 == v.getClubX() && h.getY() == v.getClubY()) || (h.getX() - 1 == v.getClubX() && h.getY() == v.getClubY()) || (h.getX() == v.getClubX() && h.getY() + 1 == v.getClubY()) || (h.getX() == v.getClubX() && h.getY() - 1 == v.getClubY()));
    }

    private void checkStun(Hero h, Ogre v, boolean heroAndVillain) {
        if (h.armed() && heroAndVillain) {
            v.stun();
        }
    }

    private boolean heroMove(int nextX, int nextY) {
        if (nextX == -1 || nextY == -1) return false;
        switch (map[nextX][nextY]) {
            case ' ':
                heroSetMove(nextX, nextY);
                return false;
            case 'k':
                heroSetMove(nextX, nextY);
                heroMoveToKey();
                return false;
            case 'S':
                heroSetMove(nextX, nextY);
                return true;
            case 'I':
                if (object instanceof Key) {
                    checkOpenDoor(nextX, nextY);
                    return false;
                }
        }
        return false;
    }

    private void checkOpenDoor(int nextX, int nextY) {
        if (((Key) object).check()) {
            for (Door d : doors) {
                if (d.getX() == nextX && d.getY() == nextY) d.open(map);
            }
            moved = true;
        }
    }

    private void heroMoveToKey() {
        if (object instanceof Lever)
            for (Door d : doors) {
                d.open(map);
            }
        if (object instanceof Key) {
            ((Key) object).grab();
            hero.grabsKey();
        }
    }

    private void heroSetMove(int nextX, int nextY) {
        hero.setX(nextX);
        hero.setY(nextY);

        moved = true;
    }

    /**
     * draws all objects and characters
     */
    public void draw() {
        map = mapCopy();
        drawImovable(map);
        drawMovable(map);
    }

    private Character[][] mapCopy() {
        return mapCopyWithParam(lvlMap);
    }

    private Character[][] mapCopyWithParam(Character[][] lvlMap) {
        Character[][] map = new Character[lvlMap.length][];
        for (int i = 0; i < lvlMap.length; i++) {
            Character[] aMatrix = lvlMap[i];
            int aLength = aMatrix.length;
            map[i] = new Character[aLength];
            System.arraycopy(aMatrix, 0, map[i], 0, aLength);
        }
        return map;
    }

    /**
     * Draws objects
     *
     * @param map map
     */
    public void drawImovable(Character[][] map) {
        object.draw(map);

        for (Door d : doors)
            d.draw(map);
    }

    private void drawMovable(Character[][] map) {
        hero.draw(map);

        for (GameCharacter c : chars)
            if (c instanceof Ogre) {
                ((Ogre) c).drawClub(map);
            }
        for (GameCharacter c : chars)
            c.draw(map);
    }

    /**
     * Returns the map with objects and chars
     *
     * @return map
     */
    public Character[][] getPlayMap() {
        Character[][] map = mapCopy();
        drawImovable(map);
        drawMovable(map);

        return map;
    }

    /**
     * Setter for the original map
     *
     * @param map map
     */
    public void setLvlMap(Character[][] map) {
        this.lvlMap = map;
    }

    public int findDoor(int cellX, int cellY) {

        int i = 0;
        for (Door door : doors) {
            if (i<0) return -2;
            if (door.getX() == cellX && door.getY() == cellY)
                return i;
            else i++;
        }
        return -1;
    }

    /**
     * Finds an ogre based on its position
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public int findOgre(int x, int y) {
        int i = 0;
        for (GameCharacter ogre : chars) {
            if (i<0) return -2;
            if (ogre.getX() == x && ogre.getY() == y)
                return i;
            else i++;
        }
        return -1;
    }
}