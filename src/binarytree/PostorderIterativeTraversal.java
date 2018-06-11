package binarytree;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.Stack;

/**
 *
 */
public class PostorderIterativeTraversal {

    static void traverse(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stackTreeNode = new Stack<>();
        Stack<TreeNode> stackPostOrder = new Stack<>();
        stackTreeNode.push(root);
        TreeNode curr = null;
        while(!stackTreeNode.isEmpty()) {
            curr = stackTreeNode.pop();
            stackPostOrder.push(curr);
            if(curr.left != null) {
                stackTreeNode.push(curr.left);
            }
            if(curr.right != null) {
                stackTreeNode.push(curr.right);
            }
        }
        while (!stackPostOrder.isEmpty()) {
            System.out.print(stackPostOrder.pop().val + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7, 8);
        PostorderIterativeTraversal.traverse(root);
    }
}
