package leetcode;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zizhengli on 1/25/17.
 */
public class lc144_BinaryTreePreorderTraversal {


    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        TreeNode curr = null;

        while(!stack.isEmpty()) {
            curr = stack.pop();
            result.add(curr.val);
            if(curr.right != null) {
                stack.push(curr.right);
            }
            if(curr.left != null) {
                stack.push(curr.left);
            }
        }
        return result;
    }

    public static List<Integer> preorderTraversalMorrisTraversal(TreeNode root) {

        List<Integer> result = new ArrayList();
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
                    result.add(curr1.val); // Print, if the curr.right is null, means the first time we traverse the
                                            // current node.
                    curr1 = curr1.left;
                    continue;
                } else {
                    curr2.right = null;
                }
            } else {
                result.add(curr1.val); // Print left node
            }
            curr1 = curr1.right;
        }
        return result;
    }

    public static void main(String[] args) {

        Integer[] nodes = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeUtils.buildTree(nodes);

        List<Integer> result = lc144_BinaryTreePreorderTraversal.preorderTraversal(root);
        for(Integer i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
        List<Integer> resultMorris = lc144_BinaryTreePreorderTraversal.preorderTraversalMorrisTraversal(root);
        for(Integer i : resultMorris) {
            System.out.print(i + " ");
        }
    }
}
