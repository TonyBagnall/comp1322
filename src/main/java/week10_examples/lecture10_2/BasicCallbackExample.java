package week10_examples.lecture10_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BasicCallbackExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a button
        Button button = new Button("Press me in case of fire");
        String str = "Don't Panic";
        String str2 = "FIRE: RUN AWAY";
        // Create labels
        Label label1 = new Label(str);
        Label label2 = new Label(str);
        Label label3 = new Label(str);

        // Set button action with a callback to manually update each label
        button.setOnAction(event -> {
            label1.setText(str2);
            label2.setText(str2);
            label3.setText(str2);
        });

        VBox vbox = new VBox(10, button, label1, label2, label3);
        vbox.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        primaryStage.setTitle("Callbacks Only");
        primaryStage.setScene(new Scene(vbox, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}