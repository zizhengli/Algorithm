package binarytree;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.Stack;

/**
 *
 */
public class PreorderIterativeTraversal {

    static void traverse(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        stack.push(root);

        while(!stack.isEmpty()) {
            curr = stack.pop();
            System.out.print(curr.val + " ");
            if(curr.right != null) {
                stack.push(curr.right);
            }
            if(curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7, 8);
        PreorderIterativeTraversal.traverse(root);
    }
}
