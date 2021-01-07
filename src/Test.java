import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javafx.util.Pair;
import utils.LinkedListUtils;
import utils.LinkedNode;
import utils.LinkedNodeWithChild;
import utils.TreeNode;
import utils.TreeUtils;

/**
 * Created by lim20 on 11/12/2018.
 */
public class Test {

    static LinkedNodeWithChild flattenLinkedList(LinkedNodeWithChild head) {
        if(head == null) {
            return head;
        }
        LinkedNodeWithChild curr = head;
        LinkedNodeWithChild tail = getTail(head);

        while(curr != null) {
            if(curr.child != null) {
                tail.next = curr.child;
                //curr = curr.child;
                tail = getTail(curr.child);
            }
            curr = curr.next;
        }
        return head;
    }

    static LinkedNodeWithChild getTail(LinkedNodeWithChild node) {
        if(node == null) {
            return node;
        }
        LinkedNodeWithChild curr = node;
        while(curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }

    static public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if(nums == null || nums.length == 0) {
            return result;
        }

        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        int x = 0;
        // Find left boundary
        while(low <= high) {
            mid = low + (high - low) / 2;
            if(nums[mid] == target) {
                x = mid;
            } else if(nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if(nums[x] != target) {
            return result;
        } else {
            result[0] = x;
        }
        // Find right boundary
        low = 0;
        high = nums.length - 1;
        while(low < high) {
            mid = low + (high - low) / 2 + 1;
            if(nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        result[1] = high;
        return result;
    }



    public static void main(String[] args) {

        LinkedNodeWithChild head = new LinkedNodeWithChild(1);
        LinkedNodeWithChild node1 = new LinkedNodeWithChild(2);
        LinkedNodeWithChild node2 = new LinkedNodeWithChild(3);
        LinkedNodeWithChild node3 = new LinkedNodeWithChild(4);
        LinkedNodeWithChild node4 = new LinkedNodeWithChild(5);
        LinkedNodeWithChild node5 = new LinkedNodeWithChild(6);
        LinkedNodeWithChild node6 = new LinkedNodeWithChild(7);
        LinkedNodeWithChild node7 = new LinkedNodeWithChild(8);
        LinkedNodeWithChild node8 = new LinkedNodeWithChild(9);
        LinkedNodeWithChild node9 = new LinkedNodeWithChild(10);


        head.next = node1;
        node1.child = node5;
        node1.next = node2;
        node2.next = node3;
        node3.child = node6;
        node3.next = node4;
        node4.next = null;

        node5.next = node7;
        node7.child = node9;
        node7.next = node8;

        head = flattenLinkedList(head);

        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("=======");

        int[] array = {5,7,7,8,8,10};

        //System.out.println(searchRange(array, 8));



        System.out.println(3.1d / 3);
    }
}
