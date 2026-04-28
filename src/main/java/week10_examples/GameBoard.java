package week10_examples;

public class GameBoard {

    private final int size;
    private final String[][] board;
    private String currentPlayer = "X";

    public GameBoard(int size) {
        this.size = size;
        this.board = new String[size][size];
        resetBoard();
    }

    public void resetBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = "";
            }
        }

        currentPlayer = "X";
    }

    public void switchPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    public boolean makeMove(int row, int col) {
        if (!board[row][col].isEmpty()) {
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    public String checkWinner() {
        int requiredLength = getRequiredLineLength();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String mark = board[row][col];

                if (mark.isEmpty()) {
                    continue;
                }

                if (hasLineFrom(row, col, 0, 1, requiredLength)
                        || hasLineFrom(row, col, 1, 0, requiredLength)
                        || hasLineFrom(row, col, 1, 1, requiredLength)
                        || hasLineFrom(row, col, 1, -1, requiredLength)) {
                    return mark;
                }
            }
        }

        return "";
    }

    private boolean hasLineFrom(int startRow, int startCol, int rowStep, int colStep,
                                int requiredLength) {
        String mark = board[startRow][startCol];

        for (int offset = 1; offset < requiredLength; offset++) {
            int row = startRow + offset * rowStep;
            int col = startCol + offset * colStep;

            if (row < 0 || row >= size || col < 0 || col >= size) {
                return false;
            }

            if (!board[row][col].equals(mark)) {
                return false;
            }
        }

        return true;
    }

    public boolean isDraw() {
        if (!checkWinner().isEmpty()) {
            return false;
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col].isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getRequiredLineLength() {
        if (size > 4) {
            return size - 2;
        }

        return size;
    }

    public String getCell(int row, int col) {
        return board[row][col];
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public int getSize() {
        return size;
    }
}
