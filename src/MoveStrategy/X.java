package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.Position;

import java.util.ArrayList;

class X extends MoveStrategy {

    X() {
        super();
    }

    public ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece) {
        ArrayList<ChessMove> result = new ArrayList<>();
        // check forward
        int counter = 0;
        Position checkingPosition = null;
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] direction : directions) {
            counter = 0;
            while (counter < 8) {
                checkingPosition = new Position(piece.getPosition().x + counter + direction[0], piece.getPosition().y + direction[1]);
                if (!checkingPosition.isValid()) break;
                else if (board.get(checkingPosition.y).get(checkingPosition.x) == null) {
                    result.add(new ChessMove(piece.getPosition(), checkingPosition, piece.getClone(), null, null));
                } else if (board.get(checkingPosition.y).get(checkingPosition.x).player != piece.player) {
                    result.add(new ChessMove(piece.getPosition(), checkingPosition, piece.getClone(), board.get(checkingPosition.y).get(checkingPosition.x).getClone(), null));
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
    boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, ChessPiece defender) {
        return false;
    }
}
