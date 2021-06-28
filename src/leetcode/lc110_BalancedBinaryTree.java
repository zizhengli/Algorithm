package leetcode;

import utils.TreeNode;
import utils.TreeUtils;

/**
 * Created by zizhengli on 1/25/17.
 */
public class lc110_BalancedBinaryTree {

    // Use a function to return the height of each level subtree and determine if the tree is balanced at each level
    public static boolean isBalanced(TreeNode root) {
        if(getHeight(root) == -1) {
            return false;
        } else {
            return true;
        }
    }

    private static int getHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = getHeight(root.left);
        if(left == -1) {
            return -1;
        }

        int right = getHeight(root.right);
        if(right == -1) {
            return -1;
        }

        if(Math.abs(right - left) > 1) {
            return -1;
        }
        return Math.max(right, left) + 1;
    }

    public static void main(String[] args) {

        TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5);
        System.out.println(lc110_BalancedBinaryTree.isBalanced(root));
    }
}
