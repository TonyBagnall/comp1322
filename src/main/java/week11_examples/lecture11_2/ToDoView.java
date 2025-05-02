package week11_examples.lecture11_2;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ToDoView {
    private final TextField inputField = new TextField();
    private final Button addButton = new Button("Add Task");
    private final ListView<ToDoItem> listView = new ListView<>();
    private final VBox layout = new VBox(10, inputField, addButton, listView);

    public VBox getLayout() {
        return layout;
    }

    public TextField getInputField() {
        return inputField;
    }

    public Button getAddButton() {
        return addButton;
    }

    public ListView<ToDoItem> getListView() {
        return listView;
    }
}