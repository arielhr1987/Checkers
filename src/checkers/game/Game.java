package checkers.game;

import checkers.ai.Brain;

/**
 * Created by Ariel on 10/28/2016.
 */
public class Game {

    private Board board;

    private Brain brain;

    public Game() {

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Brain getBrain() {
        return brain;
    }

    public void setBrain(Brain brain) {
        this.brain = brain;
    }



}
