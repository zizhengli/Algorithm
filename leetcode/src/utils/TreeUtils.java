package utils;

import apple.laf.JRSUIUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zizhengli on 1/24/17.
 */
public class TreeUtils {

    public static TreeNode buildTree(String... nodes) {
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
                    if(nodes[index].equals("null")) {
                        curr.left = null;
                    } else {
                        curr.left = new TreeNode(Integer.valueOf(nodes[index]));
                    }
                    queue.add(curr.left);
                }
                // Add right child
                index++;
                if(index < nodes.length) {
                    if(nodes[index].equals("null")) {
                        curr.right = null;
                    } else {
                        curr.right = new TreeNode(Integer.valueOf(nodes[index]));
                    }
                    queue.add(curr.right);
                }
            }
        }
        return root;
    }

    private static TreeNode buildTreeHelper(String[] nodes, int index) {
        if(index >= nodes.length) {
            return null;
        }
        TreeNode curr = null;
        if(nodes[index].equals("null")) {
            return curr;
        }

        curr = new TreeNode(Integer.valueOf(nodes[index]));
        curr.left = buildTreeHelper(nodes, index + 1);
        curr.right = buildTreeHelper(nodes, index + 2);
        return curr;
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
