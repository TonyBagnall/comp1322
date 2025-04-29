package week10_examples.lecture10_3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StyleSheetExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        Label label = new Label("Enter your name:");
        TextField textField = new TextField();
        Button button = new Button("Submit");

        // Add style classes
        label.getStyleClass().add("my-label");
        textField.getStyleClass().add("my-textfield");
        button.getStyleClass().add("my-button");

        // Set button action
        button.setOnAction(event -> {
            String name = textField.getText();
            if (name.trim().isEmpty()) {
                label.setText("Please enter a valid name!");
                label.getStyleClass().removeAll("success-label");
                label.getStyleClass().add("error-label");
            } else {
                label.setText("Hello, " + name + "!");
                label.getStyleClass().removeAll("error-label");
                label.getStyleClass().add("success-label");
            }
        });

        // Layout
        VBox root = new VBox(10); // spacing = 10
        root.getStyleClass().add("root-pane");
        root.getChildren().addAll(label, textField, button);

        // Scene
        Scene scene = new Scene(root, 300, 200);
        // Attach the stylesheet
        var style = getClass().getResource("/style.css").toExternalForm();
        // Attach the stylesheet
        scene.getStylesheets().add(style);

        // Stage
        primaryStage.setTitle("Styled JavaFX with CSS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}