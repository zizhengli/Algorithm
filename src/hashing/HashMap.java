package hashing;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> {

    private List<HashNode> buckets;
    private int capacity;
    private int size;

    private static final float LOAD_FACTOR = 0.75f;

    public HashMap() {
        this(10);
    }

    public HashMap(int initialCapacity) {
        capacity = initialCapacity;
        buckets = new ArrayList<>(this.capacity);
        for(int i = 0; i < capacity; i++) {
            buckets.add(null);
        }
        size = 0;
    }

    public void put(K key, V value) {
        add(key, value, buckets);
        size++;
        if(size >= capacity * LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        if(key == null) {
            return null;
        }
        int index = getBucketIndex(key);
        if(buckets.get(index) == null) {
            return null;
        }
        HashNode head = buckets.get(index);
        while(head != null) {
            if(head.key == key) {
                return (V)head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        if(key == null) {
            return null;
        }
        int index = getBucketIndex(key);
        HashNode head = buckets.get(index);
        HashNode node = null;
        if(head != null) {
            node = removeNodeFromChain(key, head);
        }
        size--;
        return node == null ? null : (V)node.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int getBucketIndex(K key) {
        int hashcode = key.hashCode();
        return hashcode % capacity;
    }

    private void resize() {
        capacity = capacity * 2;
        List<HashNode> temp = new ArrayList<>(capacity);
        for(int i = 0; i < capacity; i++) {
            temp.add(null);
        }
        for(HashNode head : buckets) {
            while(head != null) {
                add((K)head.key, (V)head.value, temp);
                head = head.next;
            }
        }
        buckets = temp;
        temp = null;
    }

    private void add(K key, V value, List<HashNode> buckets) {
        int index = getBucketIndex(key);
        //System.out.println(index + " " + key);
        if(buckets.get(index) != null) {
            addNodeToEndOfChain(new HashNode(key, value), buckets.get(index));
        } else {
            buckets.set(index, new HashNode(key, value));
        }
    }

    private void addNodeToEndOfChain(HashNode node, HashNode head) {
        if(node == null || head == null) {
            return;
        }
        HashNode prev = head;
        HashNode current = prev.next;

        while(current != null) {
            prev = current;
            current = current.next;
        }
        prev.next = node;
    }

    private HashNode removeNodeFromChain(K key, HashNode head) {
        if(key == null || head == null) {
            return null;
        }
        HashNode prev = head;
        HashNode current = head.next;

        while(current != null) {
            if(current.key == key) {
                prev.next = current.next;
                return current;
            } else {
                prev = current;
                current = current.next;
            }
        }
        return null;
    }

    private class HashNode<K, V> {

        K key;
        V value;
        HashNode next;

        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>(1);
        map.put("this",1);
        map.put("coder",2);
        map.put("this",4);
        map.put("hi",5);
        System.out.println(map.size());
        System.out.println(map.remove("this"));
        System.out.println(map.remove("this"));
        System.out.println(map.size());
        System.out.println(map.isEmpty());
    }
}
