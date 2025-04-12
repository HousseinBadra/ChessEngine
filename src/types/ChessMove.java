package types;

import MoveStrategy.Strategies;

public class ChessMove {
    final Position from;
    final Position to;
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

    public ChessMove(Position from, Position to, ChessPiece piece, ChessPiece destroy, ChessMove sideEffect, Strategies stragie) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.destroy = destroy;
        this.sideEffect = sideEffect;
        this.create = null;
        this.stragie = stragie;
    }

//    public ChessMove(Position promotionSquare, ChessPlayer player, Strategies stragie) {
//        this.from = null;
//        this.to = null;
//        this.piece = null;
//        this.destroy = null;
//        this.sideEffect = null;
//        this.create = new ChessPiece(promotionSquare, 0, player, null, PieceType.Promotion);
//        this.stragie = stragie;
//    }
}
