package dkeep.test;

import static org.junit.Assert.*;

import dkeep.cli.UserInterface;
import org.junit.Test;

import dkeep.logic.*;

public class TestRandomBehaviour {

    @Test(timeout = 1000)
    public void RandomBehaviour() {

        boolean OgreUpClubUp = false, OgreUpClubLeft = false, OgreUpClubDown = false, OgreUpClubRight = false;
        boolean OgreLeftClubUp = false, OgreLeftClubDown = false, OgreLeftClubLeft = false, OgreLeftClubRight = false;
        boolean OgreRightClubUp = false, OgreRightClubLeft = false, OgreRightClubDown = false, OgreRightClubRight = false;
        boolean OgreDownClubUp = false, OgreDownClubDown = false, OgreDownClubLeft = false, OgreDownClubRight = false;

        Game newGame = new Game();
        newGame.testLevel();
        newGame.incLevel();

        int oX = newGame.getTestLevel().get(1).getChars().get(0).getX();
        int oY = newGame.getTestLevel().get(1).getChars().get(0).getX();

        if (newGame.getTestLevel().get(1).getChars().get(0) instanceof Ogre) {
            int cX = ((Ogre) newGame.getTestLevel().get(1).getChars().get(0)).getClubX();
            int cY = ((Ogre) newGame.getTestLevel().get(1).getChars().get(0)).getClubY();

            int nextOX, nextOY;
            int nextCX, nextCY;

            while (!OgreUpClubUp || !OgreUpClubLeft || !OgreUpClubDown || !OgreUpClubRight || !OgreLeftClubUp || !OgreLeftClubDown || !OgreLeftClubLeft || !OgreLeftClubRight || !OgreRightClubUp || !OgreRightClubLeft || !OgreRightClubDown || !OgreRightClubRight || !OgreDownClubUp || !OgreDownClubDown || !OgreDownClubLeft || !OgreDownClubRight) {

                newGame.getTestLevel().get(1).getChars().get(0).update(newGame.getTestLevel().get(1).getMap());

                nextOX = newGame.getTestLevel().get(1).getChars().get(0).getX();
                nextOY = newGame.getTestLevel().get(1).getChars().get(0).getY();
                nextCX = ((Ogre)newGame.getTestLevel().get(1).getChars().get(0)).getClubX();
                nextCY = ((Ogre)newGame.getTestLevel().get(1).getChars().get(0)).getClubY();

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
}
