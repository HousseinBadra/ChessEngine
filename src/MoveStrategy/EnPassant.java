package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.ChessPlayer;
import types.Position;

import java.util.ArrayList;

class EnPassant extends MoveStrategy {
    EnPassant() {
        super();
    }

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove, Position position) {
        ArrayList<ChessMove> result = new ArrayList<>();
        if (lastMove == null) return result;
        // check white
        int[] directions = {-1, 1};
        if (piece.player == ChessPlayer.White && position.y == 4) {
            for (int direction : directions) {
                Position checkingPosition = new Position(position.x + direction, position.y);
                Position newPosition = new Position(position.x + direction, position.y + 1);
                ChessPiece target = board.get(checkingPosition.y).get(checkingPosition.x);
                if (checkingPosition.isValid() && target != null && target.player == ChessPlayer.Black) {
                    boolean EnPassantWasPreviousMove = lastMove.getStragie() == Strategies.DoublePawnMove && checkingPosition.equals(lastMove.getTo());
                    if (EnPassantWasPreviousMove)
                        result.add(new ChessMove(position, newPosition, piece.getClone(), board.get(checkingPosition.y).get(checkingPosition.x).getClone(), null, Strategies.EnPassant, checkingPosition, null));
                }
            }

        }
        if (piece.player == ChessPlayer.Black && position.y == 3) {
            for (int direction : directions) {
                Position checkingPosition = new Position(position.x + direction, position.y);
                Position newPosition = new Position(position.x + direction, position.y - 1);
                ChessPiece target = board.get(checkingPosition.y).get(checkingPosition.x);
                if (checkingPosition.isValid() && target != null && target.player == ChessPlayer.White) {
                    boolean EnPassantWasPreviousMove = lastMove.getStragie() == Strategies.DoublePawnMove && checkingPosition.equals(lastMove.getTo());
                    if (EnPassantWasPreviousMove)
                        result.add(new ChessMove(position, newPosition, piece.getClone(), board.get(checkingPosition.y).get(checkingPosition.x).getClone(), null, Strategies.EnPassant, checkingPosition, null));
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
