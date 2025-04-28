package week10_examples.lecture10_3.custom;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomComponentExamples extends Application {
    public void start(Stage primaryStage) {
        VBox box = new VBox();
        for(int i = 0; i<6; i++)
            box.getChildren().add(new RedHoverButton("Button number "+i));

        StackPane root = new StackPane(box);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX :: Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
