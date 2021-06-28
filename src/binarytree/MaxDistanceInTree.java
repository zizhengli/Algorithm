package binarytree;

import utils.TreeNode;
import utils.TreeUtils;

/**
 * Created by zizhengli on 1/27/17.
 */
/**
 * 从二叉树的节点A出发,可以向上或者向下走,但沿途的节点只能经 过一次,当到达节点B时,路径上的节点数叫作A到B的距离
 * 给定一 棵二叉树的头节点head,求整棵树上节点间的最大距离
 */
public class MaxDistanceInTree {

    /**
     * Use recursion to compare the max distance from left to current node and the max distance from right to current
     * node, it could contain max distance tree in left sub tree or right sub tree, so we need to track this information
     * as well. Since we could construct a new max distance tree by recursion so we need to compare
     *
     * 1. maxFromLeft + maxFromRight + 1 : leftMax : rightMax -> get the max
     * 2. maxFromLeft : maxFromRight -> max + 1 return
     * */
    public static int getMaxDistanceInTree(TreeNode root) {

        int[] info = new int[2]; // info[0] : lMax, info[1] : maxFromLeft
        maxDistanceHelper(root, info);
        return info[0];
    }

    private static void maxDistanceHelper(TreeNode root, int[] info) {
        if(root == null) {
            info[0] = 0;
            info[1] = 0;
            return;
        }

        maxDistanceHelper(root.left, info);
        int lMax = info[0];
        int maxFromLeft = info[1];

        maxDistanceHelper(root.right, info);
        int rMax = info[0];
        int maxFromRight = info[1];

        int max = maxFromLeft + maxFromRight + 1;
        info[0] = Math.max(max, Math.max(rMax, lMax));
        info[1] = Math.max(maxFromLeft, maxFromRight) + 1;

        return;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.buildTree(1,2,3,4,5,6,7,8,null,null,null,null,9,null,null,10);
        System.out.println(MaxDistanceInTree.getMaxDistanceInTree(root));
    }
}
