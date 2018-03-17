package dkeep.logic;

//TODO meter drunken a dormir mais que uma ronda, organizar funcoes

public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        game.loadLevel1();

        game.loadLevel2();

        game.start();
    }
}
