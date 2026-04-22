package week10_examples;

import java.util.Comparator;

public class Student {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static class CompareByAge
            implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            return a.age - b.age;
        }
    }

    public static class CompareByName
            implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            return a.name.compareTo(b.name);
        }
    }
}
