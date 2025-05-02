/*
Basic Car class for Java Lab 3 on prog 2
 */
package week11_examples.lecture11_1;

import java.util.ArrayList;

/**
 *
 * @author ajb
 */
public class Car implements Cloneable{
    String make;
    String colour;
    int doors;
    int type; //0=hatch back, 1= sedan, /2=SUV
    double engineSize;
    public void copy(Car other){
        make=other.make;
        colour=other.colour;
        doors=other.doors;
        type=other.type;
        engineSize=other.engineSize;
    }

    
    public Car(){
    
    }
    
    public Car(String b,String c, int d, int t, double e){
        make=b;
        colour=c;
        doors=d;
        type=t;
        engineSize=e;
    }


   static ArrayList<Car> createCars(){
        ArrayList<Car> cars=new ArrayList<>();
        cars.add(new Car("BMW","Blue",2,1,2000));
        cars.add(new Car("BMW","Red",4,2,3000));
        cars.add(new Car("BMW","Blue",4,3,2000));
        cars.add(new Car("Audi","White",2,2,1600));
        cars.add(new Car("Audi","Blue",2, 1,2000));
        cars.add(new Car("Mercedes","Red",2, 2,2000));
        cars.add(new Car("Mercedes","White",4, 1,4000));
        cars.add(new Car("Mercedes","Black",4, 2,3000));
        return cars;
    }    
}
