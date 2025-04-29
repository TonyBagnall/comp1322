package week10_examples.lecture10_3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InlineStylingExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        Label label = new Label("Enter your name:");
        TextField textField = new TextField();
        Button button = new Button("Submit");

        // Apply inline styles
        label.setStyle("-fx-font-size: 16px; -fx-text-fill: darkslateblue;");
        textField.setStyle("-fx-border-color: grey; -fx-border-width: 2px; -fx-background-color: #f0f0f0;");
        button.setStyle("-fx-background-color: teal; -fx-text-fill: white; -fx-font-size: 14px;");

        // Set button action
        button.setOnAction(event -> {
            String name = textField.getText();
            if (name.trim().isEmpty()) {
                label.setText("Please enter a valid name!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
            } else {
                label.setText("Hello, " + name + "!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");
            }
        });

        // Layout
        VBox root = new VBox(10); // spacing = 10
        root.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-background-color: whitesmoke;");
        root.getChildren().addAll(label, textField, button);

        // Scene
        Scene scene = new Scene(root, 300, 200);

        // Stage
        primaryStage.setTitle("Styled JavaFX Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}