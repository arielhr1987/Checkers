package checkers.ui;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum PieceType {
    RED(1), WHITE(-1), REDQUEEN(2), WHITEQUEEN(-2) ;

    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}
