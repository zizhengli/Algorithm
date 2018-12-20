package linkedlist;

import utils.LinkedListUtils;
import utils.LinkedNode;

/**
 * 给定两个有序单链表的头节点h1和h2,请合并两个有序链表并返回大链表头节点
 */
public class MergeLinkedList {

    public static LinkedNode mergeLinkedList(LinkedNode head1, LinkedNode head2) {

        LinkedNode curr1 = head1;
        LinkedNode curr2 = head2;
        LinkedNode dummy = new LinkedNode(0);
        LinkedNode curr = dummy;

        while(curr1 != null && curr2 != null) {
            if(curr1.val < curr2.val) {
                curr.next = curr1;
                curr1 = curr1.next;
            } else {
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        curr.next = curr1 == null ? curr2 : curr1;
        return dummy.next;
    }

    public static void main(String[] args) {

        LinkedNode head1 = LinkedListUtils.buildLinkedList(2, 3, 4, 8);
        LinkedNode head2 = LinkedListUtils.buildLinkedList(1, 2, 8, 9);
        LinkedNode head = MergeLinkedList.mergeLinkedList(head1, head2);
        System.out.println(LinkedListUtils.printLinkedList(head));
    }
}
