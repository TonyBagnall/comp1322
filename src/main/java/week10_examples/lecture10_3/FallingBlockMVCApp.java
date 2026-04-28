package week10_examples.lecture10_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the falling-block MVC example.
 *
 * This class wires together the model, view and controller.
 */
public class FallingBlockMVCApp extends Application {

    private static final int WINDOW_WIDTH = 540;
    private static final int WINDOW_HEIGHT = 680;

    @Override
    public void start(Stage stage) {
        FallingBlockModel model = new FallingBlockModel();
        FallingBlockView view = new FallingBlockView(model);
        FallingBlockController controller = new FallingBlockController(model, view);

        Scene scene = new Scene(view.getRoot(), WINDOW_WIDTH, WINDOW_HEIGHT);

        controller.attachKeyboard(scene);

        stage.setTitle("Falling Block MVC");
        stage.setScene(scene);
        stage.show();

        controller.start();
        view.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}