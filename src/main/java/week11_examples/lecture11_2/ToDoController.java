package week11_examples.lecture11_2;
public class ToDoController {
    private final ToDoModel model;
    private final ToDoView view;

    public ToDoController(ToDoModel model, ToDoView view) {
        this.model = model;
        this.view = view;

        view.getListView().setItems(model.getItems());
        attachEvents();
    }

    private void attachEvents() {
        view.getAddButton().setOnAction(e -> {
            String task = view.getInputField().getText();
            if (!task.isBlank()) {
                model.addItem(task);
                view.getInputField().clear();
                view.getListView().refresh(); // manual refresh
            }
        });

        view.getListView().setOnMouseClicked(e -> {
            ToDoItem selected = view.getListView().getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setCompleted(!selected.isCompleted());
                view.getListView().refresh(); // manual refresh
            }
        });
    }
}