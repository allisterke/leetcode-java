package a124;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private int maxPathSum(TreeNode root, TreeNode max) {
        if(root != null) {
            int left = Math.max(maxPathSum(root.left, max), 0);
            int right = Math.max(maxPathSum(root.right, max), 0);
            max.val = Math.max(max.val, left + right + root.val);
            return Math.max(left, right) + root.val;
        }
        else {
            return 0;
        }
    }

    public int maxPathSum(TreeNode root) {
        TreeNode max = new TreeNode(Integer.MIN_VALUE);
        maxPathSum(root, max);
        return max.val;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}