package linkedlist;

import utils.LinkedListUtils;
import utils.ListNode;

import java.util.HashSet;

/**
 * 给定一个无序单链表的头节点head,删除值重复的节点(保留一个)
 */
public class RemoveDuplicateNode {

    public static ListNode removeDuplicateNode(ListNode head) {

        HashSet<Integer> set = new HashSet();
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null) {
            if(set.contains(curr.val)) {
                // Remove current node
                prev.next = curr.next;
            } else {
                set.add(curr.val);
                prev = curr;
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = LinkedListUtils.buildLinkedList(3, 2, 3, 4, 3, 5, 3);
        head = removeDuplicateNode(head);
        System.out.println(LinkedListUtils.printLinkedList(head));
    }
}
