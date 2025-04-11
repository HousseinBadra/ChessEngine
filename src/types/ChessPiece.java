package types;

import MoveStrategy.MoveStrategy;

public class ChessPiece {
    private Position position;
    private int numberOfMoves = 0;
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

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
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
}

