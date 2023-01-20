package arraylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;

public class ArrayList<T> implements Iterable<T> {

    public T[] data;
    public  int initialCapacity = 10;
    public  int capacity;
    public  int index = 0;

    public ArrayList() {
        capacity = initialCapacity;
        data = (T[]) new Object[capacity];
    }

    public void add(T val) {

        if (index == capacity) {
            capacity *= 2;
            resize(capacity);
        }
        data[index++] = val;
    }

    public void remove() {
        if (index <= 0) return;
        index--;
        if (index < capacity / 2) {
            capacity /= 2;
            resize(capacity);
        }
    }

    public void add(int pos, T value) {
        if (pos < 1 || pos > index) return;
        int i = 0;
        for (i = index; i >= pos; i--) {
            data[i] = data[i - 1];
        }
        data[pos - 1] = value;
        index++;
    }

    public T get(int pos) {
        if (pos < 1 || pos > index - 1) return null;
        return data[pos-1];
    }

    public void set(int pos, T value) {
        if (pos < 0 || pos >= index) return;
        data[pos] = value;
    }
    public int size() {
        return index;
    }
    private void resize(int capacity) {

        data = Arrays.copyOf(data, capacity);
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i=0;
            public boolean hasNext() {
                return i==index;
            }
            public T next() {
                return data[i++];
            }
        };
    }
}
