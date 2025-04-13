import types.*;
import java.util.*;

public class ChessGame {

    private final ArrayList<ArrayList<ChessPiece>> board;
    private final ArrayList<ChessMove> moveHistory;
    private ChessPlayer currentPlayer;
    private int totalMoves;

    public ChessGame() {
        this.board = ChessBoardFactory.createStartingBoard();
        this.moveHistory = new ArrayList<>();
        this.currentPlayer = ChessPlayer.White;
        this.totalMoves = 0;
    }

    public void applyMove(ChessMove move) {
        Position from = move.getFrom();
        Position to = move.getTo();

        // handle destruction
        if (move.getDestroy() != null && move.getDestroyPosition() != null) {
            board.get(move.getDestroyPosition().y).set(move.getDestroyPosition().x, null);
        }

        //handle move
        ChessPiece piece = board.get(from.y).get(from.x);
        piece.moveSideEffect();

        board.get(to.y).set(to.x, piece);
        board.get(from.y).set(from.x, null);

        // handle creation
        if (move.getCreate() != null && move.getCreatePosition() != null) {
            board.get(move.getCreatePosition().y).set(move.getCreatePosition().x, move.getCreate());
        }

        if (move.getSideEffect() != null) {
            applyMove(move.getSideEffect()); // e.g., en passant or castling rook move
        }

        moveHistory.add(move);
        togglePlayer();
        totalMoves++;
    }

    public void undo() {
        if (moveHistory.isEmpty()) return;
        ChessMove move = moveHistory.getLast();
        moveHistory.removeLast();
        togglePlayer();
        totalMoves--;
        undoMove(move);
    }

    public void undoMove(ChessMove move) {

        Position from = move.getFrom();
        Position to = move.getTo();

        if (move.getSideEffect() != null) {
            undoMove(move.getSideEffect()); // e.g., en passant or castling rook move
        }

        // handle creation
        if (move.getCreate() != null && move.getCreatePosition() != null) {
            board.get(move.getCreatePosition().y).set(move.getCreatePosition().x, null);
        }

        //handle move
        ChessPiece piece = board.get(to.y).get(to.x);
        piece.undoMoveSideEffect();

        board.get(to.y).set(to.x, null);
        board.get(from.y).set(from.x, piece);

        // handle destruction
        if (move.getDestroy() != null && move.getDestroyPosition() != null) {
            board.get(move.getDestroyPosition().y).set(move.getDestroyPosition().x, move.getDestroy());
        }

    }

    public void togglePlayer() {
        currentPlayer = (currentPlayer == ChessPlayer.White) ? ChessPlayer.Black : ChessPlayer.White;
    }

    public ChessPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public ArrayList<ArrayList<ChessPiece>> getBoard() {
        return board;
    }

    public boolean isCheckmate() {
        // Stub: Add your move generator and king attack checker here
        return false;
    }

    public boolean isStalemate() {
        // Stub: Add logic to check for legal moves and non-check condition
        return false;
    }

    public ArrayList<ChessMove> getMoveHistory() {
        return moveHistory;
    }
}
