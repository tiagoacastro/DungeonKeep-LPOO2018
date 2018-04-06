package dkeep.test;

import static org.junit.Assert.*;

import dkeep.Hardcoded.GameLevels;
import dkeep.Hardcoded.TestLevels;
import dkeep.cli.UserInterface;
import org.junit.Test;

import dkeep.logic.*;

public class TestDungeonGameLogic extends TestLevels {

    @Test
    public void testMoveHeroIntoToFreeCell(){

        Game newGame = new Game();

        loadTestLevel1(newGame, "Rookie",true);

        newGame.getLevel().freezeLevel();

        if( newGame.getLevel().getChars().get(0) instanceof Guard) {
            ((Guard)newGame.getLevel().getChars().get(0)).setMov(0);
            assertEquals(0, ((Guard) newGame.getLevel().getChars().get(0)).getMov());
        }

        assertEquals(1, newGame.getCurrentLevel());

        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        assertEquals(Game.levelState.NONE,newGame.getLevelState());
    }

    @Test
    public void testMoveHeroIntoToWall() {

        Game newGame = new Game();

        loadTestLevel1(newGame, "Rookie",true);


        newGame.getLevel().freezeLevel();
        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.LEFT);

        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.incLevel();
        assertEquals(2, newGame.getCurrentLevel());

        newGame.decLevel();
        assertEquals(1, newGame.getCurrentLevel());

        newGame.setLevelState(Game.levelState.WIN);
        assertEquals(false,newGame.isGameWon());
        newGame.setState(Game.gameState.WIN);
        assertEquals(Game.gameState.WIN,newGame.getState());
        assertEquals(true,newGame.isGameWon());
    }

    @Test
    public void testMoveHeroIntoToGuard() {

        Game newGame = new Game();

        loadTestLevel1(newGame, "Rookie",true);

        newGame.getLevel().freezeLevel();

        assertFalse(newGame.isGameOver());
        newGame.userMove(UserInterface.Direction.RIGHT);

        assertTrue(newGame.isGameOver());

    }

    @Test
    public void testMoveHeroIntoToClosedDoors() {

        Game newGame = new Game();

        loadTestLevel1(newGame, "Rookie",true);

        newGame.getLevel().freezeLevel();
        newGame.getLevel().checkFrozenLevel();

        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        assertTrue(newGame.getLevel().moved());

        newGame.userMove(UserInterface.Direction.LEFT);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        assertFalse(newGame.getLevel().moved());

        assertFalse(newGame.isGameOver());

    }

    @Test
    public void testMoveHeroIntoToLever() {

        Game newGame = new Game();

        loadTestLevel1(newGame, "Rookie",true);

        newGame.getLevel().freezeLevel();
        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(3, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        assertEquals('S', newGame.getLevel().getDoors().get(0).getSymbol());
        assertEquals('S', newGame.getLevel().getDoors().get(1).getSymbol());

        assertEquals('H', newGame.getLevel().getHero().getSymbol());
    }

    @Test
    public void testMoveHeroIntoToKeep() {

        Game newGame = new Game();

        GameLevels.loadLevel1("Rookie", newGame);

        GameLevels.loadLevel2(0, newGame);

        newGame.getLevel().freezeLevel();
        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);

        assertEquals(6, newGame.getLevel().getHero().getX());
        assertEquals(0, newGame.getLevel().getHero().getY());

    }

    @Test(timeout = 1000)
    public void test2waysSuspiciousGuard(){

        Game newGame = new Game();
        loadTestLevel1(newGame, "Suspicious",false);

        assertEquals(1,newGame.getLevel().getChars().get(0).getX());
        assertEquals(3,newGame.getLevel().getChars().get(0).getY());

        int oldX, oldY;

        oldX = newGame.getLevel().getChars().get(0).getX();
        oldY = newGame.getLevel().getChars().get(0).getY();

        int currX, currY;
        int i = 0;

        boolean way = false;
        char c ;

        while(!way) {

            newGame.getLevel().getChars().get(0).update(newGame.getLevel().getMap());
            newGame.getLevel().getChars().get(0).draw(newGame.getLevel().getMap());

            currX = newGame.getLevel().getChars().get(0).getX();
            currY = newGame.getLevel().getChars().get(0).getY();

            c = directionMove(oldX, oldY, currX, currY);

            if (c != ((Guard) newGame.getLevel().getChars().get(0)).getPath().get(i)) {
                if (c ==getSimetrical(((Guard)newGame.getLevel().getChars().get(0)).getPath().get(i)))
                    way = true;
            }
            i++;
        }
    }

    private char getSimetrical(char x) {

        switch(x) {
            case 'l':
                return 'r';
            case 'r':
                return 'l';
            case 'u':
                return 'd';
            case 'd':
                return 'u';
                default:
                    return ' ';
        }
    }
    private char directionMove(int oldX, int oldY, int currX, int currY){

        //right
        if (currX > oldX) return 'r';
        //left
        else if (currX <oldX) return 'l';
        //down
        else if (currY > oldY) return 'd';
        //up
        else if (currY < oldY) return 'u';

        return ' ';
    }

    @Test
    public void testMoveDrunkenGuard() {

        boolean sleep = false;

        Game newGame = new Game();
        loadTestLevel1(newGame, "Drunken",false);

        assertEquals(1,newGame.getLevel().getChars().get(0).getX());
        assertEquals(3,newGame.getLevel().getChars().get(0).getY());

        assertEquals(true, ((Guard)newGame.getLevel().getChars().get(0)).getWay());

        if(newGame.getLevel().getChars().get(0) instanceof DrunkenGuard) {

            while (!sleep) {
                ((DrunkenGuard)newGame.getLevel().getChars().get(0)).update(newGame.getLevel().getMap());
                newGame.getLevel().getChars().get(0).draw(newGame.getLevel().getMap());
                if (((DrunkenGuard) newGame.getLevel().getChars().get(0)).sleeping()) {

                    assertEquals('g', ((DrunkenGuard) newGame.getLevel().getChars().get(0)).getSymbol());
                    sleep = true;

                    ((DrunkenGuard) newGame.getLevel().getChars().get(0)).wake();

                } else {
                    assertEquals('G', ((DrunkenGuard) newGame.getLevel().getChars().get(0)).getSymbol());
                }

            }
        }

        assertTrue(sleep);
    }

    @Test
    public void testMoveRookieGuard() {

        Game newGame = new Game();

        loadTestLevel1(newGame, "Rookie", false);

        assertEquals(1, newGame.getLevel().getChars().get(0).getX());
        assertEquals(3, newGame.getLevel().getChars().get(0).getY());

        int counter = 0;

        if (newGame.getLevel().getChars().get(0) instanceof RookieGuard) {

            while (counter != ((RookieGuard) newGame.getLevel().getChars().get(0)).getPath().size()) {

                newGame.getLevel().getChars().get(0).update(newGame.getLevel().getMap());
                newGame.getLevel().getChars().get(0).draw(newGame.getLevel().getMap());

                counter++;
            }
        }

        assertEquals(1, newGame.getLevel().getChars().get(0).getX());
        assertEquals(2, newGame.getLevel().getChars().get(0).getY());
    }

    @Test
    public void testRestart() {


        Game newGame = new Game();
        loadTestLevel1(newGame, "Rookie", true);

        assertEquals(1,newGame.getLevels().size());

        newGame.restart();

        assertEquals(0,newGame.getLevels().size());

        loadTestLevel1(newGame, "Rookie", true);

        int doorX = newGame.getLevel().getDoors().get(0).getX();
        int doorY = newGame.getLevel().getDoors().get(0).getY();


        newGame.getLevel().getDoors().get(0).open(newGame.getLevel().getMap());
        Character map [][] = newGame.getLevel().getMap();
        assertEquals("S", map[newGame.getLevel().getDoors().get(0).getX()][newGame.getLevel().getDoors().get(0).getY()].toString());

        assertEquals(0 , newGame.getLevel().findDoor(doorX,doorY));

        assertEquals(-1 , newGame.getLevel().findDoor(1,1));

        Character map2[][] = {{'a', 'b'}, {'b','a'}};
        newGame.getLevel().setLvlMap(map2);
    }

}
