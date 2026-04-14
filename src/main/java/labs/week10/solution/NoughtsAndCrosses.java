package labs.week10.solution;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoughtsAndCrosses extends Application {

    private GameBoard gameBoard;
    private Button[][] buttons;
    private Stage mainStage;
    private StringProperty playerXName = new SimpleStringProperty("Player X");
    private StringProperty playerOName = new SimpleStringProperty("Player O");
    private int gridSize = 3;

    @Override
    public void start(Stage primaryStage) {
        this.mainStage = primaryStage;
        showStartScreen();
    }

    private void showStartScreen() {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label title = new Label("Noughts and Crosses");

        TextField xNameField = new TextField(playerXName.get()); // pre-fill with current names
        xNameField.setPromptText("Enter X player name");

        TextField oNameField = new TextField(playerOName.get());
        oNameField.setPromptText("Enter O player name");

        Spinner<Integer> sizeSpinner = new Spinner<>(3, 10, gridSize); // pre-fill with last grid size
        Button startButton = new Button("Start Game");
        startButton.setDisable(xNameField.getText().isEmpty() || oNameField.getText().isEmpty());

        xNameField.textProperty().addListener((obs, oldText, newText) ->
                startButton.setDisable(xNameField.getText().isEmpty() || oNameField.getText().isEmpty())
        );
        oNameField.textProperty().addListener((obs, oldText, newText) ->
                startButton.setDisable(xNameField.getText().isEmpty() || oNameField.getText().isEmpty())
        );

        startButton.setOnAction(e -> {
            playerXName.set(xNameField.getText());
            playerOName.set(oNameField.getText());
            gridSize = sizeSpinner.getValue();
            showGameScreen();
        });

        layout.getChildren().addAll(title, xNameField, oNameField, new Label("Grid Size:"), sizeSpinner, startButton);

        Scene scene = new Scene(layout, 300, 300);
        mainStage.setScene(scene);
        mainStage.show();
    }
    private void showGameScreen() {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Label turnLabel = new Label();
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        gameBoard = new GameBoard(gridSize);
        buttons = new Button[gridSize][gridSize];

        turnLabel.textProperty().bind(
                Bindings.createStringBinding(
                        () -> gameBoard.getCurrentPlayer().equals("X")
                                ? playerXName.get() + "'s turn"
                                : playerOName.get() + "'s turn",
                        gameBoard.currentPlayerProperty(), playerXName, playerOName
                )
        );
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Button button = new Button();
                button.setPrefSize(80, 80);
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e -> handleMove(button, finalRow, finalCol));
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        root.getChildren().addAll(turnLabel, grid);

        Scene scene = new Scene(root, gridSize * 100, gridSize * 100 + 50);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void handleMove(Button button, int row, int col) {
        if (gameBoard.makeMove(row, col)) {
            button.setText(gameBoard.getCurrentPlayer());
            String winner = gameBoard.checkWinner();
            if (!winner.isEmpty()) {
                showEndScreen(winner);
            } else {
                gameBoard.switchPlayer();
            }
        }
    }

    private void showEndScreen(String winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        String winnerName = winner.equals("X") ? playerXName.get() : playerOName.get();
        alert.setContentText("Winner: " + winnerName + "!");
        alert.showAndWait();
        showStartScreen(); // Restart the game
    }

    public static void main(String[] args) {
        launch(args);
    }
}