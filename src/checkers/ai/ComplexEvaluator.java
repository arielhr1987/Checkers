package checkers.ai;

import checkers.game.Board;

/**
 * Created by Ariel on 10/28/2016.
 */
public class ComplexEvaluator implements Evaluator {

    public final int POINT_WON = 100000;
    public final int POINT_QUEEN = 2000;
    public final int POINT_NORMAL = 1000;
    public final int POINT_CENTRAL_PIECE = 100;
    public final int POINT_END_PIECE = 50;

    @Override
    public Integer evaluate(Board board, String player) {
        //String[][] matrix = board.getMatrix();
        //return 1;

        int value = 0;
        if (board.isWinner(player)) {
            value += POINT_WON;
        } else {
            value += whiteBlackPiecesDifferencePoints(board);
            value += boardPositionPoints(board);
            //value /= board.countBlack();
        }
        return value;
    }

    private int whiteBlackPiecesDifferencePoints(Board board) {

        int value = 0;
        // Scan across the board
        for (int r = 0; r < board.getMatrix().length; r++) {
            // Check only valid cols
            int c = (r % 2 == 0) ? 0 : 1;
            for (; c < board.getMatrix()[0].length; c += 2) {
                String entry = board.get(r, c);
                if (entry == "w") {
                    value += POINT_NORMAL;
                } else if (entry == "W") {
                    value += POINT_QUEEN;
                } else if (entry == "b") {
                    value -= POINT_NORMAL;
                } else if (entry == "B") {
                    value -= POINT_QUEEN;
                }
            }
        }
        return value;
    }

    private int boardPositionPoints(Board board) {

        int value = 0;

        // Central Points
        if (board.get(3, 3) == "w" || board.get(3, 3) == "W" || board.get(3, 5) == "w" || board.get(3, 5) == "W") {
            value += POINT_CENTRAL_PIECE;
        }
        if (board.get(4, 2) == "b" || board.get(4, 2) == "B" || board.get(4, 4) == "b" || board.get(4, 4) == "B") {
            value -= POINT_CENTRAL_PIECE;
        }

        // End Points
        if (board.get(0, 0) == "w" || board.get(0, 2) == "w" || board.get(0, 4) == "w" || board.get(0, 6) == "w") {
            value += POINT_END_PIECE;
        }

        if (board.get(7, 1) == "b" || board.get(7, 3) == "b" || board.get(7, 5) == "b" || board.get(7, 7) == "b") {
            value -= POINT_END_PIECE;
        }

        return value;
    }
}
