package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.Position;

import java.util.ArrayList;

class X extends MoveStrategy {

    X() {
        super();
    }

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove) {
        ArrayList<ChessMove> result = new ArrayList<>();
        // check forward
        int counter;
        Position checkingPosition;
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] direction : directions) {
            counter = 0;
            while (counter < 8) {
                checkingPosition = new Position(piece.getPosition().x + counter + direction[0], piece.getPosition().y + direction[1]);
                if (!checkingPosition.isValid()) break;
                else if (board.get(checkingPosition.y).get(checkingPosition.x) == null) {
                    result.add(new ChessMove(piece.getPosition(), checkingPosition, piece.getClone(), null, null, Strategies.X));
                } else if (board.get(checkingPosition.y).get(checkingPosition.x).player != piece.player) {
                    result.add(new ChessMove(piece.getPosition(), checkingPosition, piece.getClone(), board.get(checkingPosition.y).get(checkingPosition.x).getClone(), null, Strategies.X));
                    break;
                } else {
                    break;
                }
                counter++;
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
