package week7_examples.functors.students;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FunctorExamples{
    static void selectionSort(Student[] s, CompareStudent cmp){
        for(int i=0;i<s.length-1;i++){
            int pos=i;
            for(int j=i+1;j<s.length;j++){
                if(cmp.compare(s[j],s[pos])<0)
                    pos=j;
            }
            Student temp=s[i];
            s[i]=s[pos];
            s[pos]=temp;
        }
    }
    static void selectionSort(Student[] s, Comparator<Student> cmp){
        for(int i=0;i<s.length-1;i++){
            int pos=i;
            for(int j=i+1;j<s.length;j++){
                if(cmp.compare(s[j],s[pos])<0)
                    pos=j;
            }
            Student temp=s[i];
            s[i]=s[pos];
            s[pos]=temp;
        }
    }


    public static void studentExamples(){
        Student[] s= new Student[3];
        s[0]=new Student("Alice",66);
        s[1]=new Student("Zac",55);
        s[2]=new Student("Thomas",88);
        Student temp=s[0];
        Comparator<Student> functor=new CompareStudentsByAge();
        selectionSort(s,functor);


        int pos=Arrays.binarySearch(s,temp,functor);
        System.out.println(" pos of "+temp+" now  = "+pos);
        List<Student> s2 = Arrays.asList(s);
        Collections.sort(s2,functor);


        for(Student st:s)
            System.out.println(st);
        selectionSort(s,new CompareStudentsByName());
        for(Student st:s)
            System.out.println(st);
        pos=Arrays.binarySearch(s,temp,functor);
        System.out.println(" pos of "+temp+" now  = "+pos);
    }
    public static void main(String[] args) {
        studentExamples();
    }
}
