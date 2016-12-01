package checkers.ai;

import checkers.game.Board;
/**
 * Created by Ariel on 10/28/2016.
 */
public class SimpleEvaluator implements Evaluator{

    public final int POINT_WON = 100000;
    public final int POINT_QUEEN = 2000;
    public final int POINT_NORMAL = 1000;

    @Override
    public Integer evaluate(Board board, String player) {

        int value = 0;
        if(board.isWinner(player)){
            value += POINT_WON;
        }else{
            value = whiteBlackPiecesDifferencePoints(board);
            //value /= board.blackPieces;
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
}
