package leetcode;

import utils.TreeNode;
import utils.TreeUtils;

/**
 * Created by zizhengli on 1/26/17.
 */
public class lc98_ValidateBinarySearchTree {

    public static boolean isValidBST(TreeNode root) {
        TreeNode curr1 = root;
        TreeNode curr2 = null;
        Integer prev = null;
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
            if(prev != null && prev >= curr1.val) {
                return false;
            }
            prev = curr1.val;
            curr1 = curr1.right;
        }
        return true;
    }

    public static void main(String[] args) {

        TreeNode root = TreeUtils.buildTree(2, 1, 1);
        System.out.println(lc98_ValidateBinarySearchTree.isValidBST(root));
    }
}
