package week10_examples.lecture10_3;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProgressBarExample extends Application {

    private static final int TOTAL_SECONDS = 10; // Total countdown time
    private int secondsRemaining = TOTAL_SECONDS;
    private ProgressBar progressBar;

    @Override
    public void start(Stage primaryStage) {
        progressBar = new ProgressBar(1.0); // Start full
        progressBar.setStyle("-fx-accent: blue;"); // Initial colour: blue
        // Make the progress bar bigger
        progressBar.setPrefWidth(400);   // Wider
        progressBar.setPrefHeight(40);   // Taller

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateProgress())
        );
        timeline.setCycleCount(TOTAL_SECONDS);
        timeline.play();

        VBox root = new VBox(20, progressBar);
        root.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(root, 300, 100);

        primaryStage.setTitle("Countdown ProgressBar Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateProgress() {
        secondsRemaining--;
        double progress = (double) secondsRemaining / TOTAL_SECONDS;
        progressBar.setProgress(progress);

        // Change colour when 5 seconds are left
        if (secondsRemaining == 5) {
            progressBar.setStyle("-fx-accent: red;");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}