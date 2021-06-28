package utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zizhengli on 1/24/17.
 */
public class TreeUtils {

    public static TreeNode buildTree(Integer... nodes) {
        if(nodes == null || nodes.length == 0) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList();
        int index = 0;
        if(nodes[index].equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodes[index]));
        queue.add(root);
        //index++;
        while(!queue.isEmpty() && index < nodes.length) {
            TreeNode curr = queue.poll();
            if(curr != null) {
                // Add left child
                index++;
                if(index < nodes.length) {
                    if(nodes[index] == null) {
                        curr.left = null;
                    } else {
                        curr.left = new TreeNode(nodes[index]);
                    }
                    queue.add(curr.left);
                }
                // Add right child
                index++;
                if(index < nodes.length) {
                    if(nodes[index] == null) {
                        curr.right = null;
                    } else {
                        curr.right = new TreeNode(nodes[index]);
                    }
                    queue.add(curr.right);
                }
            }
        }
        return root;
    }

    public static boolean compareTrees(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }
        if(node1.val != node2.val) {
            return false;
        }
        return compareTrees(node1.left, node2.left) && compareTrees(node1.right, node2.right);
    }

    public static TreeNode getTreeNodeByValue(TreeNode root, int target) {

        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;

        while(!stack.isEmpty() || curr != null) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if(curr.val == target) {
                    return curr;
                }
                curr = curr.right;
            }
        }
        return null;
    }

    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    private static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
}
