package lecture1_examples;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FirstApp extends Application {
    Button objectButton;
    static Button staticButton;
    int x =0;
    static int y =0;

    @Override
    public void start(Stage primaryStage) {
        Button localButton = new Button("Local");
        objectButton = new Button(" Object");
        staticButton = new Button(" Static");
        // Using method reference instead of lambda: e -> handleButtonClick()
        objectButton.setOnAction(e->{
            x++;
            y++;
            System.out.println("Lambda listener"+x+","+y);
        });
        localButton.setOnAction(FirstApp::staticButtonClick);
        staticButton.setOnAction(this::handleButtonClick);

        HBox box = new HBox(10);
        box.getChildren().addAll(localButton, objectButton, staticButton);
        StackPane root = new StackPane(box);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX :: Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // This method will be called when the button is clicked
    private void handleButtonClick(javafx.event.ActionEvent event) {
        x++;
        y++;
        System.out.println("Object listener method"+x+","+y);
    }
    private static void staticButtonClick(javafx.event.ActionEvent event) {
        y++;
        System.out.println("Static listener method "+y);
    }

    public static void main(String[] args) {
        launch(args);
    }
}