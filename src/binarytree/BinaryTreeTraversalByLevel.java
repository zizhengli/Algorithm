package binarytree;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.*;

/**
 * Created by lim20 on 10/8/2018.
 */
public class BinaryTreeTraversalByLevel {


    static List<List<Integer>> traversalByLevelIterative(TreeNode root) {
        if(root == null) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();

        Deque<TreeNode> queue= new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                list.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    static List<List<Integer>> traversalByLevel(TreeNode root) {
        if(root == null) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        traversalHelper(root, 0, result);
        return result;
    }

    private static void traversalHelper(TreeNode node, int level, List<List<Integer>> result) {
        if(node == null) {
            return;
        }
        if(level == result.size()) {
            result.add(level, new ArrayList<>());
        }
        result.get(level).add(node.val);
        traversalHelper(node.left, level + 1, result);
        traversalHelper(node.right, level + 1, result);
    }

    public static void main(String[] args) {

        TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>> result = traversalByLevel(root);
        for(List<Integer> list : result) {
            for(Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        List<List<Integer>> result2 = traversalByLevelIterative(root);
        for(List<Integer> list : result2) {
            for(Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
