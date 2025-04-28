package week10_examples.lecture10_3.scenes;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayGameScreen implements ScreenTemplate {
    private BooleanProperty opened = new SimpleBooleanProperty(false);
    @Override
    public Scene getScene() {
        VBox layout = new VBox(10, new Label("Game screen coming soon..."));
        opened.set(true);
        return new Scene(layout, 300, 200);
    }
    public BooleanProperty isOpen(){return opened;}
}