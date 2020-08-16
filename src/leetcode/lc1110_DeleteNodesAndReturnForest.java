package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utils.TreeNode;

public class lc1110_DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if(root == null) {
            return null;
        }
        Set<Integer> toDelete = new HashSet();
        for(int i = 0; i < to_delete.length; i++) {
            toDelete.add(to_delete[i]);
        }
        List<TreeNode> result = new ArrayList<>();
        helper(root, toDelete, result, toDelete.contains(root.val));
        return result;
    }

    private void helper(TreeNode root, Set<Integer> toDelete, List<TreeNode> result, boolean toDeleteNode) {
        if(root == null) {
            return;
        }
        if(toDeleteNode) {
            result.add(root);
        }
        if(toDelete.contains(root.val)) {
            helper(root.left, toDelete, result, true);
            root = null;
        } else {
            helper(root.right, toDelete, result, false);
        }
    }
}
