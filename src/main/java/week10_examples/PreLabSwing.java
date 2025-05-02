package week10_examples;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PreLabSwing {
    public static void main(String[] args) {
        // Create the window (frame)
        JFrame frame = new JFrame("Swing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a panel to hold components
        JPanel panel = new JPanel();

        // Create the label
        JLabel label = new JLabel("Initial Text");

        // Create the button
        JButton button = new JButton("Click Me");

        // Add action listener to the button
        button.addActionListener(e -> label.setText("Button Clicked!"));

        // Add components to panel
        panel.add(label);
        panel.add(button);

        // Add panel to frame and make visible
        frame.add(panel);
        frame.setVisible(true);
    }
}