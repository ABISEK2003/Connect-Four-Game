import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private char[][] board;
    private char currentPlayer;

    public ConnectFour() {
        board = new char[ROWS][COLUMNS];
        currentPlayer = 'X'; // Player X starts the game
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = '-';
            }
        }
    }

    private void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    private boolean isColumnFull(int col) {
        return board[0][col] != '-';
    }

    private boolean isValidColumn(int col) {
        return col >= 0 && col < COLUMNS;
    }

    private boolean dropPiece(int col) {
        if (!isValidColumn(col) || isColumnFull(col)) {
            return false;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    private boolean checkWin() {
        // Check rows
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col+1] == currentPlayer &&
                    board[row][col+2] == currentPlayer &&
                    board[row][col+3] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check columns
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row+1][col] == currentPlayer &&
                    board[row+2][col] == currentPlayer &&
                    board[row+3][col] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row+1][col+1] == currentPlayer &&
                    board[row+2][col+2] == currentPlayer &&
                    board[row+3][col+3] == currentPlayer) {
                    return true;
                }
                if (board[row][col+3] == currentPlayer &&
                    board[row+1][col+2] == currentPlayer &&
                    board[row+2][col+1] == currentPlayer &&
                    board[row+3][col] == currentPlayer) {
                    return true;
                }
            }
        }

        return false;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter column (0-6): ");
            int col = scanner.nextInt();

            if (dropPiece(col)) {
                if (checkWin()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }

    private boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (!isColumnFull(col)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.play();
    }
}