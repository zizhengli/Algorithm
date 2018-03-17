package src.binarytree;

/**
 * Created by zizhengli on 1/26/17.
 */
public class InorderBinaryTreeSuccessor {

    public static TreeNode getSuccessor(TreeNode node) {
        if(node == null) {
            return null;
        }

        if(node.right != null) {
            TreeNode tmp = node.right;
            // get most left node
            while(tmp != null) {
                tmp = tmp.left;
            }
            return tmp;
        } else {
            TreeNode parent = node.parent;
            while(parent != null && parent.right == node) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        TreeNode (int value) {
            this.val = value;
        }
    }
}
