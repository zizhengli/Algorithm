package src.leetcode;

import src.utils.TreeNode;
import src.utils.TreeUtils;

/**
 * Created by zizhengli on 1/28/17.
 */
public class lc222_CountCompleteBS {
    /**
     * Count the height of left sub tree which is height of the whole tree and the height
     * of left border of right sub tree, compare two heights. If the left height is equal to right height,
     * it means the left sub tree is a full BS and we count the number of node with 2 ^ left height - 1,
     * otherwise the right height is shorter than left height,
     * which means the right tree is full BS, we can count the number of node with 2 ^ right height - 1
     * */
    public static int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftHeight = getMostLeftTreeHeight(root.left);
        int rightHeight = getMostLeftTreeHeight(root.right);
        if(leftHeight == rightHeight) {
            return (1 << leftHeight) - 1 + countNodes(root.right) + 1;
        } else {
            return (1 << rightHeight) - 1 + countNodes(root.left) + 1;
        }
    }

    private static int getMostLeftTreeHeight(TreeNode node) {
        int res = 0;
        while(node != null) {
            res++;
            node = node.left;
        }
        return res;
    }

    public static void main(String[] args) {

        TreeNode root = TreeUtils.buildTree(1,2,3,4,5,6,7,8);
        System.out.println(lc222_CountCompleteBS.countNodes(root));
    }
}
