package utils;

import java.util.Objects;

public class Pair<T> implements Cloneable {
    public T elementOne;
    public T elementTwo;

    public Pair(T elementOne, T elementTwo) {
        this.elementOne = elementOne;
        this.elementTwo = elementTwo;
    }

    @Override
    public Pair<T> clone() {
        try {
            return (Pair<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        Pair<T> other = (Pair<T>) o;
        return elementOne.equals(other.elementOne) && elementTwo.equals(other.elementTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementOne, elementTwo);
    }
}
