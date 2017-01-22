import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zizhengli on 1/22/17.
 */
public class lc145_BinaryTreePostorderTraversal {
    
    public List<Integer> postorderTraversalWithTwoStack(TreeNode root) {
        List<Integer> result = new ArrayList();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack1 = new Stack();
        Stack<TreeNode> stack2 = new Stack();
        stack1.push(root);
        while(!stack1.isEmpty()) {
            TreeNode curr = stack1.pop();
            stack2.push(curr);
            if(curr.left != null) {
                stack1.push(curr.left);
            }
            if(curr.right != null) {
                stack1.push(curr.right);
            }
        }
        while(!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }

    /**
     * The idea is similar to inorder traversal(iterative), we need to reach down to most left leaf and go up to parent then
     * instead of print parent we need to go down to right child if there is one.
     * To do that, we need to find a way to go down to most left child and a way to switch to right child node, so there are
     * two conditions to create. We can use two pointer to mark the current node and previous node to determine in which
     * direction we should traverse.
     *
     * 1. If the current node has left child, its left child is not equal to previous node and neither its right child, then
     *    we push the current in stack.
     * 2. If the current node has right child and its right child is equal to previous, then we need to go down to its right
     *    child, and repeat the first step if necessary.
     * 3. If the two previous conditions are not true, it means we need to print current node and assign the current node to
     *    previous node.
     */
    public List<Integer> postorderTraversalWithOneStack(TreeNode root) {
        List<Integer> result = new ArrayList();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        TreeNode prev = root;
        stack.push(root);
        while(!stack.isEmpty()) {
            curr = stack.peek();
            if(curr.left != null && curr.left != prev && curr.right != prev) {
                stack.push(curr.left);
            } else if(curr.right != null && curr.right != prev) {
                stack.push(curr.right);
            } else {
                result.add(stack.pop().val);
                prev = curr;
            }
        }
        return result;
    }
}
