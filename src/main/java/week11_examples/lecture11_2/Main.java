package week11_examples.lecture11_2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        ToDoModel model = new ToDoModel();
        ToDoView view = new ToDoView();
        new ToDoController(model, view);

        stage.setTitle("Observable To-Do List");
        stage.setScene(new Scene(view.getLayout(), 300, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}