package binarytree;

import utils.TreeNode;
import utils.TreeUtils;
import static org.junit.Assert.*;

/*
* Variant: Write a program that returns the size of the largest subtree that is complete.
* Leetcode-333 https://leetcode.com/problems/largest-bst-subtree/description/
*
* */

public class CheckHeightBalanced {

    static class Result {
        boolean isBalanced;
        int height;

        Result(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean checkHeightBalanced(TreeNode root) {
        if(root == null) {
            return false;
        }
        Result res = checkHeightBalancedHelper(root);
        return res.isBalanced;
    }

    private static Result checkHeightBalancedHelper(TreeNode node) {
        if(node == null) {
            Result res = new Result(true, 0);
            return res;
        }
        Result left = checkHeightBalancedHelper(node.left);
        Result right = checkHeightBalancedHelper(node.right);
        if(!left.isBalanced) {
            return left;
        }
        if(!right.isBalanced) {
            return right;
        }
        boolean isBalanced = left.isBalanced
                                && right.isBalanced
                                && Math.abs(left.height - right.height) <= 1 ? true : false;
        int height = Math.max(left.height, right.height) + 1;
        return new Result(isBalanced, height);
    }

    public static void main(String[] args) {
        TreeNode root1 = TreeUtils.buildTree(3,9,20,1,2,15,7,5,6,7,8,9,9,null,null,9,0);
        assertTrue(CheckHeightBalanced.checkHeightBalanced(root1));

        TreeNode root2 = TreeUtils.buildTree(3,9,20,1,2,15,7,5,6,null,null,9,9,null,null,9,0);
        assertFalse(CheckHeightBalanced.checkHeightBalanced(root2));
    }
}
