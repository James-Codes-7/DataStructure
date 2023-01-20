package queue;

import java.util.LinkedList;

public class HashSet<K> {

   private LinkedList<Node>[] buckets;

    private class Node {
        K val;

        public Node(K val) {
            this.val = val;
        }
    }

    private int initialCapacity;

    public HashSet() {
        initialCapacity = 16;
        bucketSet(initialCapacity);
    }

    private void bucketSet(int initialCapacity) {
        buckets = new LinkedList[initialCapacity];
        int i = 0;
        for (i = 0; i < initialCapacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int size = 0;

    public void add(K val) {
        int index = hashFuction(val);
        int dropIndex = dropIndexGet(index, val);
        if (dropIndex != -1) {
            Node node = new Node(val);
            buckets[index].add(node);
            size++;
        }
        float loadFactor = 0.75f;
        float threshold = (float) ((size * 1.0) / buckets.length);
        if (loadFactor < threshold) rehash();
    }

    public void disPlay() {
        for (LinkedList<Node> list : buckets) {
            for (Node node : list)
                System.out.println(node.val);
        }
    }

    private void rehash() {
        LinkedList<Node>[] oldBucket = buckets;
        initialCapacity *= 2;
        bucketSet(initialCapacity);
        for (LinkedList<Node> list : oldBucket) {
            for (Node node : list) {
                add(node.val);
            }
        }
    }

    private int dropIndexGet(int hashCode, K val) {
        LinkedList<Node> linkedList = buckets[hashCode];
        int count = 0;
        for (Node node : linkedList) {
            if (node.val.equals(val)) return -1;
            count++;
        }
        return count;
    }

    private int hashFuction(K val) {
        int hashCode = hashCode(val);
        return hashCode & (buckets.length - 1);
    }

    private int hashCode(K val) {
        String str = String.valueOf(val);
        byte[] array = str.getBytes();
        int i = 1, result = 0, size = str.length();
        for (byte a : array) {
            result += size - i != 0 ? a * Math.pow(31, size - i++) : a;
        }
        return result;
    }
}
