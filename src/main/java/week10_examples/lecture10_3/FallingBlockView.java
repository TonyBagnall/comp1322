package week10_examples.lecture10_3;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * View for the falling-block MVC example.
 *
 * The view owns the JavaFX nodes and knows how to draw the current model state.
 * It does not decide how the block moves.
 */
public class FallingBlockView {

    private static final int CELL_SIZE = 30;

    private final BorderPane root = new BorderPane();
    private final Pane boardPane = new Pane();
    private final Label statusLabel = new Label("Ready");

    private final List<FallingBlockCell> currentBlockCells = new ArrayList<>();

    public FallingBlockView(FallingBlockModel model) {
        createBoard(model);
        createLayout();
    }

    private void createBoard(FallingBlockModel model) {
        boardPane.setPrefSize(
                model.getCols() * CELL_SIZE,
                model.getRows() * CELL_SIZE
        );
        boardPane.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        boardPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        boardPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffffff, #f4f7fb);" +
                        "-fx-border-color: #1f2937;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 4;" +
                        "-fx-border-radius: 4;"
        );

        for (int row = 0; row < model.getRows(); row++) {
            for (int col = 0; col < model.getCols(); col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setX(col * CELL_SIZE);
                cell.setY(row * CELL_SIZE);
                cell.setFill(Color.color(1, 1, 1, 0.0));
                cell.setStroke(Color.web("#d7dee8"));

                boardPane.getChildren().add(cell);
            }
        }
    }

    private void createLayout() {
        Label titleLabel = new Label("Falling Block MVC");
        titleLabel.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1e3a5f;"
        );

        Label instructionLabel = new Label(
                "Left/Right: move    Down: drop faster    Space: pause    R: restart"
        );
        instructionLabel.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #334155;"
        );

        statusLabel.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1f3b5b;" +
                        "-fx-background-color: white;" +
                        "-fx-padding: 8 12 8 12;" +
                        "-fx-border-color: #c8d6e5;" +
                        "-fx-border-width: 1;" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-radius: 6;"
        );

        VBox centreBox = new VBox(14, titleLabel, instructionLabel, boardPane, statusLabel);
        centreBox.setAlignment(Pos.CENTER);
        centreBox.setPadding(new Insets(15));

        root.setCenter(centreBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f8fbff, #eef4fb);");

        root.setFocusTraversable(true);
    }

    public void render(FallingBlockModel model) {
        removeCurrentBlock();
        drawCurrentBlock(model);
        updateStatus(model);
    }

    private void removeCurrentBlock() {
        boardPane.getChildren().removeAll(currentBlockCells);
        currentBlockCells.clear();
    }

    private void drawCurrentBlock(FallingBlockModel model) {
        for (int[] cell : model.getCurrentShape().getCells()) {
            int row = model.getBlockRow() + cell[0];
            int col = model.getBlockCol() + cell[1];

            FallingBlockCell blockCell = new FallingBlockCell(CELL_SIZE);
            blockCell.setX(col * CELL_SIZE);
            blockCell.setY(row * CELL_SIZE);

            currentBlockCells.add(blockCell);
            boardPane.getChildren().add(blockCell);
        }
    }

    private void updateStatus(FallingBlockModel model) {
        if (model.isBlockAtBottom()) {
            statusLabel.setText("Block reached the bottom");
        } else {
            statusLabel.setText(
                    "Shape: " + formatShapeName(model.getCurrentShape())
                            + "    Row: " + model.getBlockRow()
                            + "    Col: " + model.getBlockCol()
            );
        }
    }

    private String formatShapeName(FallingBlockModel.BlockShape shape) {
        String name = shape.getName().toLowerCase().replace("_", " ");
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public Parent getRoot() {
        return root;
    }

    public void requestFocus() {
        root.requestFocus();
    }

    /**
     * Custom JavaFX component for one visible square of the falling shape.
     *
     * This belongs in the view because it is a visual component.
     */
    private static class FallingBlockCell extends Rectangle {

        private boolean selected = false;

        FallingBlockCell(double size) {
            super(size, size);

            setArcWidth(8);
            setArcHeight(8);
            applyNormalStyle();

            setOnMouseClicked(event -> {
                selected = !selected;

                if (selected) {
                    applySelectedStyle();
                } else {
                    applyNormalStyle();
                }

                playClickAnimation();
            });
        }

        private void playClickAnimation() {
            ScaleTransition pulse = new ScaleTransition(Duration.millis(150), this);
            pulse.setFromX(1.0);
            pulse.setFromY(1.0);
            pulse.setToX(1.25);
            pulse.setToY(1.25);
            pulse.setAutoReverse(true);
            pulse.setCycleCount(2);
            pulse.play();
        }

        private void applyNormalStyle() {
            setStyle(
                    "-fx-fill: linear-gradient(to bottom, #7dd3fc, #0284c7);" +
                            "-fx-stroke: #0f3d5e;" +
                            "-fx-stroke-width: 2;"
            );
        }

        private void applySelectedStyle() {
            setStyle(
                    "-fx-fill: linear-gradient(to bottom, #f9a8d4, #db2777);" +
                            "-fx-stroke: #831843;" +
                            "-fx-stroke-width: 2;"
            );
        }
    }
}