package checkers;

import checkers.ai.Evaluator;

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
        return countPiece("w") + countWhiteKings();
    }

    public Integer countWhiteKings() {
        return countPiece("W");
    }

    public Integer countBlack() {
        return countPiece("b") + countBlackKings();
    }

    public Integer countBlackKings() {
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

    public void move() {

    }


}
