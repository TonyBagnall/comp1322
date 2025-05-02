package week11_examples.lecture11_1;

/**
 *
 * @author ajb
 */
public class Wrapper<X> {
    X stuff;
    
    
    public void setStuff(X o){
        stuff = o;
    }
    
    public static void main(String[] args) {
        Wrapper<String> w=new Wrapper<>();
        Wrapper<Integer> w2=new Wrapper<>();
        
        w.setStuff("AFC");
        w2.setStuff(49);
        w2.setStuff(49);
        
        
        
    }
}
