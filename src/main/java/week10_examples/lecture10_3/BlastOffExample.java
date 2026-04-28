package week10_examples.lecture10_3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class BlastOffExample extends Application {

    private SimpleIntegerProperty counter;
    private Label countdownLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        countdownTimer(primaryStage);
    }

    private void countdownTimer(Stage primaryStage) throws Exception {
        counter = new SimpleIntegerProperty(20);

        countdownLabel = new Label();
        countdownLabel.textProperty().bind(
                Bindings.when(counter.isEqualTo(0))
                        .then("BLAST OFF")
                        .otherwise(counter.asString())
        );

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (counter.get() > 0) {
                        counter.set(counter.get() - 1);
                    }
                })
        );
        timeline.setCycleCount(20); // 10 down to 0
        timeline.play();

        VBox root = new VBox(20, countdownLabel);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");
        Scene scene = new Scene(root, 300, 150);

        primaryStage.setTitle("Countdown Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
