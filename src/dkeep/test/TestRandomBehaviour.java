package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;

public class TestRandomBehaviour extends TestLevels {

    @Test(timeout = 1000)
    public void RandomBehaviourOgre() {

        boolean OgreUpClubUp = false, OgreUpClubLeft = false, OgreUpClubDown = false, OgreUpClubRight = false;
        boolean OgreLeftClubUp = false, OgreLeftClubDown = false, OgreLeftClubLeft = false, OgreLeftClubRight = false;
        boolean OgreRightClubUp = false, OgreRightClubLeft = false, OgreRightClubDown = false, OgreRightClubRight = false;
        boolean OgreDownClubUp = false, OgreDownClubDown = false, OgreDownClubLeft = false, OgreDownClubRight = false;

        Game newGame = new Game();

        loadTestLevel3(newGame);

        int oX = newGame.getLevel().getChars().get(0).getX();
        int oY = newGame.getLevel().getChars().get(0).getX();

        if (newGame.getLevel().getChars().get(0) instanceof Ogre) {
            int cX = ((Ogre) newGame.getLevel().getChars().get(0)).getClubX();
            int cY = ((Ogre) newGame.getLevel().getChars().get(0)).getClubY();

            int nextOX, nextOY;
            int nextCX, nextCY;

            while (!OgreUpClubUp || !OgreUpClubLeft || !OgreUpClubDown || !OgreUpClubRight || !OgreLeftClubUp || !OgreLeftClubDown || !OgreLeftClubLeft || !OgreLeftClubRight || !OgreRightClubUp || !OgreRightClubLeft || !OgreRightClubDown || !OgreRightClubRight || !OgreDownClubUp || !OgreDownClubDown || !OgreDownClubLeft || !OgreDownClubRight) {

                newGame.getLevel().drawImovable(newGame.getLevel().getMap());
                newGame.getLevel().getChars().get(0).update(newGame.getLevel().getMap());

                nextOX = newGame.getLevel().getChars().get(0).getX();
                nextOY = newGame.getLevel().getChars().get(0).getY();
                nextCX = ((Ogre) newGame.getLevel().getChars().get(0)).getClubX();
                nextCY = ((Ogre) newGame.getLevel().getChars().get(0)).getClubY();

                if (nextOX > oX && nextCX > cX)
                    OgreRightClubRight = true;
                else if (nextOX > oX && nextCX < cX)
                    OgreRightClubLeft = true;
                else if (nextOX > oX && nextCY < cY)
                    OgreRightClubDown = true;
                else if (nextOX > oX && nextCY > cY)
                    OgreRightClubUp = true;
                else if (nextOX < oX && nextCX > cX)
                    OgreLeftClubRight = true;
                else if (nextOX < oX && nextCX < cX)
                    OgreLeftClubLeft = true;
                else if (nextOX < oX && nextCY < cY)
                    OgreLeftClubDown = true;
                else if (nextOX < oX && nextCY > cY)
                    OgreLeftClubUp = true;
                else if (nextOY < oY && nextCX > cX)
                    OgreDownClubRight = true;
                else if (nextOY < oY && nextCX < cX)
                    OgreDownClubLeft = true;
                else if (nextOY < oY && nextCY < cY)
                    OgreDownClubDown = true;
                else if (nextOY < oY && nextCY > cY)
                    OgreDownClubUp = true;
                else if (nextOY > oY && nextCX > cX)
                    OgreUpClubRight = true;
                else if (nextOY > oY && nextCX < cX)
                    OgreUpClubLeft = true;
                else if (nextOY > oY && nextCY < cY)
                    OgreUpClubDown = true;
                else if (nextOY > oY && nextCY > cY)
                    OgreUpClubUp = true;

                oX = nextOX;
                oY = nextOY;
                cX = nextCX;
                cY = nextCY;


            }
        }
    }

    @Test(timeout = 1000)
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

    @Test(timeout = 1000)
    public void TestSleepDrunkenGuard() {
        Game game = new Game();

        loadTestLevel1(game, "Drunken", false);

        assertEquals(1, game.getLevel().getChars().get(0).getX());
        assertEquals(3, game.getLevel().getChars().get(0).getY());

        boolean way = false;
        boolean sleeping = false;

        if (game.getLevel().getChars().get(0) instanceof DrunkenGuard) {

            DrunkenGuard g = (DrunkenGuard)game.getLevel().getChars().get(0);

            while (!sleeping) {

                if (!g.sleeping()) {
                    sleeping = true;}

                g.update(game.getLevel().getMap());
                g.draw(game.getLevel().getMap());
            }
        }
        assertTrue(sleeping);
    }

}
