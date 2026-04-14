package week9_examples.singleton;

public class App {
    public static void main(String[] args) {
        ClassA a = new ClassA();
        ClassB b = new ClassB();

        a.doTask();
        b.doTask();

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println(logger1 == logger2); // true
        logger1.printLog();
    }
}
