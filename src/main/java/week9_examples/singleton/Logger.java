package week9_examples.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Logger {
    // Create the single instance when the class is loaded
    private ArrayList<String> messages;
    private DateTimeFormatter formatter;
    // Private constructor stops other objects being created
    private Logger() {
        messages = new ArrayList<>();
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    public void printLog() {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    // Return the stored messages
    public ArrayList<String> getMessages() {
        return messages;
    }
    // Global access point
    private static final Logger instance = new Logger();
    public static Logger getInstance() {
        return instance;
    }

    // Simple logging method
    public void log(String message) {
        String timeStamp = LocalDateTime.now().format(formatter);
        messages.add("[" + timeStamp + "] " + message);
    }
}
