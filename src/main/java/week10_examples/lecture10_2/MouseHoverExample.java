package week10_examples.lecture10_2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MouseHoverExample extends Application {

    @Override
    public void start(Stage stage) {
        Label handlerLabel = new Label("Handler output: none");
        Label listenerLabel = new Label("Listener output: none");
        Label instructionLabel = new Label("Move the mouse over the rectangle");

        Rectangle rect = new Rectangle(200, 100);
        rect.setFill(Color.LIGHTBLUE);
        rect.setStroke(Color.BLACK);

        // Event handlers: react to mouse events
        rect.setOnMouseEntered(event -> {
            handlerLabel.setText("Handler output: mouse entered");
        });

        rect.setOnMouseExited(event -> {
            handlerLabel.setText("Handler output: mouse exited");
        });

        // Change listener: reacts to property changes
        rect.hoverProperty().addListener((obs, oldVal, newVal) -> {
            listenerLabel.setText(
                    "Listener output: hover changed from " + oldVal + " to " + newVal
            );
        });

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(instructionLabel, rect, handlerLabel, listenerLabel);

        Scene scene = new Scene(root, 400, 250);
        stage.setTitle("Handlers vs Listeners");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}