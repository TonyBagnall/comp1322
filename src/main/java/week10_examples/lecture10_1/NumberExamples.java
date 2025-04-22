package week10_examples.lecture10_1;

import java.util.ArrayList;
import java.util.List;

public class NumberExamples {
    public static double square(Number n) {
        double value = n.doubleValue();
        return value * value;
    }
    public static void main(String[] args) {
        // 1. Manual boxing
        int primitiveInt = 10;
        Integer boxedInt = Integer.valueOf(primitiveInt);  // manual boxing

        double primitiveDouble = 3.14;
        Double boxedDouble = Double.valueOf(primitiveDouble);  // manual boxing

        System.out.println("boxedInt = " + boxedInt + ", type = " + boxedInt.getClass().getSimpleName());
        System.out.println("boxedDouble = " + boxedDouble + ", type = " + boxedDouble.getClass().getSimpleName());

        // 2. Auto boxing
        Integer autoBoxedInt = 20;     // autoboxing int → Integer
        Double autoBoxedDouble = 6.28; // autoboxing double → Double
        System.out.println("autoBoxedInt = " + autoBoxedInt + ", type = " + autoBoxedInt.getClass().getSimpleName());
        System.out.println("autoBoxedDouble = " + autoBoxedDouble + ", type = " + autoBoxedDouble.getClass().getSimpleName());

        // 3. Polymorphic usage
        // Because Integer, Double, Float, etc., all extend Number, you can write code that works on the Number superclass:
        Number n1 = Integer.valueOf(5);
        Number n2 = Double.valueOf(7.5);
        Number n3 = Long.valueOf(100000L);

        System.out.println("n1.intValue() = " + n1.intValue());
        System.out.println("n2.doubleValue() = " + n2.doubleValue());
        System.out.println("n3.longValue() = " + n3.longValue());

        //4. Storing Mixed Numbers in a List
        List<Number> numbers = new ArrayList<>();
        numbers.add(42);          // int → Integer (autoboxed)
        numbers.add(3.1415);      // double → Double (autoboxed)
        numbers.add(10000000000L); // long → Long (autoboxed)

        for (Number num : numbers) {
            System.out.println("value = " + num + ", type = " + num.getClass().getSimpleName());
        }
        //5. Passing to a function
        System.out.println(square(numbers.get(0)));
    }

}


