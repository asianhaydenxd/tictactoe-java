import java.util.Arrays;

enum Piece {
    X, O, EMPTY
}

class Game {
    Piece[][] board = {
        {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
        {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
        {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
    };

    Piece getWinner() {
        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] == this.board[i][1] && this.board[i][0] == this.board[i][2]) {
                return this.board[i][0];
            }
            if (this.board[0][i] == this.board[1][i] && this.board[0][i] == this.board[2][i]) {
                return this.board[0][i];
            }
        }
        if (this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2]) {
            return this.board[0][0];
        }
        if (this.board[0][2] == this.board[1][1] && this.board[0][2] == this.board[2][0]) {
            return this.board[0][2];
        }
        return Piece.EMPTY;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        
        System.out.println(Arrays.deepToString(game.board));
    }
}
