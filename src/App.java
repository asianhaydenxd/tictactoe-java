import java.util.Arrays;

enum Piece {
    X, O
}

class Slot {
    Piece piece;

    public Slot(Piece newPiece) {
        piece = newPiece;
    }
}

class Game {
    Slot[] board = new Slot[9];

    public Game() {
        for (int i = 0; i < 9; i++) {
            this.board[i] = new Slot(null);
        }
    }

    Slot getSlot(int row, int col) {
        return this.board[row*3+col];
    }

    void setSlot(int row, int col, Piece piece) {
        this.board[row*3+col].piece = piece;
    }

    Piece getWinner() {
        if (this.getSlot(0, 0) == this.getSlot(1, 1) && this.getSlot(1, 1) == this.getSlot(2, 2)) {
            return this.getSlot(0, 0).piece;
        }
        if (this.getSlot(0, 2) == this.getSlot(1, 1) && this.getSlot(1, 1) == this.getSlot(2, 0)) {
            return this.getSlot(0, 2).piece;
        }
        for (int i = 0; i < 3; i++) {
            if (this.getSlot(i, 0) == this.getSlot(i, 1) && this.getSlot(i, 1) == this.getSlot(i, 2)) {
                return this.getSlot(i, 0).piece;
            }
            if (this.getSlot(0, i) == this.getSlot(1, i) && this.getSlot(1, i) == this.getSlot(2, i)) {
                return this.getSlot(0, i).piece;
            }
        }
        return null;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        
        System.out.println(Arrays.deepToString(game.board));
    }
}
