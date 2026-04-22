package week10_examples.lecture10_2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonStateExample extends Application {

    @Override
    public void start(Stage stage) {
        Button button = new Button("Hover over me\nor press me");
        button.setMinSize(360, 180);
        button.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-width: 2;"
        );

        Label eventLabel = new Label("Last event: none");
        Label hoverLabel = new Label("Hover state: false");
        Label pressedLabel = new Label("Pressed state: false");

        eventLabel.setStyle("-fx-font-size: 24px;");
        hoverLabel.setStyle("-fx-font-size: 24px;");
        pressedLabel.setStyle("-fx-font-size: 24px;");

        // Event handlers, these react to events happening
        button.setOnMouseEntered(e -> eventLabel.setText("Last event: mouse entered"));
        button.setOnMouseExited(e -> eventLabel.setText("Last event: mouse exited"));
        button.setOnMousePressed(e -> eventLabel.setText("Last event: mouse pressed"));
        button.setOnMouseReleased(e -> eventLabel.setText("Last event: mouse released"));

        // Listeners, these react to state changing
        button.hoverProperty().addListener((obs, oldVal, newVal) -> {
            hoverLabel.setText("Hover state: " + newVal);
            updateButtonStyle(button);
        });

        button.pressedProperty().addListener((obs, oldVal, newVal) -> {
            pressedLabel.setText("Pressed state: " + newVal);
            updateButtonStyle(button);
        });

        VBox root = new VBox(20, button, eventLabel, hoverLabel, pressedLabel);
        root.setPadding(new Insets(25));

        Scene scene = new Scene(root, 650, 420);
        stage.setTitle("Handlers vs Listeners");
        stage.setScene(scene);
        stage.show();
    }

    private void updateButtonStyle(Button button) {
        String baseStyle =
                "-fx-font-size: 28px;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-width: 2;";

        if (button.isPressed()) {
            button.setStyle(baseStyle + "-fx-background-color: salmon;");
        } else if (button.isHover()) {
            button.setStyle(baseStyle + "-fx-background-color: lightblue;");
        } else {
            button.setStyle(baseStyle + "-fx-background-color: lightgray;");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}