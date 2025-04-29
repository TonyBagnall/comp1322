package week10_examples.lecture10_3;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerExample extends Application {

    private Label timerLabel;
    private int secondsPassed = 0;

    private void simpleTimer(Stage primaryStage) throws Exception {
        timerLabel = new Label("Seconds: 0");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateTimer())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); // Start the timer

        VBox root = new VBox(timerLabel);
        Scene scene = new Scene(root, 200, 100);

        primaryStage.setTitle("Timer Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        simpleTimer(primaryStage);
    }

    private void updateTimer() {
        // This method is called every second
        secondsPassed++;
        timerLabel.setText("Seconds: " + secondsPassed);
    }

    public static void main(String[] args) {
        launch(args);
    }
}