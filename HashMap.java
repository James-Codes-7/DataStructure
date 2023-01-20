package queue;


import java.util.LinkedList;

public final class HashMap<K, V> {

    private LinkedList<Node>[] buckets;
    private int size;
    private int initialCapacity;

    public HashMap() {
        initialCapacity = 16;
        setCapacity(initialCapacity);
        size = 0;
    }

    private class Node {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private void setCapacity(int initialCapacity) {
        buckets = new LinkedList[initialCapacity];
        for (int bucketIndex = 0; bucketIndex < initialCapacity; bucketIndex++)
            buckets[bucketIndex] = new LinkedList<>();
    }

    private int hashFuction(K key) {
        int hashCode = hashCode(key);
        return hashCode & (buckets.length - 1);
    }

    public void put(K key, V value) {
        int bucketIndex = hashFuction(key);
        int dropIndex = getDropIndex(key, bucketIndex);
        if (dropIndex == -1) {
            Node node = new Node(key, value);
            buckets[bucketIndex].add(node);
            size++;
        } else buckets[bucketIndex].get(dropIndex).value = value;
        float loadFactor = 0.75f;
        double threshold = (size * 1.0) / buckets.length;
        if (loadFactor < threshold) rehash();
    }

    public V get(K key) {
        int bucketindex = hashFuction(key);
        int dropIndex = getDropIndex(key, bucketindex);
        if (dropIndex != -1) {
            return buckets[bucketindex].get(dropIndex).value;
        }
        return null;
    }

    private void rehash() {
        LinkedList<Node>[] oldBucket = buckets;
        initialCapacity *= 2;
        setCapacity(initialCapacity);
        for (LinkedList<Node> list : oldBucket) {
            for (Node node : list) {
                put(node.key, node.value);
            }
        }
    }

    private int hashCode(K key) {
        String value = String.valueOf(key);
        int result = 0, size = value.length(), i = 1;
        byte[] byteValue = value.getBytes();
        for (byte ascii : byteValue) {
            result += size - i != 0 ? (ascii * (Math.pow(31, size - i++))) : ascii;
        }
        return result;
    }

    private int getDropIndex(K key, int bucketIndex) {
        int dropIndex = 0;
        for (Node node : buckets[bucketIndex]) {
            if (node.key.equals(key)) {
                return dropIndex;
            }
            dropIndex++;
        }
        return -1;
    }
}
