package dkeep.logic;

import dkeep.cli.UserInterface;

import java.util.ArrayList;

import java.util.Arrays;

//TODO meter drunken a dormir mais que uma ronda, arranjar esparguete dos guards, organizar funçoes

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
