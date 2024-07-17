import models.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToe {
    Board gameboard;
    Deque<Player> players;

    public TicTacToe() {
        initializeGame();
    }

    private void initializeGame() {
        PieceX crossPiece = new PieceX(PieceType.X);
        Player firstPlayer = new Player("First Player", crossPiece);

        PieceO noughtPiece = new PieceO(PieceType.O);
        Player secondPlayer = new Player("Second Player", noughtPiece);

        players = new LinkedList<>();
        players.add(firstPlayer);
        players.add(secondPlayer);

        gameboard = new Board(3);
    }

    public void startGame() {
        boolean isWinnerFound = false;
        while (!isWinnerFound) {
            //take out the player whose turn is awaited
            Player playerTurn = players.removeFirst();

            //display state of board
            gameboard.displayBoard();
            System.out.println(playerTurn.getName() + " enter the position where you want to move the mark");

            //taking input from the player whose turn is
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] values = input.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputCol = Integer.parseInt(values[1]);


            //add piece to board
            boolean isPieceSuccesfullyPlaced = gameboard.addPieceToBoard(inputRow, inputCol, playerTurn.getPlayingPiece());
            if (!isPieceSuccesfullyPlaced) {
                players.addFirst(playerTurn);
                System.out.println("Player : " + playerTurn.getName() + " should move again");
                continue;
            }

            isWinnerFound = checkIfPlayerWin(inputRow, inputCol, playerTurn.getPlayingPiece());
            if (isWinnerFound) {
                gameboard.displayBoard();
                System.out.println("Player " + playerTurn.getName() + " won the game");
                return;
            }
            players.add(playerTurn);

        }
        System.out.println("Match Tied");
    }

    private boolean checkIfPlayerWin(int inputRow, int inputCol, PlayingPiece playingPiece) {
        boolean isRowMatch = true;
        boolean isColMatch = true;
        boolean isDiagonalMatch = true;
        boolean isAntiDiagonalMatch = true;

        for(int col = 0; col < gameboard.board.length ; col++) {
            if(gameboard.board[inputRow][col] == null || gameboard.board[inputRow][col].getPieceType() != playingPiece.getPieceType()) {
                isRowMatch = false;
            }
        }

        for(int row = 0; row < gameboard.board.length; row++) {
            if(gameboard.board[row][inputCol] == null || gameboard.board[row][inputCol].getPieceType() != playingPiece.getPieceType()) {
                isColMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i < gameboard.board.length;i++,j++) {
            if (gameboard.board[i][j] == null || gameboard.board[i][j].getPieceType() != playingPiece.getPieceType()) {
                isDiagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameboard.board.length-1; i < gameboard.board.length; i++,j--) {
            if (gameboard.board[i][j] == null || gameboard.board[i][j].getPieceType() != playingPiece.getPieceType()) {
                isAntiDiagonalMatch = false;
            }
        }

        return isRowMatch || isColMatch || isDiagonalMatch || isAntiDiagonalMatch;

    }

}
