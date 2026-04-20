package week5_examples.selector.students;

/**
 * Example of using the built in interface Comparator
 */

import java.util.Comparator;

public class CompareStudentsByAge implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b) {
        return a.getAge()-b.getAge();
    }
}
