package week10_examples.lecture10_1;

public class EllipsisExamples {
    public static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }
    public static void printAll(String... words) {
        for (String word : words) {
            System.out.println(word);
        }
    }
    public static void greet(String greeting, String... names) {
        for (String name : names) {
            System.out.println(greeting + ", " + name);
        }
    }

    // Usage
    public static void main(String[] args) {
        // Usage
        System.out.println(sum(1, 2, 3));          // Output: 6
        System.out.println(sum(10, 20));           // Output: 30
        System.out.println(sum());                 // Output: 0
        printAll("49", "Games", "Unbeaten", "Is","The","Record");
        greet("Hello", "Alice", "Bob", "Charlie");
    }
}
