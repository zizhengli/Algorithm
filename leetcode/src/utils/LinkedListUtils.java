package utils;

/**
 * Created by zizhengli on 1/31/17.
 */
public class LinkedListUtils {

    public static ListNode buildLinkedList(Integer... value) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for(Integer i : value) {
            ListNode node = new ListNode(i);
            curr.next = node;
            curr = curr.next;
        }
        return dummy.next;
    }

    public static DoubleListNode buildDoubleLinkedList(Integer... value) {

        DoubleListNode dummy = new DoubleListNode(0);
        DoubleListNode curr = dummy;
        DoubleListNode prev = null;

        for(Integer i : value) {
            DoubleListNode node = new DoubleListNode(i);
            curr.next = node;
            curr = curr.next;
            curr.prev = prev;
            prev = curr;
        }
        return dummy.next;
    }

    public static String printLinkedList(ListNode head) {
        StringBuilder result = new StringBuilder();
        while(head != null) {
            result.append(head.val);
            result.append("->");
            head = head.next;
            if(head == null) {
                result.append("null");
            }
        }
        return result.toString();
    }

    public static ListNode buildCyclicLinkedList(Integer... value) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for(Integer i : value) {
            ListNode node = new ListNode(i);
            curr.next = node;
            curr = curr.next;
        }
        curr.next = dummy.next;
        return dummy.next;
    }

    public static String printDoubleLinkedList(DoubleListNode head) {
        StringBuilder result = new StringBuilder();
        result.append("null<-");
        while(head != null) {
            result.append(head.val);
            result.append("->");
            head = head.next;
            if(head == null) {
                result.append("null");
            }
        }
        return result.toString();
    }

    public static String printCyclicLinkedList(ListNode head) {
        StringBuilder result = new StringBuilder();
        ListNode curr = head;
        while(curr != null) {
            result.append(curr.val);
            result.append("->");
            curr = curr.next;
            if(curr == head) {
                result.append("head");
                break;
            }
        }
        return result.toString();
    }
}
