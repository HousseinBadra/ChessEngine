import MoveStrategy.Strategies;
import types.*;

import java.util.ArrayList;

public class ChessGame {

    private final ArrayList<ArrayList<ChessPiece>> board;
    private final ArrayList<ChessMove> moveHistory;
    private ChessPlayer currentPlayer;
    private int totalMoves;

    public ChessGame() {
        this.board = ChessBoardFactory.createStartingBoard();
        this.moveHistory = new ArrayList<>();
        moveHistory.add(null);
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

    public Position findKing(ChessPlayer kingColor) {
        Position position = null;
        for (int i = 0; i < board.size(); i++) {
            ArrayList<ChessPiece> row = board.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) != null && row.get(j).player == kingColor && row.get(j).type == PieceType.King) {
                    position = new Position(j, i);
                }
            }
        }
        return position;
    }

    public boolean isKingInCheck(ChessPlayer kingColor) {
        Position kingPosition = findKing(kingColor);
        ChessPiece king = board.get(kingPosition.y).get(kingPosition.x);
        for (int i = 0; i < board.size(); i++) {
            ArrayList<ChessPiece> row = board.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) != null) {
                    if (row.get(j).canAttack(board, king, moveHistory.getLast(), new Position(j, i), kingPosition)) {
                        return true;
                    }

                }
            }
        }
        return false;

    }

    private boolean isMoveLegal(ChessMove move, ChessPlayer player) {
        if (move.getStragie() == Strategies.KingMove) return true;
        applyMove(move);
        boolean result = !isKingInCheck(player);
        undoMove(move);
        return result;
    }

    public ArrayList<ChessMove> getLegalMoves(Position position) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        ArrayList<ChessMove> result = new ArrayList<>();
        ChessPiece piece = board.get(position.y).get(position.x);
        if (piece == null) return moves;
        moves.addAll(piece.generateMoves(board, moveHistory.getLast(), position));
        for (ChessMove move : moves) {
            if (isMoveLegal(move, piece.player)) {
                result.add(move);
            }
        }
        return result;

    }

    public boolean isCheckmate(ChessPlayer kingColor) {
        // Stub: Add your move generator and king attack checker here
        if (!isKingInCheck(kingColor)) return false;
        for (int i = 0; i < board.size(); i++) {
            ArrayList<ChessPiece> row = board.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) != null && row.get(j).player == kingColor) {
                    if (!getLegalMoves(new Position(j, i)).isEmpty()) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    public boolean isStalemate(ChessPlayer kingColor) {
        if (isKingInCheck(kingColor)) return false;
        for (int i = 0; i < board.size(); i++) {
            ArrayList<ChessPiece> row = board.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) != null && row.get(j).player == kingColor) {
                    if (!getLegalMoves(new Position(j, i)).isEmpty()) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    public ArrayList<ChessMove> getMoveHistory() {
        return moveHistory;
    }

    public void renderBoard() {
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " ");
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board.get(row).get(col);
                if (piece != null) {
                    System.out.print(piece.render() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println((' '));
        }
    }
}
