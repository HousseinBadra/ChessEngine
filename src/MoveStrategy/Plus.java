package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.Position;

import java.util.ArrayList;

class Plus extends MoveStrategy {

    Plus() {
        super();
    }

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece, ChessMove lastMove, Position position) {
        ArrayList<ChessMove> result = new ArrayList<>();
        // check forward
        int counter;
        Position checkingPosition;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            counter = 0;
            while (counter < 8) {
                checkingPosition = new Position(position.x + counter + direction[0], position.y + direction[1]);
                if (!checkingPosition.isValid()) break;
                else if (board.get(checkingPosition.y).get(checkingPosition.x) == null) {
                    result.add(new ChessMove(position, checkingPosition, piece.getClone(), null, null, Strategies.Plus, null, null));
                } else if (board.get(checkingPosition.y).get(checkingPosition.x).player != piece.player) {
                    result.add(new ChessMove(position, checkingPosition, piece.getClone(), board.get(checkingPosition.y).get(checkingPosition.x).getClone(), null, Strategies.Plus, checkingPosition, null));
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
