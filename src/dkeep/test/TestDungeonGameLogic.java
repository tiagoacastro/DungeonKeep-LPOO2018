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
                newGame.getLevel().getChars().get(0).update(newGame.getLevel().getMap());
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

}
