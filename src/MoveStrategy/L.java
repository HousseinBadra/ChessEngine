package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.Position;

import java.util.ArrayList;

class L extends MoveStrategy {

    L() {
        super();
    }

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove, Position position) {
        ArrayList<ChessMove> result = new ArrayList<>();
        // check forward
        Position checkingPosition;
        int[][] directions = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        for (int[] direction : directions) {
            checkingPosition = new Position(position.x + direction[0], position.y + direction[1]);
            if (!checkingPosition.isValid()) break;
            else if (board.get(checkingPosition.y).get(checkingPosition.x) == null) {
                result.add(new ChessMove(position, checkingPosition, piece.getClone(), null, null, Strategies.L, null, null));
            } else if (board.get(checkingPosition.y).get(checkingPosition.x).player != piece.player) {
                result.add(new ChessMove(position, checkingPosition, piece.getClone(), board.get(checkingPosition.y).get(checkingPosition.x).getClone(), null, Strategies.L, checkingPosition, null));
                break;
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