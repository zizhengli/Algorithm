package linkedlist;

import utils.DoubleListNode;
import utils.LinkedListUtils;
import utils.ListNode;

/**
 * 1. 分别实现反转单向链表和反转双向链表的函数
 * 2. 在单向链表上把第from个节点到第to个节点这一部分进行反转,from和to是整数
 */
public class ReverseLinkedList {

    public static ListNode reverseLinkedList(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode curr = head;
        ListNode next = null;
        ListNode prev = null;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static DoubleListNode reverseDoubleLinkedList(DoubleListNode head) {

        DoubleListNode curr = head;
        DoubleListNode prev = null;
        DoubleListNode next = null;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode reversePartLinkedList(ListNode head, int from, int to) {

        int length = 0;
        ListNode curr = head;
        while(curr != null) {
            length++;
            curr = curr.next;
        }
        if(from < 0 || to < 0 || from > length || to > length || to < from) {
            return head;
        }

        ListNode beforeFrom = new ListNode(0);
        beforeFrom.next = head;
        int index = 1;
        while(beforeFrom != null && index <= from - 1) {
            beforeFrom = beforeFrom.next;
            index++;
        }

        ListNode afterTo = head;
        index = 1;
        while(beforeFrom != null && index < to + 1) {
            afterTo = afterTo.next;
            index++;
        }

        // Reverse from and to
        curr = beforeFrom.next;
        ListNode end = beforeFrom.next;
        ListNode next = null;
        ListNode prev = null;
        for(int i = from; i <= to; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        beforeFrom.next = prev;
        end.next = afterTo;
        if(from == 1) {
            return beforeFrom.next;
        } else {
            return head;
        }
    }


    public static void main(String[] args) {

        ListNode head = LinkedListUtils.buildLinkedList(1, 2, 3, 4, 5);
        head = reverseLinkedList(head);
        System.out.println(LinkedListUtils.printLinkedList(head));

        DoubleListNode dHead = LinkedListUtils.buildDoubleLinkedList(1, 2, 3, 4, 5);
        dHead = reverseDoubleLinkedList(dHead);
        System.out.println(LinkedListUtils.printDoubleLinkedList(dHead));

        ListNode pHead = LinkedListUtils.buildLinkedList(1, 2, 3, 4, 5, 6);
        pHead = reversePartLinkedList(pHead, 1, 6);
        System.out.println(LinkedListUtils.printLinkedList(pHead));
    }
}