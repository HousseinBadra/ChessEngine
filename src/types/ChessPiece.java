package types;

import MoveStrategy.MoveStrategy;

import java.util.ArrayList;

public class ChessPiece {
    private Position position;
    private boolean hasMoved;
    public final int weight;
    public final ChessPlayer player;
    public final PieceType type;
    public final MoveStrategy[] strategy;

    ChessPiece(Position position, int weight, ChessPlayer player, MoveStrategy[] strategy, PieceType type) {
        this.position = position;
        this.weight = weight;
        this.player = player;
        this.strategy = strategy;
        this.type = type;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ChessPiece getClone() {
        return new ChessPiece(this.position.getClone(), this.weight, this.player, this.strategy, this.type);
    }

    public boolean canAttack(ArrayList<ArrayList<ChessPiece>> board, ChessPiece attackedPiece, ChessMove lastMove) {
        for (MoveStrategy strategy : this.strategy) {
            if (this.player == attackedPiece.player) return false;
            if (strategy.canAttack(board, this, attackedPiece.position, lastMove)) {
                return true;
            }
        }
        return false;
    }
}

