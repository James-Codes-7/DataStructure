package queue;

public interface Queue<T> {
    public void offer(T value);
    T poll();
    T peak();
    boolean isEmpty();
    int size();
}
