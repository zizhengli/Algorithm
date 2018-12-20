package test;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.*;

public class BinaryTreeTest {

    static void inorderTraversal(TreeNode root) {
        if(root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                System.out.print(curr.val + " ");
                curr = curr.right;
            }
        }
    }

    static void preorderTraversal(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        stack.push(curr);

        while(!stack.isEmpty()) {
            curr = stack.pop();
            System.out.print(curr.val + " ");
            if(curr.right != null) {
                stack.push(curr.right);
            }
            if(curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    static void postorderTraversal(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> track = new Stack<>();
        Stack<TreeNode> postorder = new Stack<>();
        track.push(root);
        TreeNode curr = null;

        while(!track.isEmpty()) {
            curr = track.pop();
            postorder.push(curr);
            if(curr.left != null) {
                track.push(curr.left);
            }
            if(curr.right != null) {
                track.push(curr.right);
            }
        }

        while (!postorder.isEmpty()) {
            System.out.print(postorder.pop().val + " ");
        }
    }

    static boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null) {
            return true;
        } else if(left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    static List<TreeNode> inroderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<TreeNode> result = new ArrayList<>();
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode next = null;
        while(curr != null) {
            // left subtree not done yet
            if(curr.parent == prev) {
                if(curr.left != null) {
                    next = curr.left;
                } else {
                    result.add(curr);
                    next = curr.right != null ? curr.right : curr.parent;
                }
            }
            // left subtree done
            else if(curr.left == prev) {
                result.add(curr);
                next = curr.right != null ? curr.right : curr.parent;
            } else {
                next = curr.parent;
            }
            prev = curr;
            curr = next;
        }
        return result;
    }

    static TreeNode reconstructBT(String inorder, String preorder) {
        return reconstructHelper(inorder, preorder, 0);
    }

    static TreeNode reconstructHelper(String inorder, String preorder, int preIdx) {
        if(inorder == null || inorder.length() == 0) {
            return null;
        }
        char c = preorder.charAt(preIdx);

        TreeNode node = new TreeNode(c - '0');
        int inIdx = inorder.indexOf(c);
        if(inIdx == -1) {
            return null;
        }
        //System.out.println(c + " " + inIdx + " " + inorder);
        node.left = reconstructHelper(inorder.substring(0, inIdx), preorder, preIdx + 1);
        node.right = reconstructHelper(inorder.substring(inIdx + 1, inorder.length()), preorder, preIdx + 1);
        return node;
    }

    static int preorderIdx = 0;
    static TreeNode reconstructPreorder(List<Integer> preorder) {
        return reconstructPreorderHelper(preorder);
    }

    static TreeNode reconstructPreorderHelper(List<Integer> preorder) {
        if(preorderIdx >= preorder.size()){
            return null;
        }
        Integer value = preorder.get(preorderIdx);
        preorderIdx++;
        if(value == null) {
            return null;
        }
        TreeNode left = reconstructPreorderHelper(preorder);
        TreeNode right = reconstructPreorderHelper(preorder);
        TreeNode node = new TreeNode(value);
        node.left = left;
        node.right = right;
        return node;
    }

    static List<TreeNode> exteriorBinaryTree(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<TreeNode> leftList = new ArrayList<>();
        preOrderTraversalFromLeft(root.left, leftList, true);
        List<TreeNode> rightList = new ArrayList<>();
        preOrderTraversalFromRight(root.right, rightList, true);
        List<TreeNode> result = new ArrayList<>();
        result.add(root);
        result.addAll(leftList);
        result.addAll(rightList);
        return result;
    }

    static void preOrderTraversalFromLeft(TreeNode root, List<TreeNode> leftList, boolean isBoundary) {
        if(root == null) {
            return;
        } else if(root.left == null && root.right == null) {
            leftList.add(root);
            return;
        } else if(isBoundary) {
            leftList.add(root);
        }
        preOrderTraversalFromLeft(root.left, leftList, isBoundary);
        preOrderTraversalFromLeft(root.right, leftList, isBoundary && root.left == null);
    }

    static void preOrderTraversalFromRight(TreeNode root, List<TreeNode> rightList, boolean isBoundary) {
        if(root == null) {
            return;
        }
        preOrderTraversalFromRight(root.left, rightList, isBoundary && root.right == null);
        preOrderTraversalFromRight(root.right, rightList, isBoundary);

        if(root.left == null && root.right == null) {
            rightList.add(root);
        } else if(isBoundary) {
            rightList.add(root);
        }
    }


    public static void main(String[] args) {
        /*TreeNode root = TreeUtils.buildTree(1, 2, 3, 4, 5, 6, null, 7, 8, 9);
        inorderTraversal(root);
        System.out.println();
        preorderTraversal(root);
        System.out.println();
        postorderTraversal(root);
        System.out.println();

        TreeNode symmetric = TreeUtils.buildTree(314, 6, 6, 1, 2, 2, 1, 4, 3, 5, 3, 3, 5, 4, 4);
        System.out.println(TreeUtils.virtualizeTree(symmetric));

        System.out.println(isSymmetric(symmetric.left, symmetric.right));*/

        List<Integer> preorder = new ArrayList<>(Arrays.asList(1, 2, 3, 4, null, 6, null, 7, 8, 9));
        TreeNode root = reconstructPreorder(preorder);

        //System.out.println(TreeUtils.printPreorder(root));

    }
}
