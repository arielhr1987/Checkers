package checkers.ai;

import checkers.game.Board;
import checkers.game.Move;

import java.util.LinkedList;

/**
 * Created by Ariel on 10/28/2016.
 */
public class AlphaBetaBrain extends Brain {



    public Move think(Board board, String player) {

        LinkedList<Move> resultantMoveSeq = new LinkedList<>();

        alphaBeta(board, "b", 0, Integer.MIN_VALUE, Integer.MAX_VALUE, resultantMoveSeq);

        //Apply the move to the game board.
//        for(Move m:resultantMoveSeq){
//            Game.board.genericMakeWhiteMove(m);
//        }

        return resultantMoveSeq.size() == 0 ? null : resultantMoveSeq.get(0);
    }

    private int alphaBeta(Board board, String player, int depth, int alpha, int beta, LinkedList<Move> resultMoveSeq) {

        if (!board.canExploreFurther(board, player, depth)) {
            return board.evaluate(player);
        }

        LinkedList<Move> possibleMoveSeq = board.allPosibleMoves(player);
        LinkedList<Board> possibleBoardConf = board.expand(possibleMoveSeq);

        Move bestMoveSeq = null;

        if (player.toLowerCase() == "w") {

            for (int i = 0; i < possibleBoardConf.size(); i++) {

                Board b = possibleBoardConf.get(i);
                Move moveSeq = possibleMoveSeq.get(i);

                int value = alphaBeta(b, "b", depth + 1, alpha, beta, resultMoveSeq);

                if (value > alpha) {
                    alpha = value;
                    bestMoveSeq = moveSeq;
                }
                if (alpha > beta) {
                    break;
                }
            }

            //If the depth is 0, copy the bestMoveSeq in the result move seq.
            if (depth == 0 && bestMoveSeq != null) {
                resultMoveSeq.clear();
                resultMoveSeq.add(bestMoveSeq);
            }

            return alpha;

        } else {
            //assert (player.toLowerCase() == "b");

            for (int i = 0; i < possibleBoardConf.size(); i++) {

                Board b = possibleBoardConf.get(i);
                Move moveSeq = possibleMoveSeq.get(i);

                int value = alphaBeta(b, "w", depth + 1, alpha, beta, resultMoveSeq);
                if (value < beta) {
                    bestMoveSeq = moveSeq;
                    beta = value;
                }
                if (alpha > beta) {
                    break;
                }
            }
            //If the depth is 0, copy the bestMoveSeq in the result move seq.
            if (depth == 0 && bestMoveSeq != null) {
                resultMoveSeq.clear();
                resultMoveSeq.add(bestMoveSeq);
            }

            return beta;
        }
    }
}
