package src.heap;

import src.utils.TreeNode;
import src.utils.TreeUtils;

/**
 * 一个数组的MaxTree定义如下
 *  1,数组必须没有重复元素
 *  2,MaxTree是一棵二叉树,数组的每一个值对应一个二叉树节点
 *  3,包括MaxTree树在内且在其中的每一棵子树上,值最大的节点 都是树的头。
 *  给定一个没有重复元素的数组arr,写出生成这个数组的MaxTree 的函数,要求如果数组长度为N,则时间复杂度为O(N)、额外空 间复杂度为O(N)
 */
public class MaxTree {

    public static TreeNode buildMaxTree(int[] array) {
        maxHeapify(array);
        TreeNode[] treeNodes = new TreeNode[array.length];
        for(int i = 0; i < array.length; i++) {
            treeNodes[i] = new TreeNode(array[i]);
        }

        for(int i = 0; i < array.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if(left < array.length) {
                treeNodes[i].left = treeNodes[left];
            }

            if(right < array.length) {
                treeNodes[i].right = treeNodes[right];
            }
        }
        return treeNodes[0];
    }

    public static void maxHeapify(int[] array) {
        if(array == null || array.length == 0) {
            return;
        }
        for(int i = 0; i < array.length; i++) {
            int index = i;
            while(index != 0) {
                int parent = (index - 1) / 2;
                if(array[index] > array[parent]) {
                    swap(array, index, parent);
                    index = parent;
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args) {

        int[] test = {3,2,1,5,32, 45,78,8};
        TreeNode treeNode = MaxTree.buildMaxTree(test);
        TreeUtils.printTree(treeNode);
    }
}
