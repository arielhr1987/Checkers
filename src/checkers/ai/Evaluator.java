package checkers.ai;

import checkers.game.Board;

/**
 * Created by Ariel on 10/28/2016.
 */
public interface Evaluator {

    public Integer evaluate(Board board);
}
