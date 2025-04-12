package MoveStrategy;

import types.PieceType;

import java.util.ArrayList;

class MoveStrategyFactory {
    private static MoveStrategyFactory factory = null;
    L LStrategy = new L();
    X XStrategy = new X();
    EnPassant EnPassantStrategy = new EnPassant();
    Plus PlusStrategy = new Plus();
    PawnMove PawnMoveStrategy = new PawnMove();
    DoublePawnMove DoublePawnMoveStrategy = new DoublePawnMove();
    PawnTake PawnTakeStrategy = new PawnTake();

    private MoveStrategyFactory() {
    }

    public static MoveStrategyFactory getFactory() {
        if (factory == null) factory = new MoveStrategyFactory();
        return factory;
    }

    ArrayList<MoveStrategy> getStrategy(PieceType request) {
        ArrayList<MoveStrategy> result = new ArrayList<>();
        if (request == PieceType.Bishop) {
            result.add(XStrategy);
        }
        if (request == PieceType.Knight) {
            result.add(LStrategy);
        }
        if (request == PieceType.Rook) {
            result.add(PlusStrategy);
        }
        if (request == PieceType.Queen) {
            result.add(PlusStrategy);
            result.add(XStrategy);
        }
        if (request == PieceType.Pawn) {
            result.add(EnPassantStrategy);
            result.add(PawnMoveStrategy);
            result.add(DoublePawnMoveStrategy);
            result.add(PawnTakeStrategy);
            // need to implement pawn take
        }
        if (request == PieceType.King) {
            // to be implemented
        }

        return result;
    }
}
