package week10_examples.lecture10_2;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BidirectionalBindingExample extends Application {

    @Override
    public void start(Stage stage) {
        StringProperty sharedText = new SimpleStringProperty();
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        sharedText.bindBidirectional(textField1.textProperty());
        textField2.textProperty().bindBidirectional(sharedText);

        // Layout
        VBox root = new VBox(10, textField1, textField2);
        Scene scene = new Scene(root, 300, 100);

        stage.setTitle("Bidirectional Binding Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}