package week7_examples.functors.students;/*
week7_examples.functors.Student example used in Week 7 functor examples
 */

public class Student {
    private String name;
    private int age;
    private String school;
    public Student() {
    }
    public Student(Student s) {
        name = s.name;
        age = s.age;
        school=s.school;
    }
    public Student(String n, int s) {
        name = n;
        age = s;
    }
    public Student(String sk, String n, int s) {
        this(n,s);
        school=sk;
    }


    public void setAge(int s) {
        age = s;
    }

    public int getAge() {
        return age;
    }

    public void setName(String s) {
        name = s;
    }
    public String getName() {
        return name;
    }

    public Student higherScore (Student a){
        if(age >a.age)
                    return this;
        return a;
    }

//Static variables and methods
    private static String university="UEA";
    public static int numStudents=0;

    public static void setUni(String s)
    {
            university=s;
    }
    public static void setNumStudent(int a){numStudents=a;}
    public String toString(){
        return name+" aged = "+age;
    }

}
