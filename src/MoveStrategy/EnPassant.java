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

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece) {
        ArrayList<ChessMove> result = new ArrayList<>();
        // check white
        int[] directions = {-1, 1};
        if (piece.player == ChessPlayer.White && piece.getPosition().y == 4) {
            for (int direction : directions) {
                Position checkingPosition = new Position(piece.getPosition().x + direction, piece.getPosition().y);
                Position newPosition = new Position(piece.getPosition().x + direction, piece.getPosition().y + 1);
                ChessPiece target = board.get(checkingPosition.y).get(checkingPosition.x);
                // this will cause a bug cause en passant is valid for only I round will check later
                if (checkingPosition.isValid() && target != null && target.player == ChessPlayer.Black && target.getNumberOfMoves() == 1) {
                    result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), board.get(checkingPosition.y).get(checkingPosition.x).getClone(), null));
                }
            }

        }
        if (piece.player == ChessPlayer.Black && piece.getPosition().y == 3) {
            for (int direction : directions) {
                Position checkingPosition = new Position(piece.getPosition().x + direction, piece.getPosition().y);
                Position newPosition = new Position(piece.getPosition().x + direction, piece.getPosition().y - 1);
                ChessPiece target = board.get(checkingPosition.y).get(checkingPosition.x);
                if (checkingPosition.isValid() && target != null && target.player == ChessPlayer.White && target.getNumberOfMoves() == 1) {
                    result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), board.get(checkingPosition.y).get(checkingPosition.x).getClone(), null));
                }
            }

        }
        return result;
    }

    @Override
    boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, ChessPiece defender) {
        return false;
    }
}
