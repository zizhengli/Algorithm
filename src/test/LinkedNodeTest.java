package test;

import utils.LinkedListUtils;
import utils.LinkedNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by zizhengli on 9/8/18.
 */
public class LinkedNodeTest {

    static LinkedNode reverseNode(LinkedNode head) {
        if (head == null) {
            return null;
        }

        LinkedNode prev = null;
        LinkedNode current = head;
        LinkedNode next = head;

        while(next != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    static LinkedNode reverseNode2(LinkedNode head, int first, int last) {
        if (head == null || first >= last) {
            return head;
        }
        LinkedNode dummy = new LinkedNode(-1);
        dummy.next = head;
        LinkedNode before = dummy;
        LinkedNode current = head;

        for(int i = 1; i < first; i++) {
            before = before.next;
            current = current.next;
        }

        LinkedNode subHead = current;
        LinkedNode next = current;
        LinkedNode prev = null;

        for(int i = first; i <= last && current != null; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        before.next = prev;
        subHead.next = current;
        return dummy.next;
    }

    static LinkedNode getFirstOverlap(LinkedNode l1, LinkedNode l2) {
        if(l1 == null || l2 == null) {
            return null;
        }
        int lengthL1 = length(l1);
        int lengthL2 = length(l2);

        if(lengthL1 > lengthL2) {
            l1 = advanceLinkedNode(l1, lengthL1 - lengthL2);
        } else {
            l2 = advanceLinkedNode(l2, lengthL2 - lengthL1);
        }

        while(l1 != null && l2 != null && l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }

    static int length(LinkedNode head) {
        if(head == null) {
            return 0;
        }
        int length = 0;
        LinkedNode current = head;
        while(current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    static LinkedNode advanceLinkedNode(LinkedNode head, int step) {
        if(head == null) {
            return null;
        }
        for(int i = 1; i <= step; i++) {
            head = head.next;
        }
        return head;
    }

    static LinkedNode deleteLastKthNode(LinkedNode head, int k) {
        if(head == null) {
            return null;
        }
        LinkedNode dummy = new LinkedNode(-1);
        dummy.next = head;
        LinkedNode current1 = head;

        while(k-- > 0) {
            current1 = current1.next;
        }

        LinkedNode current2 = dummy;
        while(current1 != null) {
            current1 = current1.next;
            current2 = current2.next;
        }
        //current2.next = current2.next == null ? null : current2.next.next;
        current2.next = current2.next.next;
        return dummy.next;
    }

    static LinkedNode removeDuplicateNode(LinkedNode head) {
        if(head == null) {
            return null;
        }
        LinkedNode dummy = new LinkedNode(-1);
        dummy.next = head;
        LinkedNode prev = dummy;
        LinkedNode current = head;

        while(current != null) {
            if(prev.val == current.val) {
                current = current.next;
                prev.next = current;
            } else {
                prev = prev.next;
                current = current.next;
            }
        }
        return dummy.next;
    }

    static LinkedNode evenOddMerge(LinkedNode head) {
        if(head == null) {
            return null;
        }
        // Odd head
        LinkedNode dummy1 = new LinkedNode(0);
        dummy1.next = head;
        // Even head
        LinkedNode dummy2 = new LinkedNode(0);
        dummy2.next = head.next;

        LinkedNode odd = head;
        LinkedNode even = head.next;

        while(odd != null && even != null) {

            odd.next = even.next;
            odd = odd.next;




        }

        return null;
    }

    static boolean isPalindromic(LinkedNode head) {
        if(head == null) {
            return false;
        }
        LinkedNode fast = head, slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverseNode(slow);
        while(slow != null) {
            if(head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    static LinkedNode listPivoting(LinkedNode head, int pivot) {
        if(head == null) {
            return null;
        }
        LinkedNode lessH = new LinkedNode(-1);
        LinkedNode less = lessH;
        LinkedNode pHead = new LinkedNode(-1);
        LinkedNode p = pHead;
        LinkedNode afterH = new LinkedNode(-1);
        LinkedNode after = afterH;

        while(head != null) {
            if(head.val < pivot) {
                less.next = head;
                less = head;
            } else if(head.val == pivot) {
                p.next = head;
                p = head;
            } else {
                after.next = head;
                after = head;
            }
            head = head.next;
        }
        less.next = pHead.next;
        p.next = afterH.next;
        after.next = null;
        return lessH.next;
    }

    static LinkedNode addTwoNumbers(LinkedNode l1, LinkedNode l2) {
        if(l1 == null) {
            return l2;
        } else if(l2 == null) {
            return l1;
        }
        LinkedNode dummy = new LinkedNode(-1);
        LinkedNode current = dummy;
        int carry = 0;

        while(l1 != null || l2 != null) {
            int sum = carry;
            if(l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            LinkedNode node = new LinkedNode(sum % 10);
            carry = sum / 10;
            current.next = node;
            current = current.next;
        }

        if(carry > 0) {
            LinkedNode node = new LinkedNode(carry);
            current.next = node;
        }
        return dummy.next;
    }

    private static Deque<Integer> stack = new LinkedList<>();
    private static Deque<MaxValue> max = new LinkedList<>();

    private static class MaxValue {
        int value;
        int occurrence;

        MaxValue(int value, int occurrence) {
            this.value = value;
            this.occurrence = occurrence;
        }
    }

    static void push(int value) {
        stack.push(value);
        if(max.isEmpty()) {
           max.push(new MaxValue(value, 1));
        } else {
            if(value > max.peek().value) {
                max.push(new MaxValue(value, 1));
            } else {
                max.peek().occurrence++;
            }
        }
    }

    static int pop() throws Exception {
        if(stack.isEmpty()) {
            throw new Exception();
        }

        max.peek().occurrence--;
        if(max.peek().occurrence == 0) {
            max.pop();
        }
        return stack.pop();
    }

    static int max() {
        return max.peek().value;
    }


    public static void main(String[] args) {
        //LinkedNode head = LinkedListUtils.buildLinkedList(1, 2, 3, 4, 5, 6);
        //System.out.println(LinkedListUtils.printLinkedList(reverseNode(head)));
        //System.out.println(LinkedListUtils.printLinkedList(reverseNode2(head, 2, 9)));

        /*LinkedNode l1 = LinkedListUtils.buildLinkedList(1, 2, 3, 4);
        LinkedNode l2 = LinkedListUtils.buildLinkedList(7, 8, 9);
        LinkedNode l3 = LinkedListUtils.buildLinkedList(5, 6, 10);
        l1 = LinkedListUtils.bindLinkedList(l1, l3);
        l2 = LinkedListUtils.bindLinkedList(l2, l3);
        System.out.println(getFirstOverlap(l1, l2));*/

        /*LinkedNode head = LinkedListUtils.buildLinkedList(1, 2, 3, 4, 5, 6);
        head = deleteLastKthNode(head, 4);
        System.out.println(LinkedListUtils.printCyclicLinkedList(head));*/

        /*LinkedNode head = LinkedListUtils.buildLinkedList(2, 2, 2, 3, 4, 4, 5, 6, 6, 6);
        head = removeDuplicateNode(head);
        System.out.println(LinkedListUtils.printCyclicLinkedList(head));*/

        /*LinkedNode head = LinkedListUtils.buildLinkedList(1, 2, 3, 4, 4, 3, 2, 1);
        System.out.println(isPalindromic(head));*/

        /*LinkedNode head = LinkedListUtils.buildLinkedList(3, 11, 2, 2, 11, 7, 5, 11, 11, 7);
        head = listPivoting(head, 7);
        System.out.println(LinkedListUtils.printCyclicLinkedList(head));*/

        /*LinkedNode l1 = LinkedListUtils.buildLinkedList(1, 2, 7, 9, 9);
        LinkedNode l2 = LinkedListUtils.buildLinkedList(1, 2, 3);
        LinkedNode res = addTwoNumbers(l1, l2);
        System.out.println(LinkedListUtils.printCyclicLinkedList(res));*/

        push(1);
        push(2);
        push(2);
        push(3);
        push(2);
        push(4);

        try {
            System.out.println(max() + " " + pop() + " " + max());
            System.out.println(max() + " " + pop() + " " + max());
            System.out.println(max() + " " + pop() + " " + max());
            System.out.println(max() + " " + pop() + " " + max());
            System.out.println(max() + " " + pop() + " " + max());
            System.out.println(max() + " " + pop() + " " + max());

        } catch(Exception e) {
            System.out.println(e);
        }




    }
}
