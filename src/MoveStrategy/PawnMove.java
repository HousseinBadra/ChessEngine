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

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove, Position position) {
        ArrayList<ChessMove> result = new ArrayList<>();
        // check white
        if (piece.player == ChessPlayer.White) {
            if (board.get(position.y + 1).get(position.x) == null) {
                Position newPosition = new Position(position.x, position.y + 1);
                // handle promotion later
//                if(piece.getPosition().x == 6) {}
                result.add(new ChessMove(position, newPosition, piece.getClone(), null, null, Strategies.PawnMove, null, null));
            }
        }
        if (piece.player == ChessPlayer.Black) {
            if (board.get(position.y - 1).get(position.x) == null) {
                Position newPosition = new Position(position.x, position.y - 1);
                // handle promotion later
//                if(piece.getPosition().x == 1) {}
                result.add(new ChessMove(position, newPosition, piece.getClone(), null, null, Strategies.PawnMove, null, null));
            }
        }
        return result;
    }

    @Override
    public boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, Position target, ChessMove lastMove, Position position) {
        return false;
    }
}
