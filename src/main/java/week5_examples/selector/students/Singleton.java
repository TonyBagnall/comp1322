package week5_examples.selector.students;

public class Singleton {

    private Singleton(){

    }
    private static Singleton theOne;

    public static Singleton getSingleton(){
        if (theOne == null)
            theOne = new Singleton();

        return theOne;
    }
}
