import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static int moveCnt = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player = "X";
        int row, col;

        clearBoard();
        display();

        while (true) {
            System.out.println("Enter Move for " + player);
            row = SafeInput.getRangedInt(in, "Enter Row", 1, 3);
            col = SafeInput.getRangedInt(in, "Enter Col", 1, 3);
            row--;
            col--;

            if (isValidMove(row, col)) {
                board[row][col] = player;
                moveCnt++;
                display();

                if (isWin(player)) {
                    System.out.println(player + " wins!");
                    break;
                } else if (moveCnt == 9) {
                    System.out.println("It's a draw!");
                    break;
                }

                player = player.equals("X") ? "O" : "X";
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
        moveCnt = 0;
    }

    private static void display() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col]);
                if (col < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < 2) {
                System.out.println("-----");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }
}
