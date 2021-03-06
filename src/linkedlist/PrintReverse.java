package linkedlist;

import utils.LinkedListUtils;
import utils.LinkedNode;

public class PrintReverse {

    static void print(LinkedNode node) {
        if(node == null) {
            return;
        }
        print(node.next);
        System.out.println(node.val);
    }

    public static void main(String[] args) {

        LinkedNode head = LinkedListUtils.buildLinkedList(1, 2, 3, 4, 5);
        print(head);
    }
}
