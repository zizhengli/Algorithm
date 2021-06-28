package utils;

/**
 * Created by zizhengli on 1/31/17.
 */
public class DoubleListNode {

    public int val;
    public DoubleListNode prev;
    public DoubleListNode next;

    DoubleListNode(int x) {
        this.val = x;
        this.next = null;
        this.prev = null;
    }
}
