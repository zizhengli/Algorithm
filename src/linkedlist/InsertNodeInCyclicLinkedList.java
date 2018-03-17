package linkedlist;

import utils.LinkedListUtils;
import utils.ListNode;

/*
* 给定有序环形单链表(最后指回头部)和一个新节点N,插入N并保证继续有序
* */
public class InsertNodeInCyclicLinkedList {

    public static ListNode insertNode(ListNode head, int value) {

        ListNode curr = head.next;
        ListNode prev = head;
        while(curr != head) {
            if(prev.val <= value && curr.val >= value) {
                break;
            }
            curr = curr.next;
            prev = prev.next;
        }
        ListNode node = new ListNode(value);
        prev.next = node;
        node.next = curr;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = LinkedListUtils.buildCyclicLinkedList(1);
        head = insertNode(head, 0);
        System.out.println(LinkedListUtils.printCyclicLinkedList(head));
    }
}
