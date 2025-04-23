package week10_examples.lecture10_2;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class ObjectPropertyExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a ChoiceBox and populate it
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        ObservableList<String> choices = FXCollections.observableArrayList("Red", "Green", "Blue");
        choiceBox.setItems(choices);  // or: choiceBox.itemsProperty().set(choices);

        // Access the list "property"
        ListProperty<String> choiceBoxItems = new SimpleListProperty<>(choiceBox.getItems());

        // Create a TabPane and populate it
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Home", new Label("Home Tab"));
        Tab tab2 = new Tab("Settings", new Label("Settings Tab"));
        tabPane.getTabs().addAll(tab1, tab2);

        // Wrap TabPane's tab list as a ListProperty
        ListProperty<Tab> tabListProperty = new SimpleListProperty<>(tabPane.getTabs());

        // Button to print contents of both list properties
        Button printButton = new Button("Print List Contents");
        printButton.setOnAction(e -> {
            System.out.println("ChoiceBox items:");
            choiceBoxItems.forEach(System.out::println);

            System.out.println("\nTabPane tabs:");
            tabListProperty.forEach(tab -> System.out.println(tab.getText()));
        });

        VBox layout = new VBox(10, new Label("Choose a colour:"), choiceBox, tabPane, printButton);
        layout.setPadding(new Insets(15));

        primaryStage.setTitle("ChoiceBox and TabPane Example");
        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
