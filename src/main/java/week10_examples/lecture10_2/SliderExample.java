package week10_examples.lecture10_2;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SliderExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox box = new VBox(10);
//        box.getChildren().addAll(localButton, objectButton, staticButton);
        // Create a label to display slider value
        // Create a slider ….
        Slider slider = new Slider(0,100,50);

        DoubleProperty p = slider.valueProperty();
        ChangeListener<Number> change = new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observable,
                    Number oldValue,
                    Number newValue) {
                System.out.println(observable.getValue()+" changed from " + oldValue + " to " + newValue);
            }
        };
        Label valueLabel = new Label("Slider Value: 50 ");
        ChangeListener<Number> change2 = (obs, newV, oldV) ->
                valueLabel.setText("Slider Value: " + newV.intValue());
//        slider.valueProperty().addListener(change2);
        // Add a ChangeListener to the slide
//        slider.valueProperty().addListener(new SliderListener(valueLabel));
        // Optional: Set tick marks
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(4);
        slider.setBlockIncrement(10);
        ProgressBar bar = new ProgressBar(0.5);
        box.getChildren().addAll(valueLabel,slider, bar);
// Bind label text to the slider value
        valueLabel.textProperty().bind(
                slider.valueProperty().asString("Slider Value: %.0f")
        );
// Bind progress bar progress to slider value scaled to 0.0-1.0
        bar.progressProperty().bind(
                slider.valueProperty().divide(100.0)
        );

//
//        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
//            double value = newVal.doubleValue();
//            valueLabel.setText("Slider Value: " + (int) value);
//            bar.setProgress(value / 100.0);
//        });

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