package week10_examples.lecture10_3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GifExample extends Application {

    private ImageView gifView;

    @Override
    public void start(Stage primaryStage) {
        // Initial GIF
        gifView = new ImageView(loadGif("/upforgrabs.gif"));
        gifView.setFitWidth(150);
        gifView.setPreserveRatio(true);

        // Label
        Label label = new Label("Click a button to change the animation:");
        label.setStyle("-fx-font-size: 14px;");

        // Buttons
        RedHoverButton button1 = new RedHoverButton("Arsenal");
        RedHoverButton button2 = new RedHoverButton("Zidane");
        RedHoverButton button3 = new RedHoverButton("Get my coat");

        // Button actions
        button1.setOnAction(e -> changeGif("/upforgrabs.gif"));
        button2.setOnAction(e -> changeGif("/zidane.gif"));
        button3.setOnAction(e -> changeGif("/bournmouth.gif"));

        // Layout
        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-background-color: #ffffff;");
        root.getChildren().addAll(gifView, label, button1, button2, button3);

        // Scene and Stage
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("GIF Switcher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to load a GIF
    private Image loadGif(String path) {
        return new Image(getClass().getResource(path).toExternalForm());
    }

    // Change the image shown
    private void changeGif(String path) {
        gifView.setImage(loadGif(path));
    }
    public static void main(String[] args) {
        launch(args);
    }
}