package stack_queue;

import java.util.Stack;

/*
* 一个栈依次压入1、2、3、4、5,那么从栈顶到栈底分别为5、4、3、2、1。将这个栈 转置后,
* 从栈顶到栈底为1、2、3、4、5,也就是实现栈中元素的逆序,但是只能用 递归函数来实现,不能用其他数据结构。
*
* 5 getBottomElementInStack return 1
*   4  getBottomElementInStack return 2
*     3  getBottomElementInStack return 3
*       2  getBottomElementInStack return 4
*         1 getBottomElementInStack return 5
*         push(5)
*       push(2)
*     push(3)
*   push(4)
* push(5)
* */
public class ReverseStackUsingRecursive {

    public static void reverseStack(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int value = getBottomElementInStack(stack);
        reverseStack(stack);
        stack.push(value);
    }

    private static int getBottomElementInStack(Stack<Integer> stack) {
        int result = stack.pop();
        if(stack.isEmpty()) {
            return result;
        } else {
            int bottom = getBottomElementInStack(stack);
            stack.push(result);
            return bottom;
        }
    }

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        ReverseStackUsingRecursive.reverseStack(stack);
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
