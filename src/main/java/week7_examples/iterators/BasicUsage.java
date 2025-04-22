package week7_examples.iterators;

import java.util.ArrayList;
import java.util.Iterator;

public class BasicUsage {

    public static void main(String[] args) {
        System.out.println(" Hello");
        int[] ar = new int[10];
        for(int i=0;i<ar.length;i++)
            ar[i]=i;
        for(int x:ar){
            x=33;
        }
        ArrayList<Integer> arList = new ArrayList<>();
        for(int i=0;i<10;i++)
            arList.add(i);

        for(Integer x:arList)
            System.out.println(x);
        Iterator<Integer> iterator=arList.iterator();
        Iterator<Integer> iterator2=arList.iterator();
        while(iterator.hasNext())
            System.out.println("Next = "+iterator.next());

    }
}
