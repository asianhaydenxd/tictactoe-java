import java.util.Scanner;

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

    Slot getSlot(Coord coord) {
        return this.board[coord.getIndex()];
    }

    void setSlot(Coord coord, Piece piece) {
        this.board[coord.getIndex()].piece = piece;
    }

    Piece getWinner() throws Exception {
        if (this.getSlot(new Coord(0, 0)).piece == this.getSlot(new Coord(1, 1)).piece && this.getSlot(new Coord(1, 1)).piece == this.getSlot(new Coord(2, 2)).piece) {
            return this.getSlot(new Coord(0, 0)).piece;
        }
        if (this.getSlot(new Coord(0, 2)).piece == this.getSlot(new Coord(1, 1)).piece && this.getSlot(new Coord(1, 1)).piece == this.getSlot(new Coord(2, 0)).piece) {
            return this.getSlot(new Coord(0, 2)).piece;
        }
        for (int i = 0; i < 3; i++) {
            if (this.getSlot(new Coord(i, 0)).piece == this.getSlot(new Coord(i, 1)).piece && this.getSlot(new Coord(i, 1)).piece == this.getSlot(new Coord(i, 2)).piece) {
                return this.getSlot(new Coord(i, 0)).piece;
            }
            if (this.getSlot(new Coord(0, i)).piece == this.getSlot(new Coord(1, i)).piece && this.getSlot(new Coord(1, i)).piece == this.getSlot(new Coord(2, i)).piece) {
                return this.getSlot(new Coord(0, i)).piece;
            }
        }
        return null;
    }

    String readBoard() throws Exception {
        String acc = "";

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                acc += "[" + this.getSlot(new Coord(row, col)).read() + "]";
            }
            acc += "\n";
        }

        return acc;
    }
}

class Coord {
    int row;
    int col;

    public Coord(int row, int col) throws Exception {
        if (!(0 <= row && row <= 2 && 0 <= col && col <= 2)) throw new Exception("Coordinates are out of range");

        this.row = row;
        this.col = col;
    }

    int getIndex() {
        return this.row * 3 + this.col;
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

        if (!(text.length() == 3 && text.charAt(1) == ' ')) throw new Exception("Input coord string is invalid");
                
        return new Coord(rowCoord, colCoord);
    }

    void close() {
        this.scanner.close();
    }
}

public class App {
    static TicTacToeReader reader = new TicTacToeReader();
    static Game game = new Game();
    static Piece turn = Piece.X;

    static Coord getCoord() throws Exception {
        for (int attempts = 0; attempts < 10; attempts++) {
            try {
                System.out.println("Input coords");
                Coord coord = reader.readCoords();
                if (game.getSlot(coord).piece != null) throw new Exception("Slot is already filled");
                return coord;
            } catch (Exception e) {
                System.out.println("Invalid coords");                
            }
        }

        throw new Exception("Too many attempts");
    }

    static Piece oppositePiece(Piece piece) {
        return piece == Piece.O ? Piece.X : Piece.O;
    }

    public static void main(String[] args) throws Exception {
        while (game.getWinner() == null) {
            System.out.println(game.readBoard());
            game.setSlot(getCoord(), turn);
            turn = oppositePiece(turn);
        }

        System.out.println(game.readBoard());

        switch (game.getWinner()) {
            case X:
                System.out.println("X Wins!");
                break;
            case O:
                System.out.println("O Wins!");
                break;
        }

        reader.close();
    }
}
