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

    public Game(Board board, Brain brain) {
        this.board = board;
        this.brain = brain;
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

    public Move makeNextBlackMove() {
        if(board.isDraw("b")){
            System.out.println("Draw");
        }
        Move move = this.brain.think(board, "b");
        board.makeMove(move);
        return move;
    }

    public void makeMove(Move move) {
        board.makeMove(move);
    }
}
