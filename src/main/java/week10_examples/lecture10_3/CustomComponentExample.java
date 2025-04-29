package week10_examples.lecture10_3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomComponentExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create several custom buttons
        RedHoverButton button1 = new RedHoverButton("Button One");
        RedHoverButton button2 = new RedHoverButton("Button Two");
        RedHoverButton button3 = new RedHoverButton("Button Three");

        // Layout
        VBox root = new VBox(15); // spacing between buttons
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #f9f9f9;");
        root.getChildren().addAll(button1, button2, button3);

        // Scene and Stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("RedHoverButton Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
