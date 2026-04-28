package week10_examples.lecture10_3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 * Controller for the falling-block MVC example.
 *
 * The controller handles time-based behaviour and user input.
 * It asks the model to change state, then asks the view to redraw.
 */
public class FallingBlockController {

    private final FallingBlockModel model;
    private final FallingBlockView view;
    private final Timeline timeline;

    private boolean paused = false;

    public FallingBlockController(FallingBlockModel model, FallingBlockView view) {
        this.model = model;
        this.view = view;

        timeline = new Timeline(
                new KeyFrame(Duration.millis(500), event -> step())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        paused = false;
        model.resetDrop();
        view.render(model);
        timeline.playFromStart();
    }

    public void stop() {
        timeline.stop();
    }

    public void pause() {
        paused = true;
        timeline.pause();
    }

    public void resume() {
        if (!model.isBlockAtBottom()) {
            paused = false;
            timeline.play();
        }
    }

    public void togglePause() {
        if (model.isBlockAtBottom()) {
            return;
        }

        if (paused) {
            resume();
        } else {
            pause();
        }
    }

    public void attachKeyboard(Scene scene) {
        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            if (key == KeyCode.R) {
                start();
                return;
            }

            if (key == KeyCode.SPACE) {
                togglePause();
                return;
            }

            if (paused || model.isBlockAtBottom()) {
                return;
            }

            if (key == KeyCode.LEFT) {
                model.moveBlockLeft();
            } else if (key == KeyCode.RIGHT) {
                model.moveBlockRight();
            } else if (key == KeyCode.DOWN) {
                model.moveBlockDown();
            } else {
                return;
            }

            view.render(model);
            stopIfBlockAtBottom();
        });
    }

    private void step() {
        if (paused || model.isBlockAtBottom()) {
            return;
        }

        model.moveBlockDown();
        view.render(model);
        stopIfBlockAtBottom();
    }

    private void stopIfBlockAtBottom() {
        if (model.isBlockAtBottom()) {
            timeline.stop();
            paused = false;
        }
    }
}