package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.ChessPlayer;
import types.Position;

import java.util.ArrayList;

public class KingMove extends MoveStrategy {
    KingMove() {
        super();
    }

    static boolean isPositionAttacked(ArrayList<ArrayList<ChessPiece>> board, Position target, ChessPlayer player) {
        for (ArrayList<ChessPiece> row : board) {
            for (ChessPiece piece : row) {
                if (piece != null && piece.player != player)
                    for (MoveStrategy stg : piece.strategy)
                        if (stg.canAttack(board, piece, target, null)) {
                            return true;
                        }
            }
        }
        return false;
    }

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove) {
        ArrayList<ChessMove> result = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] direction : directions) {
            Position newPosition = new Position(piece.getPosition().x + direction[0], piece.getPosition().y + direction[1]);
            if (newPosition.isValid() && !isPositionAttacked(board, newPosition, piece.player)) {
                if (board.get(newPosition.y).get(newPosition.x) == null) {
                    result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), null, null, Strategies.KingMove));
                } else if (board.get(newPosition.y).get(newPosition.x).player != piece.player) {
                    result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), board.get(newPosition.y).get(newPosition.x).getClone(), null, Strategies.KingMove));
                }
            }
        }
        return result;
    }

    public boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, Position target, ChessMove lastMove) {

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] direction : directions) {
            Position newPosition = new Position(attacker.getPosition().x + direction[0], attacker.getPosition().y + direction[1]);
            if(newPosition.isValid()) {
                ChessPiece targetPiece = board.get(newPosition.y).get(newPosition.x);
                if (newPosition.x == target.x && newPosition.y == target.y) {
                    return targetPiece == null || targetPiece.player != attacker.player;
                }
            }


        }
        return false;
    }
}
