package dkeep.test;

import static org.junit.Assert.*;

import dkeep.cli.UserInterface;
import org.junit.Test;

import dkeep.logic.*;

public class TestKeepGameLogic {

    @Test
    public void testMoveHeroIntoToAdjOgre() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.incLevel();
        newGame.getTestLevel().get(1).freezeLevel();

        assertFalse(newGame.isGameOver());

        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);

        assertTrue(newGame.isGameOver());

    }

    @Test
    public void testMoveHeroIntoToKeyCell() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.incLevel();
        newGame.getTestLevel().get(1).freezeLevel();

        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);

        assertFalse(newGame.isGameOver());

        assertEquals('K', newGame.getTestLevel().get(1).getHero().getSymbol());

    }

    @Test
    public void testMoveHeroIntoToClosedDoors() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.incLevel();
        newGame.getTestLevel().get(1).freezeLevel();

        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);

        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);

        assertFalse(newGame.isGameOver());

        assertEquals('I', newGame.getTestLevel().get(1).getDoors().get(0).getSymbol());

    }

    @Test
    public void testMoveHeroIntoToOpenDoors() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.incLevel();
        newGame.getTestLevel().get(1).freezeLevel();

        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);

        newGame.userMoveTests(UserInterface.Direction.DOWN);
        newGame.userMoveTests(UserInterface.Direction.DOWN);

        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);

        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);

        newGame.userMoveTests(UserInterface.Direction.LEFT);

        assertFalse(newGame.isGameOver());

        assertEquals('S', newGame.getTestLevel().get(1).getDoors().get(0).getSymbol());

    }

    @Test
    public void testMoveHeroIntoToWinGame() {

        Game newGame = new Game();
        newGame.testLevel();
        newGame.incLevel();
        newGame.getTestLevel().get(1).freezeLevel();

        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.RIGHT);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);

        newGame.userMoveTests(UserInterface.Direction.DOWN);
        newGame.userMoveTests(UserInterface.Direction.DOWN);

        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);
        newGame.userMoveTests(UserInterface.Direction.LEFT);

        newGame.userMoveTests(UserInterface.Direction.UP);
        newGame.userMoveTests(UserInterface.Direction.UP);

        newGame.userMoveTests(UserInterface.Direction.LEFT);

        assertFalse(newGame.isGameOver());

        newGame.userMoveTests(UserInterface.Direction.LEFT);

        assertEquals(Game.gameState.WIN, newGame.getState());

    }
}
