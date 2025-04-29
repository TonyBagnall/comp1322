package week10_examples.lecture10_3;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class RedHoverButton extends Button {
    public RedHoverButton(String text) {
        super(text);
        this.initStyle();
    }

    private void initStyle() {
        // Set the initial style
        this.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        // Change the style when the mouse hovers over the button
        this.setOnMouseEntered(e ->
                this.setStyle("-fx-background-color: red; -fx-text-fill: white;")
        );

        // Revert to the original style when the mouse exits the button
        this.setOnMouseExited(e ->
                this.setStyle("-fx-background-color: white; -fx-text-fill: black;")
        );

        // Set font size
        this.setFont(new Font(24));
    }
}