package dkeep.test;

import static org.junit.Assert.*;

import dkeep.cli.UserInterface;
import dkeep.logic.*;
import org.junit.Test;

import dkeep.logic.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDungeonGameLogic extends TestLevels{

    @Test
    public void testMoveHeroIntoToFreeCell(){

        Game newGame = new Game();

        loadTestLevel1(newGame, "Rookie",true);

        newGame.getLevel().freezeLevel();

        assertEquals(1, newGame.getCurrentLevel());

        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());
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
        assertEquals(1, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.DOWN);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

        newGame.userMove(UserInterface.Direction.LEFT);

        assertEquals(2, newGame.getLevel().getHero().getX());
        assertEquals(1, newGame.getLevel().getHero().getY());

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

        newGame.loadLevel1("Rookie");

        newGame.loadLevel2(0);

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
    public void testMoveSuspiciousGuard() {

        Game newGame = new Game();

        loadTestLevel1(newGame, "Suspicious", false);

        assertEquals(1, newGame.getLevel().getChars().get(0).getX());
        assertEquals(3, newGame.getLevel().getChars().get(0).getY());

        boolean way = false;

        if (newGame.getLevel().getChars().get(0) instanceof SuspiciousGuard) {

            while (!way) {

                newGame.getLevel().getChars().get(0).update(newGame.getLevel().getMap());
                newGame.getLevel().getChars().get(0).draw(newGame.getLevel().getMap());

                if (!(((SuspiciousGuard) newGame.getLevel().getChars().get(0)).getWay())) {
                    way = true;
                }
            }
        }
        assertTrue(way);

    }

}
