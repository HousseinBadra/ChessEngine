import types.Position;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.renderBoard();
        System.out.print(game.getLegalMoves(new Position(1, 0)).get(0).toString());
    }
}