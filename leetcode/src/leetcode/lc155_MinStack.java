package leetcode;

import java.util.Stack;

/**
 * Created by zizhengli on 1/30/17.
 */
public class lc155_MinStack {

    /*
    * Using two stack, one to track normal data, another to track the min data.
    * */
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    /** initialize your data structure here. */
    public lc155_MinStack() {
        stack = new Stack();
        minStack = new Stack();
    }

    public void push(int x) {
        stack.push(x);
        if(minStack.size() == 0) {
            minStack.push(x);
            return;
        }
        int min = minStack.peek();
        if(min > x) {
            minStack.push(x);
        } else {
            minStack.push(min);
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
