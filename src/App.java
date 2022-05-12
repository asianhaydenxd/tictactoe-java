enum Piece {
    X, O
}

class Slot {
    Piece piece;

    public Slot(Piece newPiece) {
        piece = newPiece;
    }

    String read() {
        if (this.piece == Piece.X) return "X";
        if (this.piece == Piece.O) return "O";
        return " ";
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

    String readBoard() {
        String acc = "";

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                acc += "[" + this.getSlot(row, col).read() + "]";
            }
            acc += "\n";
        }

        return acc;
    }
}

class Coord {
    int row;
    int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    String asString() {
        return "(" + Integer.toString(this.row) + ", " + Integer.toString(this.col) + ")";
    }
}

class TicTacToeReader {
    Scanner scanner = new Scanner(System.in);

    Coord readCoords() throws Exception {
        String text = scanner.nextLine();
        int rowCoord = Character.getNumericValue(text.charAt(0));
        int colCoord = Character.getNumericValue(text.charAt(2));

        if (!(text.length() == 3
                && text.charAt(1) == ' '
                && 0 <= rowCoord && rowCoord <= 2
                && 0 <= colCoord && colCoord <= 2)) throw new Exception("Input coord string is invalid");
                
        return new Coord(rowCoord, colCoord);
    }

    void close() {
        this.scanner.close();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.setSlot(0, 1, Piece.X);
        System.out.println(game.readBoard());
    }
}
