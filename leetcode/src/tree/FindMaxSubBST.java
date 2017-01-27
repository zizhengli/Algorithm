package tree;

/**
 * Created by zizhengli on 1/26/17.
 */
import utils.TreeNode;
import utils.TreeUtils;

/**
 * 给定一棵二叉树的头节点head,已知其中所有节点的值都不一样,找 到含有节点最多的搜索二叉子树,并返回这棵子树的头节点
 **/
public class FindMaxSubBST {

    /**
     * We need to use recursion to determine if each node has left sub BST and right sub BST, then check if the current
     * node build a bigger BST with his sub trees. To check if a tree is a BST, we need four information
     * 1. Root of sub BST
     * 2. Size of sub BST to compare which one is bigger
     * 3. The max value of left sub BST
     * 4. The min value of right sub BST
     * */
    public static TreeNode findMaxSubBST(TreeNode root) {
        int[] info = new int[3]; // info[0] : size, info[1] : max value, info[3] : min value
        return findMaxSubBSTHelper(root, info);
    }

    private static TreeNode findMaxSubBSTHelper(TreeNode root, int[] info) {
        if(root == null) {
            info[0] = 0;
            info[1] = Integer.MAX_VALUE;
            info[2] = Integer.MIN_VALUE;
            return null;
        }
        TreeNode leftBST = findMaxSubBSTHelper(root.left, info);
        int leftSize = info[0];
        int leftMin = info[1];
        int leftMax = info[2];

        TreeNode rightBST = findMaxSubBSTHelper(root.right, info);
        int rightSize = info[0];
        int rightMin = info[1];
        int rightMax = info[2];

        info[1] = Math.min(leftMin, root.val);
        info[2] = Math.max(rightMax, root.val);
        // If the current code can build a new BST with its children
        if(root.left == leftBST && root.right == rightBST && root.val > leftMax && root.val < rightMin) {
            info[0] = leftSize + rightSize + 1;
            return root;
        }

        info[0] = Math.max(leftSize, rightSize);
        return leftSize > rightSize ? leftBST : rightBST;
    }

    public static void main(String[] arg) {

        String[] tree = {"10", "7", "18", "10", "8", "2", "11", "2", "4", "null", "null", "1", "3", "null", "null", "1"};
        TreeNode root = TreeUtils.buildTree(tree);
        TreeUtils.printTree(root);
        TreeNode maxSubBST = FindMaxSubBST.findMaxSubBST(root);
    }
}
