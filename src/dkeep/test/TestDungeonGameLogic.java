package dkeep.test;

import static org.junit.Assert.*;

import dkeep.cli.UserInterface;
import org.junit.Test;

import dkeep.logic.*;

public class TestDungeonGameLogic extends TestLevels{

    @Test
    public void testMoveHeroIntoToFreeCell(){

        Game newGame = new Game();

        loadTestLevels(newGame);

        newGame.getLevel().freezeLevel();

        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());
       }

    @Test
    public void testMoveHeroIntoToWall() {

        Game newGame = new Game();

        loadTestLevels(newGame);

        newGame.getLevel().freezeLevel();
        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.LEFT);

        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());
    }

    @Test
    public void testMoveHeroIntoToGuard() {

        Game newGame = new Game();

        loadTestLevels(newGame);

        newGame.getLevel().freezeLevel();

        assertFalse(newGame.isGameOver());
        newGame.userMove(UserInterface.Direction.RIGHT);

        assertTrue(newGame.isGameOver());

    }

    @Test
    public void testMoveHeroIntoToClosedDoors() {

        Game newGame = new Game();

        loadTestLevels(newGame);

        newGame.getLevel().freezeLevel();
        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.LEFT);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());
    }

    @Test
    public void testMoveHeroIntoToLever() {

        Game newGame = new Game();

        loadTestLevels(newGame);

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
    }

    @Test
    public void testMoveHeroIntoToKeep() {

        Game newGame = new Game();

        loadTestLevels(newGame);

        newGame.getLevel().freezeLevel();
        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(3, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.LEFT);
    }
}