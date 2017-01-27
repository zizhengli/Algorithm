package tree;

/**
 * Created by zizhengli on 1/25/17.
 */

import utils.TreeNode;
import utils.TreeUtils;

/**
 * 给定彼此独立的两棵树头节点分别为t1和t2,判断t1中是否有与t2树值 的拓扑结构完全相同的子树
 * */
public class CheckIdenticalTreeInAnother {

    // O(t1 * t2)
    public static boolean checkIdenticalTree(TreeNode t1, TreeNode t2) {
        // Using Morris Traversal to traverse t1
        TreeNode curr1 = t1;
        TreeNode curr2 = null;

        while(curr1 != null) {
            curr2 = curr1.left;
            if(curr2 != null) {
                while(curr2.right != null && curr2.right != curr1) {
                    curr2 = curr2.right;
                }
                if(curr2.right == null) {
                    curr2.right = curr1;
                    curr1 = curr1.left;
                    continue;
                } else {
                    curr2.right = null;
                }
            }
            System.out.println(curr1.val);
            if(checkTwoTrees(curr1, t2)) {
                return true;
            }
            curr1 = curr1.right;
        }
        return false;
    }
    private static boolean checkTwoTrees(TreeNode t1, TreeNode t2) {
        if((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
            return false;
        } else if (t1 == null && t2 == null) {
            return true;
        }

        return t1.val == t2.val && checkTwoTrees(t1.left, t2.left) && checkTwoTrees(t1.right, t2.right);
    }

    // Solution2 : Serialize two trees and check if string2 is substring of string1 using KMP O(t1 + t2).
    public static boolean checkIdenticalTree2(TreeNode t1, TreeNode t2) {

        String s1 = serializeTree(t1);
        String s2 = serializeTree(t2);

        // KMP

        return false;
    }

    private static String serializeTree(TreeNode root) {
        if(root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        TreeNode curr1 = root;
        TreeNode curr2 = null;

        while(curr1 != null) {
            curr2 = curr1.left;
            if(curr2 != null) {
                while(curr2.right != null && curr2.right != curr1) {
                    curr2 = curr2.right;
                }
                if(curr2 == null) {
                    curr2.right = curr1;
                    curr1 = curr1.left;
                    continue;
                } else {
                    curr2.right = null;
                }
            }
            result.append(curr1.val);
            curr1 = curr1.right;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtils.buildTree("1", "2", "3", "4", "5", "6", "7");
        TreeNode t2 = TreeUtils.buildTree("3", "6", "7");
        System.out.println(CheckIdenticalTreeInAnother.checkIdenticalTree(t1, t2));
    }
}
