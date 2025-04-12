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

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove) {
        ArrayList<ChessMove> result = new ArrayList<>();
        // check white
        if (piece.player == ChessPlayer.White) {
            if (board.get(piece.getPosition().y + 1).get(piece.getPosition().x) == null) {
                Position newPosition = new Position(piece.getPosition().x, piece.getPosition().y + 1);
                // handle promotion later
//                if(piece.getPosition().x == 6) {}
                result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), null, null, Strategies.PawnMove));
            }
        }
        if (piece.player == ChessPlayer.Black) {
            if (board.get(piece.getPosition().y - 1).get(piece.getPosition().x) == null) {
                Position newPosition = new Position(piece.getPosition().x, piece.getPosition().y - 1);
                // handle promotion later
//                if(piece.getPosition().x == 1) {}
                result.add(new ChessMove(piece.getPosition(), newPosition, piece.getClone(), null, null, Strategies.PawnMove));
            }
        }
        return result;
    }

    @Override
    public boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, Position target, ChessMove lastMove) {
        return false;
    }
}
