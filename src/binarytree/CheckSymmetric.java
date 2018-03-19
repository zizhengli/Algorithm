package binarytree;

import utils.TreeNode;
import utils.TreeUtils;
import static org.junit.Assert.*;

public class CheckSymmetric {

    public static boolean checkSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return checkSymmetricHelper(root.left, root.right);
    }

    private static boolean checkSymmetricHelper(TreeNode left, TreeNode right) {
        if(left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }
        boolean isSymmetric = left.val == right.val ? true : false;
        return isSymmetric
                && checkSymmetricHelper(left.right, right.left)
                && checkSymmetricHelper(left.left, right.right);
    }

    public static void main(String[] args) {
        TreeNode root1 = TreeUtils.buildTree(1,2,2,3,4,4,3);
        assertTrue(checkSymmetric(root1));

        TreeNode root2 = TreeUtils.buildTree(1,2,2,3,4,3,4);
        assertFalse(checkSymmetric(root2));

        TreeNode root3 = TreeUtils.buildTree(1,2,2,3,4,4, null);
        assertFalse(checkSymmetric(root3));
    }
}
