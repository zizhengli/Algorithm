package binarytree;

import java.util.Stack;

import utils.TreeNode;
import utils.TreeUtils;

/**
 *
 */
public class TreeTraversalInOrder {

    static void iterativeTraverse(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(root.left != null) {
            stack.push(root.left);
            root = root.left;
        }
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if(node.right != null) {
                stack.push(node.right);
                node = node.right;
                while(node.left != null) {
                    stack.push(node.left);
                    node = node.left;
                }
            }
        }
    }

    static void iterativeTraverse2(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            // If condition is for traversing all nodes on left side
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                // When the curr node is null, means left side traversing reaches to the end then
                // pop a node and print the node
                curr = stack.pop();
                System.out.print(curr.val + " ");
                curr = curr.right;
            }
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
                    curr1 = curr1.left;
                    continue;
                } else {
                    curr2.right = null;
                }
            }
            System.out.print(curr1.val + " ");
            curr1 = curr1.right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7, 8, 9, null, 10, 11, null, 12);
        TreeTraversalInOrder.morrisTraverse(root);
        System.out.println();
        TreeTraversalInOrder.iterativeTraverse(root);
        System.out.println();
        TreeTraversalInOrder.iterativeTraverse2(root);
    }
}