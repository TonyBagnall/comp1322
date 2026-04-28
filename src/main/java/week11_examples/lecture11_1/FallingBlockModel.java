package week11_examples.lecture11_1;

public class FallingBlockModel {

    /**
     * Logical block shapes for the falling-block game.
     *
     * Each shape is defined as row/column offsets from the block's anchor position.
     * The anchor position is stored in the model as blockRow and blockCol.
     *
     * This enum contains no JavaFX code.
     */
    public enum BlockShape {

        SINGLE("Single", new int[][]{
                {0, 0}
        }),

        TWO_HORIZONTAL("Two horizontal", new int[][]{
                {0, 0},
                {0, 1}
        }),

        TWO_VERTICAL("Two vertical", new int[][]{
                {0, 0},
                {1, 0}
        }),

        SQUARE("Square", new int[][]{
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        }),

        L_SHAPE("L shape", new int[][]{
                {0, 0},
                {1, 0},
                {2, 0},
                {2, 1}
        }),

        T_SHAPE("T shape", new int[][]{
                {0, 0},
                {0, 1},
                {0, 2},
                {1, 1}
        });

        private final String name;
        private final int[][] cells;

        BlockShape(String name, int[][] cells) {
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
    }

}
