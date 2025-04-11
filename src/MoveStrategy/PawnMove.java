package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.ChessPlayer;
import types.Position;

import java.util.ArrayList;

class PawnMove extends MoveStrategy {

    PawnMove() {
        super();
    }

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece) {
        ArrayList<ChessMove> result = new ArrayList<>();
        // check white
        if (piece.player == ChessPlayer.White) {
            if (board.get(piece.getPosition().y + 1).get(piece.getPosition().x) == null) {
                Position newPosition = new Position(piece.getPosition().x, piece.getPosition().y +1);
                ChessMove sideEffect = null;
                if(piece.getPosition().x == 6) {
                    sideEffect = new ChessMove(newPosition, ChessPlayer.White);
                }
                result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), null, sideEffect));
            }
        }
        if (piece.player == ChessPlayer.Black) {
            if (board.get(piece.getPosition().y - 1).get(piece.getPosition().x) == null) {
                Position newPosition = new Position(piece.getPosition().x, piece.getPosition().y -1);
                ChessMove sideEffect = null;
                if(piece.getPosition().x == 1) {
                    sideEffect = new ChessMove(newPosition, ChessPlayer.Black);
                }
                result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), null, sideEffect));
            }
        }
        return result;
    }

    @Override
    boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, ChessPiece defender) {
        return false;
    }
}
