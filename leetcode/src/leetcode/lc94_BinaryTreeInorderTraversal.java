package leetcode;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zizhengli on 11/24/16.
 */
public class lc94_BinaryTreeInorderTraversal {


    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;

        while(!stack.isEmpty() || curr != null) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                // if the program got here, it means no more left leaf
                curr = stack.pop();
                result.add(curr.val);
                // Assign the right child node to it parent node cause if its right node is null, the program will be back
                // in this else and stack will pop off the next node.
                curr = curr.right;
            }
        }
        return result;
    }

    /**
     * Morris Traversal, two conditions.
     * 1. If current node has left node and the most right node of his left node is pointing
     *    to null then point the right node to the current node. (The step is to recurse back to parent node)
     * 2. If current node does not have left node or he has left node and the most right node of his left node is pointing to
     *    the current node then, point the right node to null and move the current node to his right node.
     */
    public static List<Integer> inorderTraversalMorrisTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        TreeNode curr1 = root;
        TreeNode curr2 = null;
        while(curr1 != null) {
            curr2 = curr1.left;
            if(curr2 != null) { // condition 1
                while(curr2.right != null && curr2.right != curr1) {
                    curr2 = curr2.right;
                }
                if(curr2.right == null) {
                    curr2.right = curr1;
                    curr1 = curr1.left;
                    continue;
                } else {
                    curr2.right = null; // condition 2
                }
            }
            result.add(curr1.val); // condition 2
            curr1 = curr1.right;
        }
        return result;
    }

    public static void main(String[] args) {

        Integer[] nodes = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeUtils.buildTree(nodes);

        List<Integer> result1 = lc94_BinaryTreeInorderTraversal.inorderTraversal(root);
        for(Integer i : result1) {
            System.out.print(i + " ");
        }

        System.out.println();
        List<Integer> result2 = lc94_BinaryTreeInorderTraversal.inorderTraversalMorrisTraversal(root);
        for(Integer i : result2) {
            System.out.print(i + " ");
        }
    }
}
