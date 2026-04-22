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
    Button button = new Button("Press me in case of fire");
    String str = "Don't Panic";
    String str2 = "FIRE: RUN AWAY";
    StringProperty sharedText = new SimpleStringProperty("Don't Panic");

    // Create labels
    Label label1 = new Label();
    Label label2 = new Label();
    Label label3 = new Label();

    public void tightlyCoupledApproach() {
            // Create a button
// Set button action with a callback to manually update each label
        label1 = new Label(str);
        label2 = new Label(str);
        label3 = new Label(str);
            button.setOnAction(event -> {
                label1.setText(str2);
                label2.setText(str2);
                label3.setText(str2);
            });
    }
    public void wrongApproach() {
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        button.setOnAction(event -> {
            str = "FIRE: RUN AWAY";
        });
    }

    public void bindingApproach(){
        // Bind labels to the shared text
        label1.textProperty().bind(sharedText);
        label2.textProperty().bind(sharedText);
        label3.textProperty().bind(sharedText);
    }
    @Override
    public void start(Stage primaryStage) {
//        wrongApproach();
        tightlyCoupledApproach();
//        bindingApproach();
        // Set button action to update shared text
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