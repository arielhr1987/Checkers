package checkers.ai;

import checkers.game.Board;
/**
 * Created by Ariel on 10/28/2016.
 */
public class SimpleEvaluator implements Evaluator{

    @Override
    public Integer evaluate(Board board) {
        String[][] matrix = board.getMatrix();
        return 1;
    }
}
