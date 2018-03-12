package dkeep.test;

import static org.junit.Assert.*;

import dkeep.cli.UserInterface;
import org.junit.Test;

import dkeep.logic.*;

public class TestDungeonGameLogic {

    @Test
    public void testMoveHeroIntoToFreeCell(){

        Game newGame = new Game();
        newGame.testLevel();
        newGame.getTestLevel().get(0).freezeLevel();

        assertEquals(1, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());
       }

    @Test
    public void testMoveHeroIntoToWall() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.getTestLevel().get(0).freezeLevel();
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.LEFT);

        assertEquals(1, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());
    }

    @Test
    public void testMoveHeroIntoToGuard() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.getTestLevel().get(0).freezeLevel();

        assertFalse(newGame.isGameOver());
        newGame.userMoveTests(UserInterface.Direction.RIGHT);

        assertTrue(newGame.isGameOver());

    }

    @Test
    public void testMoveHeroIntoToClosedDoors() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.getTestLevel().get(0).freezeLevel();
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.LEFT);

        assertEquals(2, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());
    }

    @Test
    public void testMoveHeroIntoToLever() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.getTestLevel().get(0).freezeLevel();
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.DOWN);

        assertEquals(3, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        assertEquals('S', newGame.getTestLevel().get(0).getDoors().get(0).getSymbol());
        assertEquals('S', newGame.getTestLevel().get(0).getDoors().get(1).getSymbol());
    }

    @Test
    public void testMoveHeroIntoToKeep() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.getTestLevel().get(0).freezeLevel();
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.DOWN);

        assertEquals(3, newGame.getTestLevel().get(0).getHero().getX());
        assertEquals(1, newGame.getTestLevel().get(0).getHero().getY());

        newGame.userMoveTests(UserInterface.Direction.LEFT);
    }
}