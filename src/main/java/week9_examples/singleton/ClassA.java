package week9_examples.singleton;

public class ClassA {
    public void doTask() {
        Logger logger = Logger.getInstance();
        logger.log("ClassA is starting its task");
        logger.log("ClassA has finished its task");
    }

}
