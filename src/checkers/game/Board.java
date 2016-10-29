package checkers.game;

import checkers.ai.Evaluator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ariel on 10/28/2016.
 */
public class Board {

    private Evaluator evaluator;

    private String[][] matrix = {
            {null, "b", null, "b", null, "b", null, "b"},
            {"b", null, "b", null, "b", null, "b", null},
            {null, "b", null, "b", null, "b", null, "b"},
            {"", null, "", null, "", null, "", null},
            {null, "", null, "", null, "", null, ""},
            {"w", null, "w", null, "w", null, "w", null},
            {null, "w", null, "w", null, "w", null, "w"},
            {"w", null, "w", null, "w", null, "w", null}
    };

    public Board() {

    }

    public Board(Board board) {
        matrix = board.getMatrix();
    }

    public Board(String[][] matrix) {
        this.matrix = matrix;
    }

    public Board clone() {
        return new Board(this);
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public String get(Integer row, Integer col) {
        return matrix[row][col];
    }

    public void set(Integer row, Integer col, String value) {
        String currentValue = matrix[row][col];
        //the position is empty so we can set de position with the new value
        if (currentValue == "") {
            matrix[row][col] = value;
        }
    }

    public Integer countPiece(String piece) {
        Integer count = 0;
        for (int i = 0; i < matrix.length; i++) {
            int j = (i % 2 == 0) ? 0 : 1;
            for (; j < matrix[i].length; j += 2) {
                if (matrix[i][j] == piece) {
                    count++;
                }
            }
        }
        return count;
    }

    public Integer countWhite() {
        return countPiece("w") + countWhiteQueens();
    }

    public Integer countWhiteQueens() {
        return countPiece("W");
    }

    public Integer countBlack() {
        return countPiece("b") + countBlackQueens();
    }

    public Integer countBlackQueens() {
        return countPiece("B");
    }

    public boolean isComplete() {
        return (countBlack() == 0 || this.countWhite() == 0) ? true : false;
    }


//    public boolean isDraw(Player turn) {
//
//        Vector<Vector<Move>> possibleMoveSeq = Robot.expandMoves(this.duplicate(), turn);
//
//        if (possibleMoveSeq.isEmpty()) {
//            return true;
//
//        } else {
//            return false;
//        }
//    }

    public boolean isWhiteWinner() {
        return countBlack() == 0;
    }

    public boolean isBlackWinner() {
        return countWhite() == 0;
    }


    public List<Move> posibleMoves(Integer row, Integer col) {
        List<Move> moves = new LinkedList<Move>();

        moves.addAll(forcedMoves(row, col));
        String piece = get(row, col);

        if (piece == "b" || piece == "B" || piece == "W") {
            //move left down
            if (row < matrix.length - 1 && col >= 1 && get(row + 1, col - 1) == "") {
                moves.add(new Move(row, col, row + 1, col - 1));
            }
            //move right down
            if (row < matrix.length - 1 && col < matrix[0].length - 1 && get(row + 1, col + 1) == "") {
                moves.add(new Move(row, col, row + 1, col + 1));
            }
        }
        if (piece == "w" || piece == "W" || piece == "B") {
            //move left up
            if (row >= 1 && col >= 1 && get(row - 1, col - 1) == "") {
                moves.add(new Move(row, col, row - 1, col - 1));
            }
            //move right up
            if (row >= 1 && col < matrix[0].length - 1 && get(row - 1, col + 1) == "") {
                moves.add(new Move(row, col, row - 1, col - 1));
            }

        }
        return moves;
    }

    public List<Move> forcedMoves(Integer row, Integer col) {
        List<Move> moves = new LinkedList<Move>();

        String piece = get(row, col);

        if (piece == "b" || piece == "B" || piece == "W") {
            //capture left down
            if (row < matrix.length - 2 && col >= 2 && (get(row + 1, col - 1) == "w" || get(row + 1, col - 1) == "W") && get(row + 2, col - 2) == "") {
                moves.add(new Move(row, col, row + 2, col - 2));
            }
            //capture right down
            if (row < matrix.length - 2 && col < matrix[0].length - 2 && (get(row + 1, col + 1) == "w" || get(row + 1, col + 1) == "W") && get(row + 2, col + 2) == "") {
                moves.add(new Move(row, col, row + 2, col + 2));
            }
        }
        if (piece == "w" || piece == "W" || piece == "B") {
            //capture left up
            if (row >= 2 && col >= 2 && (get(row - 1, col - 1) == "b" || get(row - 1, col - 1) == "B") && get(row - 2, col - 2) == "") {
                moves.add(new Move(row, col, row - 2, col - 2));
            }
            //capture right up
            if (row >= 2 && col < matrix[0].length - 2 && (get(row - 1, col + 1) == "b" || get(row - 1, col + 1) == "B") && get(row - 2, col + 2) == "") {
                moves.add(new Move(row, col, row - 2, col + 2));
            }
        }
        return moves;
    }

    /**
     * Find all possible moves for a player
     *
     * @param piece
     * @return
     */
    public List<Move> allPosibleMoves(String piece) {
        List<Move> moves = new LinkedList<Move>();

        for (int i = 0; i < matrix.length; i++) {
            int j = (i % 2 == 0) ? 0 : 1;
            for (; j < matrix[i].length; j += 2) {
                if (matrix[i][j] == piece.toLowerCase() || matrix[i][j] == piece.toUpperCase()) {
                    moves.addAll(posibleMoves(i, j));
                }
            }
        }
        return moves;
    }

    public List<Move> allPosibleWhiteMoves() {
        return allPosibleMoves("w");
    }

    public List<Move> allPosibleBlackMoves() {
        return allPosibleMoves("b");
    }

    public void makeMove(Move move) {
        Integer r1 = move.getFromRow();
        Integer c1 = move.getFromCol();
        Integer r2 = move.getToRow();
        Integer c2 = move.getToCol();

        //we are capturing
        if (((Math.abs(r2 - r1) == 2) && (Math.abs(c2 - c1) == 2))) {
            Integer rowDif = r1 - r2;
            Integer colDif = c1 - c2;
            set(r1 - (rowDif / 2), c1 - colDif / 2, "");
        }
        set(r2, c2, get(r1, c1));
        set(r1, c1, "");

        // Promote To Queen
        if (get(r2, c2) == "b" && r2 == matrix.length - 1) {
            set(r2, c2, "B");
        } else if (get(r2, c2) == "w" && r2 == 0) {
            set(r2, c2, "W");
        }
    }

    public List<Board> expand(List<Move> moves) {
        List<Board> boards = new LinkedList<Board>();

        for (Move move : moves) {
            Board newBoard = this.clone();
            newBoard.makeMove(move);
        }
        return boards;
    }


}
