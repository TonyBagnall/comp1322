package week10_examples.lecture10_3;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a circle
        Circle circle = new Circle(50, Color.RED);
        circle.setCenterX(100);
        circle.setCenterY(100);

        // Create a TranslateTransition
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(2));    // Animation lasts 2 seconds
        transition.setNode(circle);                     // Animate the circle
        transition.setByX(300);                         // Move 300 pixels to the right
        transition.setByY(100);                         // Move 100 pixels down
        transition.setCycleCount(TranslateTransition.INDEFINITE); // Repeat forever
        transition.setAutoReverse(true);                // Move back and forth

        // Start the animation
        transition.play();

        // Set up the scene
        Pane root = new Pane(circle);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("JavaFX Transition Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
