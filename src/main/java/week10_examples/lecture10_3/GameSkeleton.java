package week10_examples.lecture10_3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameSkeleton extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Scene mainMenuScene = createMainMenuScene();

        primaryStage.setTitle("Game Skeleton");
        primaryStage.setScene(mainMenuScene);
        primaryStage.setWidth(700);
        primaryStage.setHeight(500);
        primaryStage.show();
    }

    private Scene createMainMenuScene() {
        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();

        Label titleLabel = new Label("Game");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label infoLabel = new Label(
                "Menu screen for a simple game application.\n" +
                        "Use the menu above to play, save, or open placeholder windows."
        );
        infoLabel.setStyle("-fx-font-size: 16px;");

        VBox centreBox = new VBox(20, titleLabel, infoLabel);
        centreBox.setAlignment(Pos.CENTER);

        root.setTop(menuBar);
        root.setCenter(centreBox);

        return new Scene(root);
    }

    private Scene createGameScene() {
        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();

        Label gameLabel = new Label("Game screen");
        gameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label placeholderLabel = new Label(
                "Game board would go here.\n\n" +
                        "This scene replaces the menu scene in the same stage."
        );
        placeholderLabel.setStyle("-fx-font-size: 16px;");

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene()));

        VBox centreBox = new VBox(20, gameLabel, placeholderLabel, backButton);
        centreBox.setAlignment(Pos.CENTER);

        root.setTop(menuBar);
        root.setCenter(centreBox);

        return new Scene(root);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");

        MenuItem playItem = new MenuItem("Play Game");
        MenuItem saveItem = new MenuItem("Save Game");
        MenuItem highScoresItem = new MenuItem("High Scores");
        MenuItem settingsItem = new MenuItem("Settings");
        MenuItem exitItem = new MenuItem("Exit");

        MenuItem aboutItem = new MenuItem("About");
        MenuItem controlsItem = new MenuItem("Controls");

        playItem.setOnAction(e -> openPlayGameInPrimaryStage());
        saveItem.setOnAction(e -> openSaveGameWindow());
        highScoresItem.setOnAction(e -> openSimpleWindow("High Scores", "High scores would be shown here."));
        settingsItem.setOnAction(e -> openSimpleWindow("Settings", "Game settings would go here."));
        exitItem.setOnAction(e -> primaryStage.close());

        aboutItem.setOnAction(e -> openSimpleWindow("About", "Game skeleton app for demonstrating menus, scenes, and stages."));
        controlsItem.setOnAction(e -> openSimpleWindow("Controls", "Arrow keys move pieces.\nUp rotates.\nSpace drops."));

        gameMenu.getItems().addAll(
                playItem,
                saveItem,
                highScoresItem,
                settingsItem,
                new SeparatorMenuItem(),
                exitItem
        );

        helpMenu.getItems().addAll(aboutItem, controlsItem);

        menuBar.getMenus().addAll(gameMenu, helpMenu);

        return menuBar;
    }

    private void openPlayGameInPrimaryStage() {
        primaryStage.setScene(createGameScene());
    }

    private void openSaveGameWindow() {
        Stage saveStage = new Stage();
        saveStage.setTitle("Save Game");

        Label saveLabel = new Label("Save game window");
        saveLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label placeholderLabel = new Label(
                "This opens in a new stage.\n\n" +
                        "You could place save slots, file names, or save options here."
        );
        placeholderLabel.setStyle("-fx-font-size: 14px;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> saveStage.close());

        VBox layout = new VBox(20, saveLabel, placeholderLabel, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 350, 220);
        saveStage.setScene(scene);
        saveStage.show();
    }

    private void openSimpleWindow(String title, String message) {
        Stage stage = new Stage();
        stage.setTitle(title);

        Label heading = new Label(title);
        heading.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label content = new Label(message);
        content.setStyle("-fx-font-size: 14px;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20, heading, content, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 320, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}