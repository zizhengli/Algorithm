package linkedlist;

import utils.LinkedListUtils;
import utils.ListNode;

/**
 * 链表长度为N,如N为偶数,前N/2个节点为左区,后N/2个节点为右区;如N为奇数,
 * 前N/2个节点为左区,后N/2+1个节点为右区。调整链表成L1->R1->L2->R2->...的形式
 */
public class RotateLinkedList {

    public static ListNode rotateLinkedList(ListNode head) {

        int length = 0;
        ListNode curr = head;
        while(curr != null) {
            length++;
            curr = curr.next;
        }
        if(length <= 3) {
            return head;
        }

        int rStart= length % 2 == 0 ? length - length / 2 + 1 : length - length / 2;
        int index = 1;
        ListNode prev = null;
        curr = head;
        while(index < rStart) {
            prev = curr;
            curr = curr.next;
            index++;
        }
        prev.next = null;
        ListNode rHead = curr;
        ListNode lHead = head;
        ListNode dummy = new ListNode(0);
        curr = dummy;
        int count = 1;
        while(lHead != null && rHead != null) {
            if(count % 2 != 0) {
                curr.next = lHead;
                lHead = lHead.next;
            } else {
                curr.next = rHead;
                rHead = rHead.next;
            }
            count++;
            curr = curr.next;
        }
        if(rHead != null) {
            curr.next = rHead;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

        ListNode head = LinkedListUtils.buildLinkedList(1, 2, 3);
        head = rotateLinkedList(head);
        System.out.println(LinkedListUtils.printLinkedList(head));
    }
}
