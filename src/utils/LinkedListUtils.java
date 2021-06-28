package utils;

/**
 *
 */
public class LinkedListUtils {

    public static LinkedNode buildLinkedList(Integer... value) {

        LinkedNode dummy = new LinkedNode(0);
        LinkedNode curr = dummy;

        for(Integer i : value) {
            LinkedNode node = new LinkedNode(i);
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

    public static LinkedNode bindLinkedList(LinkedNode l1, LinkedNode l2) {
        if(l1 == null) {
            return l2;
        } else if(l2 == null) {
            return l1;
        }
        LinkedNode current = l1;
        while(current.next != null) {
            current = current.next;
        }
        current.next = l2;
        return l1;
    }

    public static String printLinkedList(LinkedNode head) {
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

    public static LinkedNode buildCyclicLinkedList(Integer... value) {

        LinkedNode dummy = new LinkedNode(0);
        LinkedNode curr = dummy;

        for(Integer i : value) {
            LinkedNode node = new LinkedNode(i);
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

    public static String printCyclicLinkedList(LinkedNode head) {
        StringBuilder result = new StringBuilder();
        LinkedNode curr = head;
        result.append(curr.val);
        result.append("->");
        curr = curr.next;
        while(curr != null && curr != head) {
            result.append(curr.val);
            result.append("->");
            curr = curr.next;
        }
        result.append("head");
        return result.toString();
    }
}
