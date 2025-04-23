package week10_examples.lecture10_2;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BasicBindingExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Define a property to hold shared text
        StringProperty sharedText = new SimpleStringProperty("Don't Panic");
        // Create a button
        Button button = new Button("Press me in case of fire");

        // Create labels
        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();

        // Bind labels to the shared text
        label1.textProperty().bind(sharedText);
        label2.textProperty().bind(sharedText);
        label3.textProperty().bind(sharedText);

        // Set button action to update shared text
        button.setOnAction(event -> sharedText.set("FIRE: Run away"));

        VBox vbox = new VBox(10, button, label1, label2, label3);
        vbox.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        primaryStage.setTitle("Basic Binding");
        primaryStage.setScene(new Scene(vbox, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}