package binarytree;

import utils.TreeNode;
import utils.TreeUtils;

/*
* Write a program which takes as input an integer and a binary tree with integer node
weights, and checks if there exists a leaf whose path weight equals the given integer.
*
* */
public class FindRootToLeafPathWithSum {

    public static boolean checkRootToLeafPath(TreeNode root, int sum) {
        return checkRootToLeafPathHelper(root, 0, sum);
    }

    private static boolean checkRootToLeafPathHelper(TreeNode node, int currentSum, int sum) {
        if(node == null) {
            return false;
        }
        currentSum += node.val;
        if(node.left == null && node.right == null) {
            return currentSum == sum;
        }
        return checkRootToLeafPathHelper(node.left, currentSum, sum)
                || checkRootToLeafPathHelper(node.right, currentSum, sum);
    }

    public static void main(String[] args) {

        TreeNode root1 = TreeUtils.buildTree(314, 6, 6, 271, 561, 2, 271, 28, 0, null, 3, null, 1, null,
                28, null, null, null, null, 17, null, 401, 257);
        //Assert.assertTrue(checkRootToLeafPath(root1, 591));
    }
}
