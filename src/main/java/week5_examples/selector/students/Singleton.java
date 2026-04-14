package week5_examples.functors.students;

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
