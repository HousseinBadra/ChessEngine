package MoveStrategy;

import types.ChessMove;
import types.ChessPiece;
import types.Position;

import java.util.ArrayList;

public abstract class MoveStrategy {

    MoveStrategy() {
    }

    abstract ArrayList<ChessMove> generatePossibleMoves(ArrayList<ArrayList<ChessPiece>> board, ChessPiece piece);
    abstract boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attacker, ChessPiece defender);
}
