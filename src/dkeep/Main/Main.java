package dkeep.Main;

import dkeep.Hardcoded.GameLevels;
import dkeep.cli.UserInterface;
import dkeep.logic.Game;
import dkeep.logic.Level;

//TODO
public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        GameLevels.loadLevel1("Rookie", game);

        GameLevels.loadLevel2(1, game);

        start(game);
    }

    private static void start(Game game){
        UserInterface.Direction input;
        boolean stop;
        boolean lose;

        for (Level lvl : game.getLevels()) {
            lvl.draw();
            UserInterface.printMap(lvl.getMapCopy());
            stop = false;
            lose = false;
            while (true){
                input = UserInterface.userInput();
                game.userMove(input);
                UserInterface.printMap(lvl.getMapCopy());
                switch (game.getLevelState()) {
                    case WIN:
                        System.out.println("You won the game! Congrats ");
                        stop = true;
                        break;
                    case LOSE:
                        System.out.println("The villain has restrained you, you LOST ! :( ");
                        stop = true;
                        lose = true;
                        game.setState(Game.gameState.LOSE);
                        break;
                    case NONE: break;}
                if(stop) break;}
            if(lose) return;
            game.incLevel(); }game.setState(Game.gameState.WIN);
    }
}
