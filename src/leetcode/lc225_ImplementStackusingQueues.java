package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zizhengli on 1/30/17.
 */
public class lc225_ImplementStackusingQueues {

    private Queue<Integer> queue;
    private Queue<Integer> helper;
    /** Initialize your data structure here. */
    public lc225_ImplementStackusingQueues() {
        this.queue = new LinkedList();
        this.helper = new LinkedList();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while(queue.size() > 1) {
            helper.add(queue.poll());
        }
        int temp = queue.poll();
        //Swap the helper and queue
        Queue<Integer> tmp = helper;
        helper = queue;
        queue = tmp;
        return temp;
    }

    /** Get the top element. */
    public int top() {
        while(queue.size() > 1) {
            helper.add(queue.poll());
        }
        int temp = queue.peek();
        helper.add(queue.poll());
        //Swap the helper and queue
        Queue<Integer> tmp = helper;
        helper = queue;
        queue = tmp;
        return temp;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
