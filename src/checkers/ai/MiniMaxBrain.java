package checkers.ai;

import checkers.game.Board;
import checkers.game.Move;

/**
 * Created by Ariel on 10/28/2016.
 */
public class MiniMaxBrain extends Brain{
    @Override
    public Move think(Board board, String player) {
        return null;
    }


//    public int returnMin(List<Integer> list) {
//        int min = Integer.MAX_VALUE;
//        int index = -1;
//        for (int i = 0; i < list.size(); ++i) {
//            if (list.get(i) < min) {
//                min = list.get(i);
//                index = i;
//            }
//        }
//        return list.get(index);
//    }
//
//    public int returnMax(List<Integer> list) {
//        int max = Integer.MIN_VALUE;
//        int index = -1;
//        for (int i = 0; i < list.size(); ++i) {
//            if (list.get(i) > max) {
//                max = list.get(i);
//                index = i;
//            }
//        }
//        return list.get(index);
//    }
//
//    public void callMinimax(int depth, int turn) {
//        rootsChildrenScores = new ArrayList<>();
//        minimax(depth, turn);
//    }
//
//    public int minimax(int depth, int turn) {
//
//        if (hasXWon()) return +1;
//        if (hasOWon()) return -1;
//
//        List<Point> pointsAvailable = getAvailableStates();
//        if (pointsAvailable.isEmpty()) return 0;
//
//        List<Integer> scores = new ArrayList<>();
//
//        for (int i = 0; i < pointsAvailable.size(); ++i) {
//            Point point = pointsAvailable.get(i);
//
//            if (turn == 1) { //X's turn select the highest from below minimax() call
//                placeAMove(point, 1);
//                int currentScore = minimax(depth + 1, 2);
//                scores.add(currentScore);
//
//                if (depth == 0)
//                    rootsChildrenScores.add(new PointsAndScores(currentScore, point));
//
//            } else if (turn == 2) {//O's turn select the lowest from below minimax() call
//                placeAMove(point, 2);
//                scores.add(minimax(depth + 1, 1));
//            }
//            board[point.x][point.y] = 0; //Reset this point
//        }
//        return turn == 1 ? returnMax(scores) : returnMin(scores);
//    }
}
