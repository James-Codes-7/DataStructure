package queue;

import queue.Queue;

public class LinkedList<T> implements Queue<T> {

    public void offer(T value) {
        add(value);
    }
    public T poll() {
        if (head == null) {
            size=0;
            return null;
        }
        if (head.next == null) {
            temp = head;
            head = null;
            return temp.value;
        }
        temp = head;
        prev = temp;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        tail=prev;
        size--;
        return temp.value;
    }

    public T peak() {
        if(head==null)return null;
        return tail.value;
    }

    private class Node {
        T value;
        Node next;

        public Node(T value) {
            this.next = null;
            this.value = value;
        }
    }

    private int size = 0;

    private Node head, tail, temp, newNode, prev;

    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
        temp = null;
        newNode = null;
        prev = null;
    }

    public void add(T value) {
        newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T get(int pos) {
        if (pos < 1 || pos > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        temp = head;
        int i = 0;
        while (i < pos - 1) {
            temp = temp.next;
            i++;
        }
        return temp.value;
    }

    public int size() {
        return size;
    }

    public void remove() {
        if (head == null || head.next == null) {
            head = null;
            tail = null;
            size=0;
            return;
        }
        temp = head;
        prev = temp;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        tail=prev;
        size--;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(int pos, T val) {
        temp = head;
        prev = temp;
        int i = 0;
        Node node = new Node(val);
        while (i < pos - 1) {
            prev = temp;
            temp = temp.next;
            i++;
        }
        size++;
        if (i == 0) {
            node.next = head;
            head = node;
            return;
        }
        node.next = temp;
        prev.next = node;
    }

    public void display() {
        temp = head;
        System.out.print("[");
        while (temp != null) {
            System.out.print(temp.value + ",");
            temp = temp.next;
        }
        System.out.print("]");

    }
}
