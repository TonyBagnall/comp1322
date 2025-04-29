package week10_examples.lecture10_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CanvasExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a canvas (width x height)
        Canvas canvas = new Canvas(400, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw a filled rectangle
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(50, 50, 100, 80);

        // Draw a filled circle
        gc.setFill(Color.RED);
        gc.fillOval(200, 100, 60, 60);

        // Draw a line
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(20, 20, 380, 280);

        // Draw text
        gc.setFill(Color.DARKGREEN);
        gc.fillText("JavaFX Canvas Example", 120, 30);

        // Layout
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, 400, 300);

        // Stage
        primaryStage.setTitle("Canvas Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}