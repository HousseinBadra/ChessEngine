package types;

public class ChessMove {
    final Position from;
    final Position to;
    final ChessPiece piece;
    final ChessPiece destroy;
    final ChessMove sideEffect;
    final ChessPiece create;

    public ChessMove(Position from, Position to, ChessPiece piece, ChessPiece destroy, ChessMove sideEffect) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.destroy = destroy;
        this.sideEffect = sideEffect;
        this.create = null;
    }

    public ChessMove(Position promotionSquare, ChessPlayer player) {
        this.from = null;
        this.to = null;
        this.piece = null;
        this.destroy = null;
        this.sideEffect = null;
        this.create = new ChessPiece(promotionSquare, 0, player, null, PieceType.Promotion);
    }

    public ChessMove getClone() {
        return new ChessMove(this.from, this.to, this.piece.getClone(), this.destroy.getClone(), this.sideEffect.getClone());
    }
}
