package lecture1_examples;

import java.util.function.Predicate;

public class Student {
        int year =0;
        public Student(int y){
            year=y;
        }
        static boolean isFirstYear(Student s){
            return s.year==1;
        }

    public static void main(String[] args) {
            Predicate<Student> pred = Student::isFirstYear;
            Predicate<Student> pred2 = s -> s.year == 1;
            Student alice = new Student(1);
            Student bob = new Student(2);
            System.out.println(" Bob first year ? " +pred.test(bob));
            System.out.println(" Bob first year ? " +pred2.test(bob));
            System.out.println(" Alice first year ? " +pred.test(alice));
            System.out.println(" Alice first year ? " +pred2.test(alice));
//            year1 = selectStudentsPredicate(full, pred);
        }

}
