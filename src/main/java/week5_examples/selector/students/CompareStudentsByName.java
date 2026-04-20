package week5_examples.selector.students;

import java.util.Comparator;

/**
 * Example of using the user defined interface CompareStudent
 */

public class CompareStudentsByName implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b) {
        return a.getName().compareTo(b.getName());
    }
}
