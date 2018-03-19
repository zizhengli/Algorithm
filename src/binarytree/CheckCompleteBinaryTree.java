package binarytree;

import utils.TreeNode;
import utils.TreeUtils;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zizhengli on 1/26/17.
 */
public class CheckCompleteBinaryTree {
    /**
     * The is idea that we need check the child node for each node :
     * 1. If current node has right child but no left child, return false.
     * 2. If current node is a leaf or has only left child then after this node each node should be a leaf.
     *
     * We traverse the tree by level and apply these conditions
     * */
    public static boolean isCompleteBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        TreeNode curr = root;
        queue.add(root);
        boolean noChild = false;
        while(!queue.isEmpty()) {
            curr = queue.poll();
            if(noChild) {
                if(curr.left != null || curr.right != null) {
                    return false;
                }
            }

            if(curr.left == null && curr.right != null) {
                return false;
            } else {
                if(curr.left != null && curr.right != null) {
                    queue.add(curr.left);
                    queue.add(curr.right);
                } else {
                    noChild = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Integer[] tree = {1, 2, 3, 4, null, 6, 7};
        TreeNode root = TreeUtils.buildTree(tree);
        System.out.println(CheckCompleteBinaryTree.isCompleteBinaryTree(root));

    }
}
