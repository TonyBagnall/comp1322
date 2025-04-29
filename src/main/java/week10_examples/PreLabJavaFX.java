package week10_examples;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreLabJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the label
        Label label = new Label("Initial Text");

        // Create the button
        Button button = new Button("Click Me");

        // Set action for button
        button.setOnAction(e -> label.setText("Button Clicked!"));

        // Arrange label and button in a vertical layout
        VBox layout = new VBox(10); // 10px spacing
        layout.getChildren().addAll(label, button);

        // Create and set up the scene
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("JavaFX Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
