package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import utils.TreeNode;

public class lc662_MaximumWidthOfBinaryTree {

    private static int maxWidth;

    // BFS
    static int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Deque<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0, 0));
        int currentDepth = 0;
        int mostLeftNodePos = 0;
        int maxWidth = 0;
        while(!queue.isEmpty()) {
            Node node = queue.pollFirst();
            if(node.treeNode.left != null) {
                queue.add(new Node(node.treeNode.left, node.depth + 1, node.position * 2 + 1));
            }
            if(node.treeNode.right != null) {
                queue.add(new Node(node.treeNode.right, node.depth + 1, node.position * 2 + 2));
            }
            if(currentDepth != node.depth) {
                currentDepth = node.depth;
                mostLeftNodePos = node.position;
            }
            maxWidth = Math.max(maxWidth, node.position - mostLeftNodePos + 1);
        }
        return maxWidth;
    }

    static int widthOfBinaryTreeDFS(TreeNode root) {
        if(root == null) {
            return 0;
        }
        maxWidth = 0;
        List<Integer> leftPos = new ArrayList<>();
        dfsHelper(root, leftPos, 0, 0);
        return maxWidth;
    }

    private static void dfsHelper(TreeNode root, List<Integer> leftPos, int depth, int position) {
        if(root == null) {
            return;
        }
        if(depth == leftPos.size()) {
            leftPos.add(position);
        }
        maxWidth = Math.max(maxWidth, position - leftPos.get(depth) + 1);

        dfsHelper(root.left, leftPos, depth + 1, position * 2 + 1);
        dfsHelper(root.right, leftPos, depth + 1, position * 2 + 2);
    }

    private static class Node {
        TreeNode treeNode;
        int depth;
        int position;

        Node(TreeNode node, int depth, int position) {
            this.treeNode = node;
            this.depth = depth;
            this.position = position;
        }
    }

}
