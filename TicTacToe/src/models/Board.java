package models;

public class Board {
    public PlayingPiece board[][];

    public Board(int size) {
        this.board = new PlayingPiece[size][size];
    }

    public PlayingPiece getPlayingPiece(int row, int col) {
        return board[row][col];
    }

    public boolean isPositionEmpty(int row, int col) {
        if(board[row][col] == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addPieceToBoard(int row, int col, PlayingPiece piece) {
        if(row > board.length || col > board.length || row < 0 || col < 0) {
            return false;
        }

        if(board[row][col] == null) {
            board[row][col] = piece;
            return true;
        } else {
            return false;
        }
    }

    public void displayBoard() {
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] != null) {
                    System.out.print(board[i][j].getPieceType() + "  ");
                } else {
                    System.out.print("   ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
