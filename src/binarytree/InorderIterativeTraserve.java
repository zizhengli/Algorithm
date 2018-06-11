package binarytree;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.Stack;

/**
 *
 */
public class InorderIterativeTraserve {

    static void traverse(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                System.out.print(curr.val + " ");
                curr = curr.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7, 8);
        InorderIterativeTraserve.traverse(root);
    }
}
