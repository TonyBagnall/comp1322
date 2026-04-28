package week10_examples;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NoughtsAndCrossesApp extends Application {

    private BorderPane root;

    private GameBoard gameBoard;
    private Button[][] buttons;
    private Button continueButton;

    private int boardSize = 3;
    private boolean gameFinished = false;

    private final StringProperty playerXName = new SimpleStringProperty("");
    private final StringProperty playerOName = new SimpleStringProperty("");
    private final StringProperty turnText = new SimpleStringProperty("");
    private final StringProperty messageText = new SimpleStringProperty("");

    private final IntegerProperty xWins = new SimpleIntegerProperty(0);
    private final IntegerProperty oWins = new SimpleIntegerProperty(0);
    private final IntegerProperty draws = new SimpleIntegerProperty(0);

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        showLoadingScreen();

        Scene scene = new Scene(root, 700, 650);

        primaryStage.setTitle("Noughts and Crosses");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLoadingScreen() {
        TextField playerXField = new TextField(playerXName.get());
        TextField playerOField = new TextField(playerOName.get());

        playerXField.setPromptText("Player X name");
        playerOField.setPromptText("Player O name");

        Spinner<Integer> sizeSpinner = new Spinner<>(3, 10, boardSize);
        sizeSpinner.setEditable(true);

        Button startButton = new Button("Start game");
        Button scoreButton = new Button("Show scores");

        startButton.setMaxWidth(Double.MAX_VALUE);
        scoreButton.setMaxWidth(Double.MAX_VALUE);

        BooleanBinding missingNames = Bindings.createBooleanBinding(
                () -> playerXField.getText().trim().isEmpty()
                        || playerOField.getText().trim().isEmpty(),
                playerXField.textProperty(),
                playerOField.textProperty()
        );

        startButton.disableProperty().bind(missingNames);

        startButton.setOnAction(event -> {
            playerXName.set(playerXField.getText().trim());
            playerOName.set(playerOField.getText().trim());
            boardSize = sizeSpinner.getValue();
            startNewRound();
        });

        scoreButton.setOnAction(event -> showScoreBoard());

        Label title = new Label("Noughts and Crosses");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        Label subtitle = new Label("Enter two player names and choose a grid size.");
        subtitle.setStyle("-fx-font-size: 14px;");

        VBox form = new VBox(
                12,
                title,
                subtitle,
                new Label("Grid size"),
                sizeSpinner,
                new Label("Player X"),
                playerXField,
                new Label("Player O"),
                playerOField,
                startButton,
                scoreButton
        );

        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(30));
        form.setMaxWidth(380);
        form.setStyle(
                "-fx-background-color: #f4f4f4;"
                        + "-fx-border-color: #cccccc;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );

        BorderPane wrapper = new BorderPane(form);
        wrapper.setPadding(new Insets(40));

        root.setTop(null);
        root.setCenter(wrapper);
        root.setBottom(null);
    }

    private void startNewRound() {
        gameBoard = new GameBoard(boardSize);
        buttons = new Button[boardSize][boardSize];
        gameFinished = false;
        continueButton = null;
        messageText.set("");

        updateTurnText();
        showGameScreen();
    }

    private void showGameScreen() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(20));

        int buttonSize = calculateButtonSize(boardSize);

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Button button = new Button();

                button.setPrefSize(buttonSize, buttonSize);
                button.setMinSize(buttonSize, buttonSize);
                button.setMaxSize(buttonSize, buttonSize);
                button.setFocusTraversable(false);
                button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                setEmptyButtonStyle(button);

                button.setOnAction(new ButtonClickHandler(row, col));

                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        Label playersLabel = new Label();
        playersLabel.textProperty().bind(Bindings.concat(
                playerXName, " is X    |    ", playerOName, " is O"
        ));
        playersLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label turnLabel = new Label();
        turnLabel.textProperty().bind(turnText);
        turnLabel.setStyle("-fx-font-size: 18px;");

        Label ruleLabel = new Label(
                "Win condition: " + gameBoard.getRequiredLineLength()
                        + " in a row on a " + boardSize + "x" + boardSize + " board."
        );
        ruleLabel.setStyle("-fx-font-size: 14px;");

        Button resetButton = new Button("Reset round");
        Button scoreButton = new Button("Show scores");
        Button setupButton = new Button("Change players / size");

        resetButton.setOnAction(event -> startNewRound());
        scoreButton.setOnAction(event -> showScoreBoard());
        setupButton.setOnAction(event -> showLoadingScreen());

        HBox controls = new HBox(10, resetButton, scoreButton, setupButton);
        controls.setAlignment(Pos.CENTER);

        VBox top = new VBox(8, playersLabel, turnLabel, ruleLabel, controls);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(15));

        Label messageLabel = new Label();
        messageLabel.textProperty().bind(messageText);
        messageLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        continueButton = new Button("Continue");
        continueButton.setVisible(false);
        continueButton.setManaged(false);
        continueButton.setOnAction(event -> showFinishScreen(messageText.get()));

        VBox bottom = new VBox(10, messageLabel, continueButton);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10));

        root.setTop(top);
        root.setCenter(grid);
        root.setBottom(bottom);
    }

    private class ButtonClickHandler implements EventHandler<ActionEvent> {

        private final int row;
        private final int col;

        ButtonClickHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void handle(ActionEvent event) {
            if (gameFinished) {
                return;
            }

            String playerThatMoved = gameBoard.getCurrentPlayer();

            boolean moveMade = gameBoard.makeMove(row, col);

            if (!moveMade) {
                messageText.set("That square is already taken.");
                return;
            }

            showMoveOnButton(buttons[row][col], playerThatMoved);

            String winner = gameBoard.checkWinner();

            if (!winner.isEmpty()) {
                finishWithWinner(winner);
            } else if (gameBoard.isDraw()) {
                finishWithDraw();
            } else {
                gameBoard.switchPlayer();
                updateTurnText();
                messageText.set("");
            }
        }
    }

    private void showMoveOnButton(Button button, String mark) {
        Label label = new Label(mark);

        int fontSize = Math.max(36, calculateButtonSize(boardSize) / 2);
        label.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));

        if (mark.equals("X")) {
            label.setTextFill(Color.DODGERBLUE);
        } else {
            label.setTextFill(Color.FIREBRICK);
        }

        button.setText("");
        button.setGraphic(label);
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        button.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #999999;"
                        + "-fx-border-width: 1;"
        );
    }

    private void setEmptyButtonStyle(Button button) {
        button.setText("");
        button.setGraphic(null);

        button.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #999999;"
                        + "-fx-border-width: 1;"
        );
    }

    private void finishWithWinner(String winner) {
        gameFinished = true;

        String result;

        if (winner.equals("X")) {
            xWins.set(xWins.get() + 1);
            result = playerXName.get() + " wins as X.";
        } else {
            oWins.set(oWins.get() + 1);
            result = playerOName.get() + " wins as O.";
        }

        messageText.set(result);
        turnText.set("Game finished");
        showContinueButton();
    }

    private void finishWithDraw() {
        gameFinished = true;

        draws.set(draws.get() + 1);

        String result = "Draw. No squares left.";
        messageText.set(result);
        turnText.set("Game finished");
        showContinueButton();
    }

    private void showContinueButton() {
        if (continueButton != null) {
            continueButton.setVisible(true);
            continueButton.setManaged(true);
        }
    }

    private void showFinishScreen(String result) {
        Label title = new Label("Game finished");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        Label resultLabel = new Label(result);
        resultLabel.setStyle("-fx-font-size: 22px;");

        Label scoreSummary = new Label();
        scoreSummary.textProperty().bind(Bindings.concat(
                "Scores: X ", xWins,
                "    O ", oWins,
                "    Draws ", draws
        ));
        scoreSummary.setStyle("-fx-font-size: 16px;");

        Button playAgainButton = new Button("Play again with same players");
        Button setupButton = new Button("Return to setup screen");
        Button scoreButton = new Button("Show scores");

        playAgainButton.setMaxWidth(Double.MAX_VALUE);
        setupButton.setMaxWidth(Double.MAX_VALUE);
        scoreButton.setMaxWidth(Double.MAX_VALUE);

        playAgainButton.setOnAction(event -> startNewRound());
        setupButton.setOnAction(event -> showLoadingScreen());
        scoreButton.setOnAction(event -> showScoreBoard());

        VBox box = new VBox(
                15,
                title,
                resultLabel,
                scoreSummary,
                playAgainButton,
                setupButton,
                scoreButton
        );

        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30));
        box.setMaxWidth(420);
        box.setStyle(
                "-fx-background-color: #f4f4f4;"
                        + "-fx-border-color: #cccccc;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );

        BorderPane wrapper = new BorderPane(box);
        wrapper.setPadding(new Insets(40));

        root.setTop(null);
        root.setCenter(wrapper);
        root.setBottom(null);
    }

    private void showScoreBoard() {
        Stage scoreStage = new Stage();
        scoreStage.setTitle("Score board");

        Label title = new Label("Score board");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label xScoreLabel = new Label();
        Label oScoreLabel = new Label();
        Label drawScoreLabel = new Label();

        String xDisplayName = playerXName.get().isEmpty() ? "Player X" : playerXName.get();
        String oDisplayName = playerOName.get().isEmpty() ? "Player O" : playerOName.get();

        xScoreLabel.textProperty().bind(Bindings.concat(
                xDisplayName,
                " (X) wins: ",
                xWins
        ));

        oScoreLabel.textProperty().bind(Bindings.concat(
                oDisplayName,
                " (O) wins: ",
                oWins
        ));

        drawScoreLabel.textProperty().bind(Bindings.concat("Draws: ", draws));

        xScoreLabel.setStyle("-fx-font-size: 18px;");
        oScoreLabel.setStyle("-fx-font-size: 18px;");
        drawScoreLabel.setStyle("-fx-font-size: 18px;");

        VBox box = new VBox(12, title, xScoreLabel, oScoreLabel, drawScoreLabel);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(25));

        Scene scene = new Scene(box, 340, 230);
        scoreStage.setScene(scene);
        scoreStage.show();
    }

    private void updateTurnText() {
        if (gameBoard.getCurrentPlayer().equals("X")) {
            turnText.set("Turn: " + playerXName.get() + " (X)");
        } else {
            turnText.set("Turn: " + playerOName.get() + " (O)");
        }
    }

    private int calculateButtonSize(int size) {
        if (size <= 3) {
            return 110;
        }

        if (size <= 5) {
            return 80;
        }

        if (size <= 7) {
            return 60;
        }

        return 48;
    }

    public static void main(String[] args) {
        launch(args);
    }
}