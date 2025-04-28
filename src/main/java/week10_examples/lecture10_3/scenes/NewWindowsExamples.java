package week10_examples.lecture10_3.scenes;
import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewWindowsExamples extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button playButton = new Button("Play Game");
        PlayGameScreen pg = new PlayGameScreen();
        playButton.disableProperty().bind(pg.isOpen());

        Button saveButton = new Button("Save Game");
        Button settingsButton = new Button("Settings");
        SaveGameScreen sg = new SaveGameScreen();
        SettingsScreen set = new SettingsScreen();
        // Set button actions to open new windows
        playButton.setOnAction(e -> openScreen(pg.getScene(), "Play"));
        saveButton.setOnAction(e -> openScreen(sg.getScene(), "Save your progress here."));
        settingsButton.setOnAction(e -> openScreen(set.getScene(), "Top Players:"));

//         When Play Game is clicked, switch scene on the same stage
        playButton.setOnAction(e -> {
            PlayGameScreen playScreen = new PlayGameScreen();
            primaryStage.setScene(playScreen.getScene());
        });


        VBox mainMenu = new VBox(15, playButton, saveButton, settingsButton);
        mainMenu.setPadding(new Insets(20));
        mainMenu.setStyle("-fx-alignment: center;");

        Scene mainScene = new Scene(mainMenu, 300, 250);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    // Helper method to create and show a new window (scene + stage)
    private void openScreen(Scene newScene, String title) {
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setTitle(title);
        newStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}