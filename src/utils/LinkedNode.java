package utils;

/**
 * Created by zizhengli on 1/31/17.
 */
public class LinkedNode {
    public int val;
    public LinkedNode next;
    public LinkedNode(int x) {
        this.val = x;
        this.next = null;
    }

    public String toString() {
        return Integer.toString(val);
    }
}
