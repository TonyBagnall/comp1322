package labs.week10.solution;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameBoard {

    private String[][] board;
    private int size;
    private StringProperty currentPlayer;

    public GameBoard(int size) {
        this.size = size;
        board = new String[size][size];
        currentPlayer = new SimpleStringProperty("X");
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col].isEmpty()) {
            board[row][col] = currentPlayer.get();
            return true;
        }
        return false;
    }

    public String checkWinner() {
        // Check rows
        for (int i = 0; i < size; i++) {
            if (!board[i][0].isEmpty() && allEqual(board[i]))
                return board[i][0];
        }

        // Check columns
        for (int col = 0; col < size; col++) {
            boolean allEqual = true;
            for (int row = 1; row < size; row++) {
                if (!board[row][col].equals(board[0][col])) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual && !board[0][col].isEmpty())
                return board[0][col];
        }

        // Check diagonals
        boolean diag1 = true;
        for (int i = 1; i < size; i++) {
            if (!board[i][i].equals(board[0][0])) {
                diag1 = false;
                break;
            }
        }
        if (diag1 && !board[0][0].isEmpty())
            return board[0][0];

        boolean diag2 = true;
        for (int i = 1; i < size; i++) {
            if (!board[i][size - 1 - i].equals(board[0][size - 1])) {
                diag2 = false;
                break;
            }
        }
        if (diag2 && !board[0][size - 1].isEmpty())
            return board[0][size - 1];

        return ""; // No winner
    }

    public void switchPlayer() {
        currentPlayer.set(currentPlayer.get().equals("X") ? "O" : "X");
    }

    public StringProperty currentPlayerProperty() {
        return currentPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer.get();
    }

    public int getSize() {
        return size;
    }

    private boolean allEqual(String[] array) {
        for (String cell : array) {
            if (!cell.equals(array[0]))
                return false;
        }
        return true;
    }
}