package dkeep.logic;

import dkeep.cli.UserInterface;

import java.util.ArrayList;

import java.util.Arrays;

//TODO: Arranjar colisoes, meter ogres a dar overlap bem (primeiro desenhar unmovables, depois movables no fim)

public class Main {

    public enum State {
        PLAY
    }

    private State state;

    public static void main(String[] args) {

        Game game = new Game();

        game.start();
    }
}
