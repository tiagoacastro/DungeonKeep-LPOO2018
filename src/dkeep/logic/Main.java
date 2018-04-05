package dkeep.logic;

//TODO
public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        game.loadLevel1("Rookie");

        game.loadLevel2(1);

        game.start();
    }
}
