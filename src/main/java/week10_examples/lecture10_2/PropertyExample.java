package week10_examples.lecture10_2;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PropertyExample extends Application {
    public void start(Stage primaryStage) {
        TextField textField = new TextField("Goodbye");
        Label label = new Label("Hello");
        StringProperty strProperty = label.textProperty();
        String str = strProperty.get();
        StringProperty strProperty2 = textField.textProperty();
        String str2 = strProperty2.getValue();
        Button button = new Button("My Button");
        Boolean b1 = button.defaultButtonProperty().get();
        BooleanProperty bProperty = button.cancelButtonProperty();
        System.out.println(" b1 = "+" property = "+bProperty);
        String str3 = button.textProperty().get();
        System.out.println(" str = "+str+" str2 = "+str2+" str3 = "+str3);


        VBox root = new VBox(10, textField, label, button);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("JavaFX Property Binding Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}