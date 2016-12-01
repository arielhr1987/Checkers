package checkers.ui;

import checkers.ai.AlphaBetaBrain;
import checkers.ai.Brain;
import checkers.ai.ComplexEvaluator;
import checkers.ai.Evaluator;
import checkers.game.Board;
import checkers.game.Game;
import checkers.game.Move;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CheckersApp extends Application {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];
    public static String turn = "w";
    private Game game;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    private Piece findPiece(int row, int col) {
        for (Node node : pieceGroup.getChildren()) {
            if (((Piece) node).getOldX() / TILE_SIZE == col && ((Piece) node).getOldY() / TILE_SIZE == row) {
                return (Piece) node;
            }
        }
        return null;
    }

    private void makeMove(Move move) {
        Piece piece = findPiece(move.getFromRow(), move.getFromCol());
        if (piece != null) {
            int newX = move.getToCol();
            int newY = move.getToRow();

            MoveResult result = tryMove(piece, newX, newY);

            int x0 = move.getFromCol();
            int y0 = move.getFromRow();

            switch (result.getType()) {
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
        }
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 1, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 1) {
                    piece = makePiece(PieceType.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 1) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
        if (newY == 0 || newY == 7) {
            piece.promote();
        }
        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (piece.getType() == PieceType.REDQUEEN || piece.getType() == PieceType.WHITEQUEEN) {
            if (board[newX][newY].hasPiece()) {        //
                return new MoveResult(MoveType.NONE);
            }
            if (Math.abs(newX - x0) == 1) { //
                return new MoveResult(MoveType.NORMAL);
            } else if (Math.abs(newX - x0) == 2) {//

                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;
                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                }
            }
        } else {
            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 1) {        //
                return new MoveResult(MoveType.NONE);
            }
            if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) { //
                return new MoveResult(MoveType.NORMAL);
            } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {//

                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;
                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                }
            }
        }
        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int) (pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Checkers App");
        primaryStage.setScene(scene);
        primaryStage.show();

        Evaluator evaluator = new ComplexEvaluator();
        Board board = new Board();
        board.setEvaluator(evaluator);
        Brain brain = new AlphaBetaBrain();
        game = new Game(board, brain);
    }

    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);


        piece.setOnMouseReleased(e -> {
            if (CheckersApp.turn == "b") {
                piece.abortMove();
            } else {
                int newX = toBoard(piece.getLayoutX());
                int newY = toBoard(piece.getLayoutY());

                MoveResult result = tryMove(piece, newX, newY);

                int x0 = toBoard(piece.getOldX());
                int y0 = toBoard(piece.getOldY());

                switch (result.getType()) {
                    case NONE:
                        piece.abortMove();
                        break;
                    case NORMAL:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        break;
                    case KILL:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);

                        Piece otherPiece = result.getPiece();
                        board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                        pieceGroup.getChildren().remove(otherPiece);
                        break;
                }
                if (result.getType() == MoveType.KILL || result.getType() == MoveType.NORMAL) {
                    this.game.makeMove(new Move(y0, x0, newY, newX));
                    CheckersApp.turn = "b";

                    //computer play
                    Move move = this.game.makeNextBlackMove();
                    this.makeMove(move);
                    CheckersApp.turn = "w";
                }
            }
        });

        return piece;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
