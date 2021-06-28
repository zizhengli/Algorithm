package leetcode;

import utils.TreeNode;

public class lc333_LargestBSTSubtree {

    static class Result {
        int size;
        boolean isBST;
        int minValue;
        int maxValue;
        Result(int size, boolean isBST, int minValue, int maxValue) {
            this.size = size;
            this.isBST = isBST;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }
    static int maxSize = 0;
    public static int largestBSTSubtree(TreeNode root) {
        if(root == null) {
            return maxSize;
        }
        Result res = largestBSTSubtreeHelper(root);
        return maxSize;
    }

    private static Result largestBSTSubtreeHelper(TreeNode node) {
        if(node == null) {
            Result res = new Result(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
            return res;
        }
        Result left = largestBSTSubtreeHelper(node.left);
        Result right = largestBSTSubtreeHelper(node.right);
        if(!left.isBST) {
            return left;
        }
        if(!right.isBST) {
            return right;
        }
        boolean isBST = node.val > left.maxValue && node.val < right.minValue;
        if(isBST) {
            maxSize = Math.max(maxSize, left.size + right.size + 1);
            return new Result(left.size + right.size + 1, isBST,
                    left.minValue == Integer.MAX_VALUE ? node.val : left.minValue,
                    right.maxValue == Integer.MIN_VALUE ? node.val : right.maxValue);
        } else {
            return new Result(0, false, 0, 0);
        }
    }
}
