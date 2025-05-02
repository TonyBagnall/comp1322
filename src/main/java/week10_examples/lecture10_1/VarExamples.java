package week10_examples.lecture10_1;

import java.util.ArrayList;
import java.util.List;

public class VarExamples {

    public static void main(String[] args) {
        System.out.println(" Simple VAR examples");
        var number = 49;         // Inferred as int
        var message = "MLS";  // Inferred as String

        System.out.println("number = " + number + ", type = " + ((Object) number).getClass().getSimpleName());
        System.out.println("message = " + message + ", type = " + message.getClass().getSimpleName());

        var list = new ArrayList<String>();  // inferred as week11_examples.lecture11_1.ArrayList<String>
        list.add("Saka");
        list.add(message);

        System.out.println("list = " + list + ", type = " + list.getClass().getSimpleName());
        var names = List.of("Alice", "Bob", "Charlie");  // inferred as List<String>
        for (var name : names) {                         // each 'name' is inferred as String
            System.out.println("name = " + name + ", type = " + name.getClass().getSimpleName());
        }

    }
}
