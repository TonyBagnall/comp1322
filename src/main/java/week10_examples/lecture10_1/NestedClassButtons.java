package week10_examples.lecture10_1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NestedClassButtons extends Application {

    // Instance fields: each App object gets its own copy
    private final TextArea instanceArea = new TextArea();
    private final TextArea sharedArea = new TextArea();

    // Static field: one copy shared by the whole class
    private static int totalClicks = 0;

    public class InnerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            totalClicks++;
            instanceArea.appendText("Inner class handler fired\n");
            sharedArea.appendText("totalClicks = " + totalClicks + "\n");
        }
    }

    public static class NestedButtonHandler implements EventHandler<ActionEvent> {
        private final TextArea outputArea;

        public NestedButtonHandler(TextArea outputArea) {
            this.outputArea = outputArea;
        }

        @Override
        public void handle(ActionEvent event) {
            totalClicks++;

            // Cannot access instanceArea directly here
            outputArea.appendText("Static nested class handler fired\n");
            outputArea.appendText("totalClicks = " + totalClicks + "\n");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Button button1 = new Button("Use inner class");
        Button button2 = new Button("Use nested class");

        instanceArea.setEditable(false);
        sharedArea.setEditable(false);

        instanceArea.setPrefRowCount(10);
        instanceArea.setPrefColumnCount(20);
        sharedArea.setPrefRowCount(10);
        sharedArea.setPrefColumnCount(20);

        button1.setOnAction(new InnerButtonHandler());
        button2.setOnAction(new NestedButtonHandler(sharedArea));

        VBox buttonBox = new VBox(10, button1, button2);
        VBox textBox = new VBox(10, instanceArea, sharedArea);

        HBox root = new HBox(15, buttonBox, textBox);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 600, 280);
        primaryStage.setTitle("Inner vs Static Nested Classes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}