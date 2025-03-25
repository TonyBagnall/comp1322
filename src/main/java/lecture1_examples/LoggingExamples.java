package lecture1_examples;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggingExamples {
    private static final Logger logger = LogManager.getLogger(LoggingExamples.class);

    public static void example() {
        logger.info("Application started");

        int number = 5;
        logger.debug("The initial number is: {}", number);

        try {
            int result = divide(10, 0);
            logger.debug("Division result: {}", result);
        } catch (Exception e) {
            logger.error("An error occurred: ", e);
        }

        logger.info("Application finished");
    }

    public static int divide(int a, int b) {
        logger.debug("Dividing {} by {}", a, b);
        return a / b;  // This will throw an exception if b = 0
    }

    public static void main(String[] args) {
        // Simple logging
        LoggingExamples.example();


    }

}
