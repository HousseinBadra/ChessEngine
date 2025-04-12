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

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove) {
        ArrayList<ChessMove> result = new ArrayList<>();
        int[] directions = {-1, 1};
        if (piece.player == ChessPlayer.White) {
            for (int direction : directions) {
                Position newPosition = new Position(piece.getPosition().x + direction, piece.getPosition().y + 1);
                if (newPosition.isValid()) {
                    ChessPiece target = board.get(newPosition.y).get(newPosition.x);
                    if (target != null && target.player != piece.player)
                        result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), target, null, Strategies.PawnTake));
                }
            }
        }
        if (piece.player == ChessPlayer.Black) {
            for (int direction : directions) {
                Position newPosition = new Position(piece.getPosition().x + direction, piece.getPosition().y - 1);

                if (newPosition.isValid()) {
                    ChessPiece target = board.get(newPosition.y).get(newPosition.x);
                    if (target != null && target.player != piece.player)
                        result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), target, null, Strategies.PawnTake));
                }
            }
        }

        return result;
    }

    @Override
    public boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, Position target, ChessMove lastMove) {
        ArrayList<ChessMove> moves = generatePossibleMoves(board, attacker, lastMove);
        for (ChessMove move : moves) {
            if (move.getTo().equals(target)) {
                return true;
            }

        }
        return false;
    }
}
