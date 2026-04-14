package week9_examples.singleton;

public class ClassB {
    public void doTask() {
        Logger logger = Logger.getInstance();
        logger.log("ClassB is starting its task");
        logger.log("ClassB has finished its task");
    }
}
