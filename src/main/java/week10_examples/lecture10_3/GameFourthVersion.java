package week10_examples.lecture10_3;


import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameFourthVersion extends Application {

    private static final int CELL_SIZE = 30;
    private static final int WINDOW_WIDTH = 540;
    private static final int WINDOW_HEIGHT = 680;

    private Stage primaryStage;

    // JavaFX interface state
    private Timeline gameTimeline;
    private Pane boardPane;
    private Label statusLabel;
    private FallingBlock fallingBlock;

    // Plain Java game logic
    private final FallingBlockGame game = new FallingBlockGame();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tetris Demo - Third Version");
        primaryStage.setScene(createMenuScene());
        primaryStage.show();
    }

    private Scene createMenuScene() {
        stopGame();

        BorderPane root = new BorderPane();
        root.setTop(createMainMenuBar());
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f8fbff, #eaf2fb);");

        Label titleLabel = new Label("Tetris");
        titleLabel.setStyle(
                "-fx-font-size: 30px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1e3a5f;"
        );

        Label infoLabel = new Label(
                "Third version\n\n" +
                        "Play Game switches to the game scene in the same stage.\n" +
                        "Save Game opens a separate stage.\n" +
                        "The falling shape is a small custom component.\n\n" +
                        "The game logic has now been separated from the JavaFX interface."
        );
        infoLabel.setWrapText(true);
        infoLabel.setMaxWidth(360);
        infoLabel.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-text-fill: #2b3d52;" +
                        "-fx-background-color: white;" +
                        "-fx-padding: 18;" +
                        "-fx-border-color: #c8d6e5;" +
                        "-fx-border-width: 1;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 8;"
        );

        VBox centreBox = new VBox(20, titleLabel, infoLabel);
        centreBox.setAlignment(Pos.CENTER);

        root.setCenter(centreBox);
        root.setPadding(new Insets(20));

        return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private Scene createPlayScene() {
        BorderPane root = new BorderPane();
        root.setTop(createPlayMenuBar());
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f8fbff, #eef4fb);");

        boardPane = createBoardPane();

        statusLabel = new Label("Ready");
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

        VBox centreBox = new VBox(14, boardPane, statusLabel);
        centreBox.setAlignment(Pos.CENTER);
        centreBox.setPadding(new Insets(15));

        root.setCenter(centreBox);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.moveBlockLeft();
                updateBlockPosition();
                updateStatusLabel();
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.moveBlockRight();
                updateBlockPosition();
                updateStatusLabel();
            } else if (event.getCode() == KeyCode.DOWN) {
                moveBlockDown();
            }
        });

        startDrop();

        return scene;
    }

    private MenuBar createMainMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");

        MenuItem playItem = new MenuItem("Play Game");
        MenuItem saveItem = new MenuItem("Save Game");
        MenuItem scoresItem = new MenuItem("High Scores");
        MenuItem settingsItem = new MenuItem("Settings");
        MenuItem exitItem = new MenuItem("Exit");

        MenuItem aboutItem = new MenuItem("About");

        playItem.setOnAction(e -> primaryStage.setScene(createPlayScene()));
        saveItem.setOnAction(e -> new SaveGameWindow().show());
        scoresItem.setOnAction(e -> new MessageWindow("High Scores", "Placeholder high scores window.").show());
        settingsItem.setOnAction(e -> new MessageWindow("Settings", "Placeholder settings window.").show());
        exitItem.setOnAction(e -> Platform.exit());

        aboutItem.setOnAction(e -> new MessageWindow(
                "About",
                "This version separates the game state from the JavaFX interface."
        ).show());

        gameMenu.getItems().addAll(
                playItem,
                saveItem,
                new SeparatorMenuItem(),
                scoresItem,
                settingsItem,
                new SeparatorMenuItem(),
                exitItem
        );

        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(gameMenu, helpMenu);
        return menuBar;
    }

    private MenuBar createPlayMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");

        MenuItem saveItem = new MenuItem("Save Game");
        MenuItem restartItem = new MenuItem("Restart Drop");
        MenuItem backItem = new MenuItem("Back to Menu");
        MenuItem scoresItem = new MenuItem("High Scores");
        MenuItem settingsItem = new MenuItem("Settings");
        MenuItem exitItem = new MenuItem("Exit");

        MenuItem aboutItem = new MenuItem("About");

        saveItem.setOnAction(e -> new SaveGameWindow().show());
        restartItem.setOnAction(e -> startDrop());
        backItem.setOnAction(e -> primaryStage.setScene(createMenuScene()));
        scoresItem.setOnAction(e -> new MessageWindow("High Scores", "Placeholder high scores window.").show());
        settingsItem.setOnAction(e -> new MessageWindow("Settings", "Placeholder settings window.").show());
        exitItem.setOnAction(e -> Platform.exit());

        aboutItem.setOnAction(e -> new MessageWindow(
                "About",
                "The play scene uses a Timeline to ask the game to move the block down."
        ).show());

        gameMenu.getItems().addAll(
                saveItem,
                restartItem,
                backItem,
                new SeparatorMenuItem(),
                scoresItem,
                settingsItem,
                new SeparatorMenuItem(),
                exitItem
        );

        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(gameMenu, helpMenu);
        return menuBar;
    }

    private Pane createBoardPane() {
        Pane pane = new Pane();
        pane.setPrefSize(game.getCols() * CELL_SIZE, game.getRows() * CELL_SIZE);
        pane.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #ffffff, #f4f7fb);" +
                        "-fx-border-color: #1f2937;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 4;" +
                        "-fx-border-radius: 4;"
        );

        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setX(col * CELL_SIZE);
                cell.setY(row * CELL_SIZE);
                cell.setFill(Color.color(1, 1, 1, 0.0));
                cell.setStroke(Color.web("#d7dee8"));
                pane.getChildren().add(cell);
            }
        }

        return pane;
    }

    private void startDrop() {
        stopGame();

        game.resetDrop();

        if (fallingBlock != null) {
            boardPane.getChildren().remove(fallingBlock);
        }

        fallingBlock = new FallingBlock(CELL_SIZE);
        boardPane.getChildren().add(fallingBlock);

        updateBlockPosition();
        updateStatusLabel();

        gameTimeline = new Timeline(
                new KeyFrame(Duration.millis(500), e -> moveBlockDown())
        );
        gameTimeline.setCycleCount(Timeline.INDEFINITE);
        gameTimeline.play();
    }

    private void moveBlockDown() {
        game.moveBlockDown();
        updateBlockPosition();
        updateStatusLabel();

        if (game.isBlockAtBottom()) {
            stopGame();
            statusLabel.setText("Block reached the bottom");
        }
    }

    private void updateBlockPosition() {
        fallingBlock.setX(game.getBlockCol() * CELL_SIZE);
        fallingBlock.setY(game.getBlockRow() * CELL_SIZE);
    }

    private void updateStatusLabel() {
        statusLabel.setText(
                "Row: " + game.getBlockRow()
                        + "    Col: " + game.getBlockCol()
        );
    }

    private void stopGame() {
        if (gameTimeline != null) {
            gameTimeline.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Custom component for the falling shape.
     * It is static because it does not need access to the outer GameThirdVersion object.
     */
    private static class FallingBlock extends Rectangle {

        private boolean selected = false;

        FallingBlock(double size) {
            super(size, size);

            setArcWidth(8);
            setArcHeight(8);
            applyNormalStyle();

            setOnMouseClicked(e -> {
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
            pulse.setToX(3);
            pulse.setToY(3);
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

    /**
     * Save window showing simple binding.
     */
    private static class SaveGameWindow extends Stage {

        SaveGameWindow() {
            setTitle("Save Game");

            Label heading = new Label("Save Game");
            heading.setStyle(
                    "-fx-font-size: 20px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: #1e3a5f;"
            );

            TextField saveNameField = new TextField();
            saveNameField.setPromptText("Enter save name");
            saveNameField.setMaxWidth(220);
            saveNameField.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #b8c7d9;" +
                            "-fx-border-width: 1;" +
                            "-fx-padding: 6;"
            );

            Label previewLabel = new Label();
            previewLabel.textProperty().bind(
                    Bindings.when(saveNameField.textProperty().isEmpty())
                            .then("Save slot: <empty>")
                            .otherwise(Bindings.concat("Save slot: ", saveNameField.textProperty()))
            );
            previewLabel.setStyle("-fx-text-fill: #334155; -fx-font-size: 13px;");

            Button saveButton = new Button("Save");
            saveButton.disableProperty().bind(saveNameField.textProperty().isEmpty());
            saveButton.setStyle(
                    "-fx-background-color: #0ea5e9;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-weight: bold;"
            );

            Label resultLabel = new Label();
            resultLabel.setStyle("-fx-text-fill: #166534; -fx-font-size: 13px;");

            saveButton.setOnAction(e -> resultLabel.setText("Saved as: " + saveNameField.getText()));

            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> close());

            HBox buttonRow = new HBox(10, saveButton, closeButton);
            buttonRow.setAlignment(Pos.CENTER);

            VBox layout = new VBox(15, heading, saveNameField, previewLabel, buttonRow, resultLabel);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(20));
            layout.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #ffffff, #f4f8fc);"
            );

            setScene(new Scene(layout, 340, 240));
        }
    }

    /**
     * Simple placeholder window for menu actions.
     */
    private static class MessageWindow extends Stage {

        MessageWindow(String title, String message) {
            setTitle(title);

            Label heading = new Label(title);
            heading.setStyle(
                    "-fx-font-size: 20px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: #1e3a5f;"
            );

            Label content = new Label(message);
            content.setWrapText(true);
            content.setMaxWidth(240);
            content.setStyle("-fx-font-size: 14px; -fx-text-fill: #334155;");

            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> close());

            VBox layout = new VBox(15, heading, content, closeButton);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(20));
            layout.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #f4f8fc);");

            setScene(new Scene(layout, 320, 200));
        }
    }
}