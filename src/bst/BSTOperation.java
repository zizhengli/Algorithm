package bst;

import utils.TreeNode;
import utils.TreeUtils;

/**
 *
 */
public class BSTOperation {

    static TreeNode insertNode(TreeNode root, int key) {
        if(root == null) {
            return root;
        }
        TreeNode newNode = new TreeNode(key);
        TreeNode current = root;
        while(current != null) {
            if(key < current.val) {
                if(current.left == null) {
                    current.left = newNode;
                    return root;
                }
                current = current.left;
            } else if(key > current.val) {
                if(current.right == null) {
                    current.right = newNode;
                    return root;
                }
                current = current.right;
            } else {
                newNode.right = current.right;
                current.right = newNode;
                return root;
            }
        }
        return root;
    }

    static TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return root;
        }
        TreeNode current = root;
        TreeNode parent = null;

        while(current != null && current.val != key) {
            parent = current;
            if(current.val > key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        // Means the key has not been found so return root.
        if(current == null) {
            return root;
        }
        // If the node to delete has both child
        if(current.left != null && current.right != null) {
            TreeNode max = current.left;
            TreeNode maxP = current;
            while(max.right != null) {
                maxP = max;
                max = max.right;
            }
            // Swap value
            int temp = current.val;
            current.val = max.val;
            max.val = temp;
            parent = maxP;
            current = max;
        }
        TreeNode child = null;
        if(current.left != null) {
            child = current.left;
        } else if(current.right != null) {
            child = current.right;
        }
        // The root is the node to delete
        if(parent == null) {
            root = child;
        } else if(parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        return root;
    }

    public static void main(String[] args) {

        TreeNode root = TreeUtils.buildTree(7, 5, 9, 3, 6, 8, 10);
        root = deleteNode(root, 5);

        TreeNode test1 = TreeUtils.buildTree(7, 3, 9, null, 6, 8, 10);
        TreeNode test2 = TreeUtils.buildTree(7, 6, 9, 3, null, 8, 10);

        System.out.println(TreeUtils.compareTrees(root, test1) || TreeUtils.compareTrees(root, test2));

        TreeNode root1 = TreeUtils.buildTree(7, 5, 9, 3, 6, 8, 10);
        root1 = insertNode(root1, 1);
        TreeNode test3 = TreeUtils.buildTree(7, 5, 9, 3, 6, 8, 10, 1);
        System.out.println(TreeUtils.compareTrees(root1, test3));
        root1 = deleteNode(root1, 1);
        TreeNode test4 = TreeUtils.buildTree(7, 5, 9, 3, 6, 8, 10);
        System.out.println(TreeUtils.compareTrees(root1, test4));

        root1 = insertNode(root1, 5);
        TreeNode test5 = TreeUtils.buildTree(7, 5, 9, 3, 5, 8, 10, null, null, null, 6);
        System.out.println(TreeUtils.compareTrees(root1, test5));
    }
}
