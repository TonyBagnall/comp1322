package week9_examples;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FirstApp extends Application {
    Button objectButton;
    static Button staticButton;
    Button nestedButton;

    int x = 0;
    static int y = 0;

    @Override
    public void start(Stage primaryStage) {
        Button localButton = new Button("Local");
        objectButton = new Button("Object");
        staticButton = new Button("Static");
        nestedButton = new Button("Nested");

        // Lambda listener
        objectButton.setOnAction(e -> {
            x++;
            y++;
            System.out.println("Lambda listener " + x + "," + y);
        });

        // Static method reference
        localButton.setOnAction(FirstApp::staticButtonClick);

        // Object method reference
        staticButton.setOnAction(this::handleButtonClick);

        // Inner nested class handler
        nestedButton.setOnAction(new NestedButtonHandler());

        HBox box = new HBox(10);
        box.getChildren().addAll(localButton, objectButton, staticButton, nestedButton);

        StackPane root = new StackPane(box);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("JavaFX :: Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(ActionEvent event) {
        x++;
        y++;
        System.out.println("Object listener method " + x + "," + y);
    }

    private static void staticButtonClick(ActionEvent event) {
        y++;
        System.out.println("Static listener method " + y);
    }

    // Inner nested class
    private class NestedButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            x++;
            y++;
            nestedButton.setText("Clicked");
            System.out.println("Nested class listener " + x + "," + y);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}