package week10_examples.lecture10_1;
// Simple iterator example with inner class
import java.util.Iterator;

public class IntRange implements Iterable<Integer> {
    private final int start;
    private final int end;

    public IntRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {
        private int current = start;

        @Override
        public boolean hasNext() {
            return current < end;
        }

        @Override
        public Integer next() {
            return current++;
        }
    }

    public static void main(String[] args) {
        IntRange range = new IntRange(1, 5);
        for(Integer x: range)
            System.out.println(x);
    }
}