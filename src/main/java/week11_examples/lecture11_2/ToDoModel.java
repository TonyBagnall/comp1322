package week11_examples.lecture11_2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToDoModel {
    private final ObservableList<ToDoItem> items = FXCollections.observableArrayList();

    public ObservableList<ToDoItem> getItems() {
        return items;
    }

    public void addItem(String task) {
        if (task != null && !task.isBlank()) {
            items.add(new ToDoItem(task));
        }
    }

    public void removeItem(ToDoItem item) {
        items.remove(item);
    }
}