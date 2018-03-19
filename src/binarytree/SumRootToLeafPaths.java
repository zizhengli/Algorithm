package binarytree;

import utils.TreeNode;
import utils.TreeUtils;

/**
 * Created by lim20 on 3/17/2018.
 */
public class SumRootToLeafPaths {

    public static int sumRootToLeaf(TreeNode root) {
        return sumRootToLeafHelper(root, 0);
    }

    private static int sumRootToLeafHelper(TreeNode node, int partialSum) {
        if(node == null) {
            return 0;
        }
        partialSum = partialSum * 2 + node.val;
        if(node.left == null && node.right == null) {
            return partialSum;
        }
        return sumRootToLeafHelper(node.left, partialSum) + sumRootToLeafHelper(node.right, partialSum);
    }

    public static void main(String[] args) {
        TreeNode root1 = TreeUtils.buildTree(1,0,0,0,1,1,1);
        int sum = SumRootToLeafPaths.sumRootToLeaf(root1);
        System.out.println(Integer.toBinaryString(sum));
    }
}
