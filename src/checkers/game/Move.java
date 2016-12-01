package checkers.game;

/**
 * Created by Ariel on 10/28/2016.
 */
public class Move {
    private Integer fromRow;
    private Integer fromCol;

    private Integer toRow;
    private Integer toCol;


    public Move() {

    }

    public Move(Integer fromRow, Integer fromCol, Integer toRow, Integer toCol) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    public Integer getFromRow() {
        return fromRow;
    }

    public void setFromRow(Integer fromRow) {
        this.fromRow = fromRow;
    }

    public Integer getFromCol() {
        return fromCol;
    }

    public void setFromCol(Integer fromCol) {
        this.fromCol = fromCol;
    }

    public Integer getToRow() {
        return toRow;
    }

    public void setToRow(Integer toRow) {
        this.toRow = toRow;
    }

    public Integer getToCol() {
        return toCol;
    }

    public void setToCol(Integer toCol) {
        this.toCol = toCol;
    }

}
