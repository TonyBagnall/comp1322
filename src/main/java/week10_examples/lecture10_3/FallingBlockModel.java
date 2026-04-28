package week10_examples.lecture10_3;

import java.util.Random;

/**
 * Model class for the falling-block game.
 *
 * This class stores the game state and movement rules.
 * It deliberately contains no JavaFX code.
 */
public class FallingBlockModel {

    public static final int DEFAULT_ROWS = 16;
    public static final int DEFAULT_COLS = 10;

    private final int rows;
    private final int cols;
    private final Random random = new Random();

    private int blockRow;
    private int blockCol;
    private boolean blockAtBottom;
    private BlockShape currentShape;

    public FallingBlockModel() {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }

    public FallingBlockModel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        resetDrop();
    }

    public void resetDrop() {
        currentShape = randomShape();
        blockRow = 0;
        blockCol = (cols - currentShape.getWidth()) / 2;
        blockAtBottom = false;
    }

    public void moveBlockDown() {
        if (blockAtBottom) {
            return;
        }
        if (canShapeFitAt(blockRow + 1, blockCol)) {
            blockRow++;
        } else {
            blockAtBottom = true;
        }
    }

    public void moveBlockLeft() {
        if (!blockAtBottom && canShapeFitAt(blockRow, blockCol - 1)) {
            blockCol--;
        }
    }

    public void moveBlockRight() {
        if (!blockAtBottom && canShapeFitAt(blockRow, blockCol + 1)) {
            blockCol++;
        }
    }

    private boolean canShapeFitAt(int newRow, int newCol) {
        for (int[] cell : currentShape.getCells()) {
            int row = newRow + cell[0];
            int col = newCol + cell[1];

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                return false;
            }
        }

        return true;
    }

    private BlockShape randomShape() {
        BlockShape[] shapes = BlockShape.getAllShapes();
        int index = random.nextInt(shapes.length);
        return shapes[index];
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

    public BlockShape getCurrentShape() {
        return currentShape;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    /**
     * Logical block shape.
     *
     * Each shape is defined as row/column offsets from the block's anchor
     * position. The anchor is the blockRow/blockCol position stored in the
     * model.
     */

    public static class BlockShape {

        public static final BlockShape SINGLE = new BlockShape(
                "Single",
                new int[][]{
                        {0, 0}
                }
        );

        public static final BlockShape TWO_HORIZONTAL = new BlockShape(
                "Two horizontal",
                new int[][]{
                        {0, 0},
                        {0, 1}
                }
        );

        public static final BlockShape TWO_VERTICAL = new BlockShape(
                "Two vertical",
                new int[][]{
                        {0, 0},
                        {1, 0}
                }
        );

        public static final BlockShape SQUARE = new BlockShape(
                "Square",
                new int[][]{
                        {0, 0},
                        {0, 1},
                        {1, 0},
                        {1, 1}
                }
        );

        public static final BlockShape L_SHAPE = new BlockShape(
                "L shape",
                new int[][]{
                        {0, 0},
                        {1, 0},
                        {2, 0},
                        {2, 1}
                }
        );

        public static final BlockShape T_SHAPE = new BlockShape(
                "T shape",
                new int[][]{
                        {0, 0},
                        {0, 1},
                        {0, 2},
                        {1, 1}
                }
        );

        private static final BlockShape[] ALL_SHAPES = {
                SINGLE,
                TWO_HORIZONTAL,
                TWO_VERTICAL,
                SQUARE,
                L_SHAPE,
                T_SHAPE
        };

        private final String name;
        private final int[][] cells;

        private BlockShape(String name, int[][] cells) {
            this.name = name;
            this.cells = cells;
        }

        public String getName() {
            return name;
        }

        public int[][] getCells() {
            return cells;
        }

        public int getHeight() {
            int maxRow = 0;

            for (int[] cell : cells) {
                maxRow = Math.max(maxRow, cell[0]);
            }

            return maxRow + 1;
        }

        public int getWidth() {
            int maxCol = 0;

            for (int[] cell : cells) {
                maxCol = Math.max(maxCol, cell[1]);
            }

            return maxCol + 1;
        }

        public static BlockShape[] getAllShapes() {
            return ALL_SHAPES;
        }
    }
}