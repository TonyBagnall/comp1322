package week10_examples.lecture10_3;

import javafx.animation.KeyFrame;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameFirstVersion extends Application {

    private static final int COLS = 10;
    private static final int ROWS = 16;
    private static final int CELL_SIZE = 30;

    private static final int WINDOW_WIDTH = 520;
    private static final int WINDOW_HEIGHT = 640;

    private Stage primaryStage;

    // Fields used only while the play scene is active
    private Timeline gameTimeline;
    private Pane boardPane;
    private Label statusLabel;
    private Rectangle fallingBlock;
    private int blockRow;
    private int blockCol;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Tetris Demo");
        primaryStage.setScene(createMenuScene());
        primaryStage.show();
    }

    private Scene createMenuScene() {
        stopGame();

        BorderPane root = new BorderPane();
        root.setTop(createMainMenuBar());

        Label titleLabel = new Label("Tetris");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label infoLabel = new Label(
                "Lecture demo\n\n" +
                        "Play Game switches scene in the same stage.\n" +
                        "Save Game opens a new stage.\n" +
                        "In the play scene, one block drops down a grid."
        );
        infoLabel.setStyle("-fx-font-size: 16px;");
        infoLabel.setWrapText(true);

        VBox centreBox = new VBox(20, titleLabel, infoLabel);
        centreBox.setAlignment(Pos.CENTER);

        root.setCenter(centreBox);
        root.setPadding(new Insets(20));

        return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private Scene createPlayScene() {
        BorderPane root = new BorderPane();
        root.setTop(createPlayMenuBar());

        boardPane = createBoardPane();

        statusLabel = new Label("Block is dropping...");
        statusLabel.setStyle("-fx-font-size: 14px;");

        VBox centreBox = new VBox(12, boardPane, statusLabel);
        centreBox.setAlignment(Pos.CENTER);
        centreBox.setPadding(new Insets(15));

        root.setCenter(centreBox);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

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
                "A small JavaFX example for scenes, stages, menus, timelines, and binding."
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
                "The play scene uses a Timeline to move one block down the grid."
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
        pane.setPrefSize(COLS * CELL_SIZE, ROWS * CELL_SIZE);
        pane.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2;");

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setX(col * CELL_SIZE);
                cell.setY(row * CELL_SIZE);
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.LIGHTGRAY);
                pane.getChildren().add(cell);
            }
        }

        return pane;
    }

    private void startDrop() {
        stopGame();

        blockRow = 0;
        blockCol = 4;

        fallingBlock = new Rectangle(CELL_SIZE, CELL_SIZE);
        fallingBlock.setFill(Color.DODGERBLUE);
        fallingBlock.setStroke(Color.BLACK);

        boardPane.getChildren().add(fallingBlock);
        updateBlockPosition();

        statusLabel.setText("Row: " + blockRow);

        gameTimeline = new Timeline(
                new KeyFrame(Duration.millis(500), e -> moveBlockDown())
        );
        gameTimeline.setCycleCount(Timeline.INDEFINITE);
        gameTimeline.play();
    }

    private void moveBlockDown() {
        if (blockRow < ROWS - 1) {
            blockRow++;
            updateBlockPosition();
            statusLabel.setText("Row: " + blockRow);
        } else {
            gameTimeline.stop();
            statusLabel.setText("Block reached the bottom");
        }
    }

    private void updateBlockPosition() {
        fallingBlock.setX(blockCol * CELL_SIZE);
        fallingBlock.setY(blockRow * CELL_SIZE);
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
     * Nested stage used for Save Game.
     * Keeps the example in one file and gives you a natural stage example.
     */
    private static class SaveGameWindow extends Stage {

        SaveGameWindow() {
            setTitle("Save Game");

            Label heading = new Label("Save Game");
            heading.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            TextField saveNameField = new TextField();
            saveNameField.setPromptText("Enter save name");

            Label previewLabel = new Label();
            previewLabel.textProperty().bind(
                    Bindings.when(saveNameField.textProperty().isEmpty())
                            .then("Save slot: <empty>")
                            .otherwise(Bindings.concat("Save slot: ", saveNameField.textProperty()))
            );

            Button saveButton = new Button("Save");
            saveButton.disableProperty().bind(saveNameField.textProperty().isEmpty());

            Label resultLabel = new Label();

            saveButton.setOnAction(e ->
                    resultLabel.setText("Saved as: " + saveNameField.getText())
            );

            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> close());

            HBox buttonRow = new HBox(10, saveButton, closeButton);
            buttonRow.setAlignment(Pos.CENTER);

            VBox layout = new VBox(15, heading, saveNameField, previewLabel, buttonRow, resultLabel);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(20));

            setScene(new Scene(layout, 340, 240));
        }
    }

    /**
     * Nested stage for simple placeholder windows such as High Scores and Settings.
     */
    private static class MessageWindow extends Stage {

        MessageWindow(String title, String message) {
            setTitle(title);

            Label heading = new Label(title);
            heading.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            Label content = new Label(message);
            content.setWrapText(true);

            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> close());

            VBox layout = new VBox(15, heading, content, closeButton);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(20));

            setScene(new Scene(layout, 320, 200));
        }
    }
}