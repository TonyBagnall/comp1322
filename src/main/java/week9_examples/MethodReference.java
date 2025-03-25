package week9_examples;

import java.util.function.*;

public class MethodReference {
    int foo=1;

    int getFoo(){
        return foo;
    }
    static String convert(Integer x){
        return "String+"+x;
    }

    public static void main(String[] args) {
        Function<Integer, String> f = MethodReference::convert;
        System.out.println("Convert = "+f.apply(22));
        MethodReference mtr = new MethodReference();
        Supplier<Integer> supp = mtr::getFoo;
        System.out.println(" Supplier = "+supp.get());

    }
}
