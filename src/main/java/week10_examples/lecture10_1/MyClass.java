package week10_examples.lecture10_1;

import java.util.Comparator;

public class MyClass {

    public static class MyCompare implements Comparator<MyClass> {
           public int compare(MyClass a, MyClass b){
               return 0;
        }
    }
}
