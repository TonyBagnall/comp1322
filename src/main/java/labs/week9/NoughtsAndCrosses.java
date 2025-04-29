package labs.week9;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoughtsAndCrosses extends Application {

    private Button[][] buttons = new Button[3][3];
    private boolean xTurn = true;
    private Label messageLabel = new Label("X's Turn");
    private Label scoreLabel = new Label("Scores: X = 0 | O = 0 | Draws = 0");

    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        VBox topBox = new VBox();

        Button resetButton = new Button("Reset Game");
        resetButton.setOnAction(e -> resetGame());

        topBox.getChildren().addAll(resetButton, scoreLabel);
        root.setTop(topBox);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button("");
                btn.setPrefSize(100, 100);
                btn.setStyle("-fx-font-size: 24px;");
                buttons[row][col] = btn;
                btn.setOnAction(new ButtonClickHandler(row, col));
                grid.add(btn, col, row);
            }
        }

        root.setCenter(grid);
        root.setBottom(messageLabel);
        BorderPane.setMargin(messageLabel, new javafx.geometry.Insets(10));

        Scene scene = new Scene(root, 350, 400);
        primaryStage.setTitle("Noughts and Crosses");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ButtonClickHandler implements javafx.event.EventHandler<javafx.event.ActionEvent> {
        private int row, col;

        public ButtonClickHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void handle(javafx.event.ActionEvent event) {
            Button clicked = buttons[row][col];
            if (!clicked.getText().isEmpty()) return;

            clicked.setText(xTurn ? "X" : "O");
            if (checkWinner()) {
                if (xTurn) {
                    xWins++;
                    messageLabel.setText("X wins!");
                } else {
                    oWins++;
                    messageLabel.setText("O wins!");
                }
                updateScores();
                disableAllButtons();
            } else if (isBoardFull()) {
                draws++;
                messageLabel.setText("It's a draw!");
                updateScores();
            } else {
                xTurn = !xTurn;
                messageLabel.setText((xTurn ? "X" : "O") + "'s Turn");
            }
        }
    }

    private boolean checkWinner() {
        String playerSymbol = xTurn ? "X" : "O";

        for (int i = 0; i < 3; i++) {
            // Check rows
            if (buttons[i][0].getText().equals(playerSymbol) &&
                    buttons[i][1].getText().equals(playerSymbol) &&
                    buttons[i][2].getText().equals(playerSymbol)) {
                return true;
            }

            // Check columns
            if (buttons[0][i].getText().equals(playerSymbol) &&
                    buttons[1][i].getText().equals(playerSymbol) &&
                    buttons[2][i].getText().equals(playerSymbol)) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(playerSymbol) &&
                buttons[1][1].getText().equals(playerSymbol) &&
                buttons[2][2].getText().equals(playerSymbol)) {
            return true;
        }

        if (buttons[0][2].getText().equals(playerSymbol) &&
                buttons[1][1].getText().equals(playerSymbol) &&
                buttons[2][0].getText().equals(playerSymbol)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (Button[] row : buttons) {
            for (Button btn : row) {
                if (btn.getText().isEmpty()) return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (Button[] row : buttons) {
            for (Button btn : row) {
                btn.setText("");
                btn.setDisable(false);
            }
        }
        messageLabel.setText("X's Turn");
        xTurn = true;
    }

    private void disableAllButtons() {
        for (Button[] row : buttons) {
            for (Button btn : row) {
                btn.setDisable(true);
            }
        }
    }

    private void updateScores() {
        scoreLabel.setText(String.format("Scores: X = %d | O = %d | Draws = %d", xWins, oWins, draws));
    }
    public static void main(String[] args) {
        launch(args);
    }
}