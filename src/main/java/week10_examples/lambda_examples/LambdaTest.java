/*
Code to demonstrate lambdas. All currently using raw types, will revisit with generics later.

*/
package week9_examples.lambda_examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 *
 * @author ajb
 */
public class LambdaTest {

    /**
     * Raw type example
     * @param a
     * @param s
     * @return
     */
    static ArrayList selectFromList(List a, Selector s){
         ArrayList a2=new ArrayList();
           for(Object ob:a){
            if(s.select(ob))
                a2.add(ob);
        }
        return a2;
    }
    static <T> ArrayList<T> selectFromList2(List<T> a, Selector<T> s){
        ArrayList<T> a2=new ArrayList<T>();
        for(T ob:a){
            if(s.select(ob))
                a2.add(ob);
        }
        return a2;
    }


    public static List makeExampleStudentList(){
        LinkedList<Student> list=new LinkedList();
       list.add(new Student("Bob",44,1));
       list.add(new Student("Alice",58,3));
       list.add(new Student("Jim",63,2));
       list.add(new Student("Fred",88,1));
       list.add(new Student("Lucy",68,3));
       return list;
    }
    public static void main(String[] args) {




        selectExamplesWithLambdas();
//Generics with lambdas
        BiFunction<Car, Student,String> bi =(a, b)->a.toString()+b.toString();
        Student bob=new Student("Bob",65,2);
        Car bmw= new Car("BMW","Blue",2,1,2000);
        String str=bi.apply(bmw,bob);
        
    }

    public static void selectExamplesWithLambdas() {
        List l= makeExampleStudentList();
        Selector<Student> s=o-> o.year==1;
        ArrayList<Student> firstYears=selectFromList(l,s);
        System.out.println("First years with lambda="+firstYears);
        ArrayList secondYears=selectFromList(l,s);
        System.out.println("Second years ="+secondYears);
        ArrayList thirdYears=selectFromList(l,(Object o)-> ((Student)o).year==3);
        System.out.println("third years ="+thirdYears);

        List<Student> studentList = (List<Student>)l;
        ArrayList<Student> third=selectFromList2(studentList,o-> o.year==3);
        System.out.println("third years ="+thirdYears);


        Selector s2=(Object o)-> ((Student)o).score>40 && ((Student)o).score<=50;

       Selector s3=(Object o)-> {
           if(((Student)o).score>40 && ((Student)o).score<=50) return true;
           return false;
       };
       Selector<Student> two_two = o -> o.score>40 &&  o.score <=50;
        System.out.println("2.2 students = "+selectFromList2(studentList,two_two));
    }
    
    
    public static void selectExamplesWithFunctors() {
        LinkedList<Student> list=new LinkedList();
       list.add(new Student("Bob",44,1));
       list.add(new Student("Alice",58,3));
       list.add(new Student("Jim",63,2));
       list.add(new Student("Fred",88,1));
       list.add(new Student("Lucy",68,3));
       Selector s=new Student.selectYear1();
       ArrayList firstYears=selectFromList(list,s);
        System.out.println("First years ="+firstYears);
       ArrayList secondYears=selectFromList(list,new Selector(){
           public boolean select(Object o){
               if(((Student)o).year==2) return true;
               return false;
           }
       });
        System.out.println("Second years ="+secondYears);
        
        
        
//       for(String st:firstYears) System.out.println(st);
    }
    public interface Printer{
        void print(String s);
    }
    public interface Getter{
        double get(Object o);
    }
    public interface Comparator{
        int compare(Object o);
    }


}
