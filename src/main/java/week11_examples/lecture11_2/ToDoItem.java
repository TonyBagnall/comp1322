package week11_examples.lecture11_2;
import javafx.beans.property.*;

public class ToDoItem {
    private final StringProperty task = new SimpleStringProperty();
    private final BooleanProperty completed = new SimpleBooleanProperty(false);

    public ToDoItem(String task) {
        this.task.set(task);
    }
    public String getTask() {
        return task.get();
    }
    public void setTask(String task) {
        this.task.set(task);
    }
    public StringProperty taskProperty() {
        return task;
    }
    public boolean isCompleted() {
        return completed.get();
    }

    public void setCompleted(boolean completed) {
        this.completed.set(completed);
    }

    public BooleanProperty completedProperty() {
        return completed;
    }
    @Override
    public String toString() {
        return (isCompleted() ? "[✔] " : "[ ] ") + getTask();
    }
}