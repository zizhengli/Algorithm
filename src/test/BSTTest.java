package test;

import utils.TreeNode;

/**
 * Created by zizhengli on 9/18/18.
 */
public class BSTTest {

    static TreeNode BSTLCA(TreeNode root, TreeNode node1, TreeNode node2) {
        if(root == null) {
            return root;
        } else if(root == node1) {
            return node1;
        } else if(root == node2) {
            return node2;
        }

        if((root.val > node1.val && root.val < node2.val) || (root.val < node1.val && root.val > node2.val)) {
            return root;
        } else if (root.val > node1.val && root.val > node2.val) {
            return BSTLCA(root.left, node1, node2);
        } else {
            return BSTLCA(root.right, node1, node2);
        }

    }
}
