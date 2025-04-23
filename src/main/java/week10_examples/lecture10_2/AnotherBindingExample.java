package week10_examples.lecture10_2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AnotherBindingExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Labels
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        // TextFields
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        // Buttons
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        saveButton.disableProperty().bind(
                firstNameField.textProperty().isEmpty().or(
                        lastNameField.textProperty().isEmpty()));
//        saveButton.disableProperty().bind(lastNameField.textProperty().isEmpty());
//        firstNameField.textProperty().addListener((obs, oldVal, newVal) -> {
//            saveButton.setDisable(newVal.trim().isEmpty());
//        });
//        lastNameField.textProperty().addListener((obs, oldVal, newVal) -> {
//            saveButton.setDisable(newVal.trim().isEmpty());
//        });

        // GridPane for form layout
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));
        form.add(firstNameLabel, 0, 0);
        form.add(firstNameField, 1, 0);
        form.add(lastNameLabel, 0, 1);
        form.add(lastNameField, 1, 1);

        // HBox for buttons
        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(10, form, buttonBox);
        layout.setPadding(new Insets(10));

        primaryStage.setTitle("Binding");
        primaryStage.setScene(new Scene(layout, 300, 150));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}