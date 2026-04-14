/*
 Code for Lab Sheet 3 demonstraing Functors vs Lambdas
 */
package week9_examples.lambda_examples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajb
 */
public class CarTest {
    static ArrayList selectFromList(List a, Selector s){
           ArrayList a2=new ArrayList();
           for(Object ob:a){
                    if(s.select(ob))
                            a2.add(ob);
            }
            return a2;
        }
 
    
    public static void main(String[] args) {
        ArrayList<Car> c = Car.createCars();
//1. Select all blue classes using an anonymous inner class
        Selector<Car> s = car -> car.colour=="Blue";
        ArrayList<Car> blueCars = selectFromList(c,s);
        System.out.println(" Blue cars = "+blueCars);

    }
    
}
