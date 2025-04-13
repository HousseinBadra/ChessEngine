package types;

import MoveStrategy.Strategies;

public class ChessMove {
    final Position from;
    final Position to;
    final Position destroyPosition;
    final Position createPosition;
    final ChessPiece piece;
    final ChessPiece destroy;
    final ChessMove sideEffect;
    final ChessPiece create;
    final Strategies stragie;

    public Position getTo() {
        return to;
    }

    public Position getFrom() {
        return from;
    }



    public Strategies getStragie() {
        return stragie;
    }

    public ChessMove(Position from, Position to, ChessPiece piece, ChessPiece destroy, ChessMove sideEffect, Strategies stragie, Position destroyPosition, Position createPosition) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.destroy = destroy;
        this.sideEffect = sideEffect;
        this.create = null;
        this.stragie = stragie;
        this.createPosition = createPosition;
        this.destroyPosition = destroyPosition;
    }

    public ChessMove(Position from, Position to, ChessPiece piece, ChessPiece destroy, ChessPiece create, ChessMove sideEffect, Strategies stragie, Position destroyPosition, Position createPosition) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.destroy = destroy;
        this.create = create;
        this.sideEffect = sideEffect;
        this.stragie = stragie;
        this.createPosition = createPosition;
        this.destroyPosition = destroyPosition;
    }
}
