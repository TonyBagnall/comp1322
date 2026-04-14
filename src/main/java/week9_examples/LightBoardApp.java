package week9_examples;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LightBoardApp extends Application {
    private static final int GRID_SIZE = 4;
    private static final int BUTTON_SIZE = 80;
    private static final int GAP = 10;
    private static final int PADDING = 15;

    // Fields: needed in more than one method
    private Button[][] buttons = new Button[GRID_SIZE][GRID_SIZE];
    private boolean[][] lightsOn = new boolean[GRID_SIZE][GRID_SIZE];
    private Label statusLabel = new Label("Lights on: 0    Clicks: 0");
    private Label titleLabel = new Label("Light Board");

    private int clickCount = 0;
    private int lightsOnCount = 0;
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(GAP);
        grid.setVgap(GAP);
        grid.setPadding(new Insets(PADDING));

        // Build the grid using loops
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = new Button("OFF");
                button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);

                buttons[row][col] = button;
                lightsOn[row][col] = false;

                button.setOnAction(new ButtonClickHandler(row, col));

                grid.add(button, col, row);
            }
        }

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> resetBoard());

        HBox topBar = new HBox(15);
        topBar.setPadding(new Insets(PADDING));
        topBar.getChildren().addAll(titleLabel, resetButton);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(grid);
        statusLabel.setStyle("-fx-font-size: 22px;");
        root.setBottom(statusLabel);
        BorderPane.setMargin(statusLabel, new Insets(PADDING));

        double width = (GRID_SIZE * BUTTON_SIZE) + ((GRID_SIZE - 1) * GAP) + (2 * PADDING) + 50;
        double height = (GRID_SIZE * BUTTON_SIZE) + ((GRID_SIZE - 1) * GAP) + (3 * PADDING) + 100;

        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("JavaFX Light Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Nested class: one handler object per button
    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        private final int row;
        private final int col;

        public ButtonClickHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void handle(ActionEvent event) {
            clickCount++;
            lightsOn[row][col] = !lightsOn[row][col];
            if (lightsOn[row][col]) {
                buttons[row][col].setText("ON");
                lightsOnCount++;
            } else {
                buttons[row][col].setText("OFF");
                lightsOnCount--;
            }
            statusLabel.setText("Lights on: " + lightsOnCount + "    Clicks: " + clickCount);
        }
    }

    private void updateStatus() {
        int lightsCount = countLightsOn();
    }

    private int countLightsOn() {
        int count = 0;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (lightsOn[row][col]) {
                    count++;
                }
            }
        }

        return count;
    }

    private void resetBoard() {
        clickCount = 0;
        lightsOnCount = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                lightsOn[row][col] = false;
                buttons[row][col].setText("OFF");
            }
        }

        updateStatus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}