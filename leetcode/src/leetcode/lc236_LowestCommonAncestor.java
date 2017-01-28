package leetcode;

import utils.TreeNode;
import utils.TreeUtils;

/**
 * Created by zizhengli on 1/27/17.
 */
public class lc236_LowestCommonAncestor {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        } else if(root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7, 8);
        TreeNode q = TreeUtils.getTreeNodeByValue(root, 8);
        TreeNode p = TreeUtils.getTreeNodeByValue(root, 5);

        TreeNode ancestor = lc236_LowestCommonAncestor.lowestCommonAncestor(root, p, q);
        System.out.println(ancestor.val);
    }
}
