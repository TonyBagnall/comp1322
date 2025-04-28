package week10_examples.lecture10_3.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsScreen implements ScreenTemplate{
    public Scene getScene() {
        VBox layout = new VBox(10, new Label("Settings screen coming soon..."));
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 300, 200);
    }
}
