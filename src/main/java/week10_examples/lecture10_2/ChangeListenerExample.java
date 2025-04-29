package week10_examples.lecture10_2;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeListenerExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a simple integer property
        SimpleIntegerProperty counter = new SimpleIntegerProperty(0);

        // Add a ChangeListener
        counter.addListener(new ChangeListener<Number>() {
            @Override
            // Anonymous class version
            public void changed(
                    ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Counter changed from " + oldValue + " to " + newValue);
            }
        });
        counter.addListener((observable, oldValue, newValue) ->
                System.out.println("A lambda version " + oldValue + " to " + newValue));


        // Button to increment the counter
        Button btn = new Button("Increment Counter");
        btn.setOnAction(e -> counter.set(counter.get() + 1));

        VBox root = new VBox(10, btn);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 300, 150);

        primaryStage.setTitle("ChangeListener Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}