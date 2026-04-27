package week10_examples.lecture10_3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScenesAndStages extends Application {

    // Shared style constants
    private static final String LABEL_STYLE  = "-fx-font-size: 24px;";
    private static final String BUTTON_STYLE =
            "-fx-font-size: 20px; -fx-padding: 12 30 12 30;";

    @Override
    public void start(Stage primaryStage) {

        // ── Scene 1 ──────────────────────────────────────────────
        Label label1 = new Label("This is Scene 1");
        label1.setStyle(LABEL_STYLE);

        Button switchSceneBtn = new Button("Switch to Scene 2");
        switchSceneBtn.setStyle(BUTTON_STYLE);

        Button openStageBtn = new Button("Open a new Window");
        openStageBtn.setStyle(BUTTON_STYLE);

        VBox layout1 = new VBox(20, label1, switchSceneBtn, openStageBtn);
        layout1.setStyle("-fx-padding: 50;");
        Scene scene1 = new Scene(layout1, 600, 400);

        // ── Scene 2 ──────────────────────────────────────────────
        Label label2 = new Label("This is Scene 2");
        label2.setStyle(LABEL_STYLE);

        Button backBtn = new Button("Back to Scene 1");
        backBtn.setStyle(BUTTON_STYLE);

        VBox layout2 = new VBox(20, label2, backBtn);
        layout2.setStyle("-fx-padding: 50;");
        Scene scene2 = new Scene(layout2, 600, 400);

        // ── Button actions ────────────────────────────────────────

        // Swap the scene on the SAME stage
        switchSceneBtn.setOnAction(e -> primaryStage.setScene(scene2));
        backBtn.setOnAction(e -> primaryStage.setScene(scene1));

        // Open a brand-new Stage (second window)
        openStageBtn.setOnAction(e -> {
            Stage secondStage = new Stage();
            secondStage.setTitle("Second Window");

            Label newLabel = new Label("I'm a separate Stage!");
            newLabel.setStyle(LABEL_STYLE);

            Button closeBtn = new Button("Close this window");
            closeBtn.setStyle(BUTTON_STYLE);
            closeBtn.setOnAction(ev -> secondStage.close());

            VBox newLayout = new VBox(20, newLabel, closeBtn);
            newLayout.setStyle("-fx-padding: 50;");

            secondStage.setScene(new Scene(newLayout, 500, 300));
            secondStage.show();
        });

        // ── Configure and show the primary Stage ─────────────────
        primaryStage.setTitle("Scenes & Stages Demo");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}