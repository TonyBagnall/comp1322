package week10_examples.lecture10_3;

/**
 * Game logic for the falling-block demo.
 *
 * This class deliberately contains no JavaFX code.
 * It knows where the block is and what moves are allowed,
 * but it does not know how the block is displayed.
 */
public class FallingBlockGame {

    public static final int DEFAULT_ROWS = 16;
    public static final int DEFAULT_COLS = 10;

    private final int rows;
    private final int cols;

    private int blockRow;
    private int blockCol;
    private boolean blockAtBottom;


    public FallingBlockGame() {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }
    public FallingBlockGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        resetDrop();
    }

    public void resetDrop() {
        blockRow = 0;
        blockCol = cols / 2;
        blockAtBottom = false;
    }

    public void moveBlockDown() {
        if (blockAtBottom) {
            return;
        }

        if (blockRow < rows - 1) {
            blockRow++;
        }

        if (blockRow == rows - 1) {
            blockAtBottom = true;
        }
    }

    public void moveBlockLeft() {
        if (!blockAtBottom && blockCol > 0) {
            blockCol--;
        }
    }

    public void moveBlockRight() {
        if (!blockAtBottom && blockCol < cols - 1) {
            blockCol++;
        }
    }

    public int getBlockRow() {
        return blockRow;
    }

    public int getBlockCol() {
        return blockCol;
    }

    public boolean isBlockAtBottom() {
        return blockAtBottom;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}