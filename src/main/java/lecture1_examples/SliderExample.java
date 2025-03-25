package lecture1_examples;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SliderExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox box = new HBox(10);
//        box.getChildren().addAll(localButton, objectButton, staticButton);
        // Create a label to display slider value
        Label valueLabel = new Label("Slider Value: 50 ");
        // Create a slider ….
        Slider slider = new Slider(0,100,50);
        // Add a ChangeListener to the slide
        slider.valueProperty().addListener(new SliderListener(valueLabel));
        // Optional: Set tick marks
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(4);
        slider.setBlockIncrement(10);
        box.getChildren().addAll(valueLabel,slider);
        StackPane root = new StackPane(box);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX :: Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
// Static nested class for slider listener
    static class SliderListener implements ChangeListener<Number> {
        private Label label;
        public SliderListener(Label label) {
            this.label = label;
        }
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            label.setText("Slider Value: " + newValue.intValue());
        }
    }

    public static void main(String[] args) {
        var name = "Alice";      // Compiler infers name is a String
        var number = 42;         // Compiler infers number is an Integer
        var list = new ArrayList<String>();
        launch(args);
    }
}