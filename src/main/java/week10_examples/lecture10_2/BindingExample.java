package week10_examples.lecture10_2;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BindingExample extends Application {
    public void start(Stage stage) {
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        Label fullNameLabel = new Label();

        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");

        // Bind the label's text directly to the text properties of the TextFields
        fullNameLabel.textProperty().bind(
                Bindings.createStringBinding(
                        () -> {
                            String fn = firstNameField.getText();
                            String ln = lastNameField.getText();
                            if ((fn == null || fn.isBlank()) && (ln == null || ln.isBlank())) {
                                return "No name entered";
                            }
                            return "Hello, " + fn + " " + ln;
                        },
                        firstNameField.textProperty(), lastNameField.textProperty()
                )
        );

        VBox root = new VBox(10, firstNameField, lastNameField, fullNameLabel);
        Scene scene = new Scene(root, 300, 150);
        stage.setScene(scene);
        stage.setTitle("Direct Unidirectional Binding");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}