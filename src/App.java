import java.util.Arrays;

enum Piece {
    X, O, EMPTY
}

class Game {
    private Piece[][] board = {
        {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
        {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
        {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
    };

    Piece[][] getBoard() {
        return this.board;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        
        System.out.println(Arrays.deepToString(game.getBoard()));
    }
}
