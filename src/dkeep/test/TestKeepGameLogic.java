package dkeep.test;

import static org.junit.Assert.*;

import dkeep.Hardcoded.TestLevels;
import dkeep.cli.UserInterface;
import org.junit.Test;

import dkeep.logic.*;

public class TestKeepGameLogic extends TestLevels {

    @Test
    public void testMoveHeroIntoToAdjOgre() {

        Game newGame = new Game();

        loadTestLevel2(newGame);

        newGame.getLevel().freezeLevel();

        assertFalse(newGame.isGameOver());
        assertEquals('A', newGame.getLevel().getHero().getSymbol());

        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);

        assertTrue(newGame.isGameOver());

    }

    @Test
    public void testMoveHeroIntoToStunOgre() {

        Game newGame = new Game();

        loadTestLevel2(newGame);

        newGame.getLevel().freezeLevel();

        assertFalse(newGame.isGameOver());

        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);

        if (newGame.getLevel().getChars().get(0) instanceof Ogre) {
            assertTrue(((Ogre) newGame.getLevel().getChars().get(0)).isStunned());
        }

        newGame.userMove(UserInterface.Direction.LEFT);
        assertTrue(newGame.isGameOver());
    }

    @Test
    public void testMoveHeroIntoToKeyCell() {

        Game newGame = new Game();

        loadTestLevel2(newGame);

        newGame.getLevel().freezeLevel();

        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);

        assertFalse(newGame.isGameOver());

        assertEquals('K', newGame.getLevel().getHero().getSymbol());

    }

    @Test
    public void testMoveHeroIntoToClosedDoors() {

        Game newGame = new Game();

        loadTestLevel2(newGame);

        newGame.getLevel().freezeLevel();

        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);

        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);

        assertFalse(newGame.isGameOver());

        assertEquals('I', newGame.getLevel().getDoors().get(0).getSymbol());
    }

    @Test
    public void testMoveHeroIntoToOpenDoors() {

        Game newGame = new Game();

        loadTestLevel2(newGame);

        newGame.getLevel().freezeLevel();

        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);

        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);

        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);

        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);

        newGame.userMove(UserInterface.Direction.LEFT);

        assertFalse(newGame.isGameOver());

        assertEquals('A', newGame.getLevel().getHero().getSymbol());

        assertEquals('S', newGame.getLevel().getDoors().get(0).getSymbol());

        newGame.userMove(UserInterface.Direction.LEFT);

        assertEquals(Game.gameState.WIN,newGame.getState());
    }

    @Test
    public void testMoveHeroIntoToWinGame() {

        Game newGame = new Game();

        loadTestLevel2(newGame);

        newGame.getLevel().freezeLevel();

        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.RIGHT);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);

        newGame.userMove(UserInterface.Direction.DOWN);
        newGame.userMove(UserInterface.Direction.DOWN);

        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);
        newGame.userMove(UserInterface.Direction.LEFT);

        newGame.userMove(UserInterface.Direction.UP);
        newGame.userMove(UserInterface.Direction.UP);

        newGame.userMove(UserInterface.Direction.LEFT);

        assertFalse(newGame.isGameOver());

        newGame.userMove(UserInterface.Direction.LEFT);

        assertEquals(Game.gameState.WIN, newGame.getState());
    }

}
