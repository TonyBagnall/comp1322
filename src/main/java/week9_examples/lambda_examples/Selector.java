package week9_examples.lambda_examples;

/**
 *
 * @author ajb
 */

/**
 * Functional interface for a Selector that applies a true/false condition
 * @param <T>
 */
public interface Selector<T> {
    boolean select(T o);
}
