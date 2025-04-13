package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.ChessPlayer;
import types.Position;

import java.util.ArrayList;

public class PawnTake extends MoveStrategy {
    PawnTake() {
        super();
    }

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove, Position position) {
        ArrayList<ChessMove> result = new ArrayList<>();
        int[] directions = {-1, 1};
        if (piece.player == ChessPlayer.White) {
            for (int direction : directions) {
                Position newPosition = new Position(position.x + direction, position.y + 1);
                if (newPosition.isValid()) {
                    ChessPiece target = board.get(newPosition.y).get(newPosition.x);
                    if (target != null && target.player != piece.player)
                        result.add(new ChessMove(position, newPosition, piece.getClone(), target, null, Strategies.PawnTake, newPosition, null));
                }
            }
        }
        if (piece.player == ChessPlayer.Black) {
            for (int direction : directions) {
                Position newPosition = new Position(position.x + direction, position.y - 1);
                if (newPosition.isValid()) {
                    ChessPiece target = board.get(newPosition.y).get(newPosition.x);
                    if (target != null && target.player != piece.player)
                        result.add(new ChessMove(position, newPosition, piece.getClone(), target, null, Strategies.PawnTake, newPosition, null));
                }
            }
        }

        return result;
    }

    @Override
    public boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, Position target, ChessMove lastMove, Position position) {
        ArrayList<ChessMove> moves = generatePossibleMoves(board, attacker, lastMove, position);
        for (ChessMove move : moves) {
            if (move.getTo().equals(target)) {
                return true;
            }

        }
        return false;
    }
}
