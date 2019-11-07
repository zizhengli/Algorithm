package linkedlist;

import utils.LinkedListUtils;
import utils.LinkedNode;

/*
* 给定有序环形单链表(最后指回头部)和一个新节点N,插入N并保证继续有序
* */
public class InsertNodeInCyclicLinkedList {

    static LinkedNode insertNode(LinkedNode head, int value) {

        LinkedNode curr = head.next;
        LinkedNode prev = head;
        while(curr != head) {
            if(prev.val <= value && curr.val >= value) {
                break;
            }
            curr = curr.next;
            prev = prev.next;
        }
        LinkedNode node = new LinkedNode(value);
        prev.next = node;
        node.next = curr;
        return head;
    }

    static LinkedNode insertNode2(LinkedNode head, int value) {
        if(head == null) {
            return head;
        }
        LinkedNode p1 = head, p2 = head;
        LinkedNode node = new LinkedNode(value);
        if(value < p1.val) {
            while(p2.next != null && p2.next != head) {
                p2 = p2.next;
            }
            if(p2.next == null) {
                p2.next = node;
                node.next = p1;
            } else {
                node.next = p1;
                p2.next = node;
                head = node;
            }
            return head;
        }

        p1 = p1.next;
        while(p1 != null && p1.val < value && p1 != head) {
            p1 = p1.next;
            p2 = p2.next;
        }

        if(p1 == null) {
            p2.next = node;
            node.next = p2;
        } else  {
            p2.next = node;
            node.next = p1;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedNode head = LinkedListUtils.buildCyclicLinkedList(1, 2, 3, 4, 6);
        head = insertNode2(head, 5);
        System.out.println(LinkedListUtils.printCyclicLinkedList(head));

        LinkedNode head2 = LinkedListUtils.buildCyclicLinkedList(1);
        head2 = insertNode2(head2, 5);
        System.out.println(LinkedListUtils.printCyclicLinkedList(head2));

        LinkedNode head3 = LinkedListUtils.buildCyclicLinkedList(1);
        head3 = insertNode2(head3, 0);
        System.out.println(LinkedListUtils.printCyclicLinkedList(head3));

        LinkedNode head4 = LinkedListUtils.buildCyclicLinkedList(1, 2, 3, 4, 6);
        head4 = insertNode2(head4, 0);
        System.out.println(LinkedListUtils.printCyclicLinkedList(head4));
    }
}
