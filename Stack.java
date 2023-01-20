package queue;

import arraylist.ArrayList;

import java.util.Arrays;

public class Stack<T> extends ArrayList<T> {


    public Stack() {
        capacity = initialCapacity;
        data = (T[]) new Object[capacity];
        index = 0;
    }

    public void push(T value) {
        if (index == capacity) {
            capacity *= 2;
            resize(capacity);
        }
        data[index++] = value;
    }

    private void resize(int capacity) {
        data = Arrays.copyOf(data, capacity);
    }

    public T peek() {
        if (index == 0) return null;
        return data[index - 1];
    }

    public T pop() {
        if (index < 1) throw new ArrayIndexOutOfBoundsException();
        if (index - 1 < capacity / 2) {
            capacity /= 2;
            resize(capacity);
        }
        return data[--index];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public int size() {
        return index;
    }

    public void add(T value) {
        super.add(value);
    }

    public void remove() {
        super.remove();
    }
}
