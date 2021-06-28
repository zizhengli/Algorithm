package binarytree;

import java.util.Stack;

import utils.TreeNode;
import utils.TreeUtils;

/**
 *
 */
public class TreeTraversalPreOrder {

    static void iterativeTraverse(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(stack.size() > 0) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
    }

    static void morrisTraverse(TreeNode root) {
        if(root == null) {
            return;
        }
        TreeNode curr1 = root;
        TreeNode curr2 = null;

        while(curr1 != null) {
            curr2 = curr1.left;
            if(curr2 != null) {
                while(curr2.right != null && curr2.right != curr1) {
                    curr2 = curr2.right;
                }
                if(curr2.right == null) {
                    curr2.right = curr1;
                    System.out.print(curr1.val + " ");
                    curr1 = curr1.left;
                    continue;
                } else {
                    curr2.right = null;
                }
            }  else {
                System.out.print(curr1.val + " ");
            }
            curr1 = curr1.right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7, 8);
        TreeTraversalPreOrder.morrisTraverse(root);
        System.out.println();
        TreeTraversalPreOrder.iterativeTraverse(root);
    }
}
