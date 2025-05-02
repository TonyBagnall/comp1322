
package week11_examples.lecture11_1;

/**
 *
 * @author ajb
 */
public class Pair<E,K extends Comparable> {
    E value;
    K key;
    void setKey(K x){
        key=x;
    }
    void setValue(E e){
        value =e;
    }
    public static void main(String[] args) {
//        Pair<String,int[]> a=new Pair<>();
        Pair<String,Integer> a=new Pair<>();
//        a=new Pair<Double,Integer>();
        Pair<?,Integer> dd=new Pair<String,Integer>();
        Pair<? extends Number,Integer> d2=new Pair<Integer,Integer>();
        d2=new Pair<Double, Integer>();
  //       d2=new Pair<String, Integer>();
          
        
        
        Pair<int[],String> b=new Pair<>();
//        b.setKey(5);
        
        
        
        
    }
    
}
