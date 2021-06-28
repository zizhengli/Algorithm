package leetcode;

import utils.TreeNode;
import utils.TreeUtils;

/**
 * Created by zizhengli on 1/27/17.
 */
public class lc99_RecoveryBinarySearchTree {

    /**
     * Inorder traversal the tree to find the first and second decrease order, using the previous variable to compare
     * with current variable, if a decrease order has been found, then assign the previous node to first and current
     * node to second, be careful the order the first should be assigned with previous node once and the second should
     * be assigned with last decrease order node.
     * */
    public static void recoverTree(TreeNode root) {

        TreeNode curr1 = root;
        TreeNode curr2 = null;
        TreeNode prev = null;
        TreeNode first = null;
        TreeNode second = null;

        while(curr1 != null) {
            curr2 = curr1.left;
            if(curr2 != null) {
                while(curr2.right != null && curr2.right == curr1) {
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
            if(prev != null && prev.val > curr1.val) {
                if(first == null) {
                    first = prev;
                }
                if(first != null) {
                    second = curr1;
                }
            }
            prev = curr1;
            curr1 = curr1.right;
        }
    }

    public static void main(String[] arg) {

        TreeNode root = TreeUtils.buildTree(4, 3, 2, 1, null, null, 5);
        lc99_RecoveryBinarySearchTree.recoverTree(root);
    }
}
