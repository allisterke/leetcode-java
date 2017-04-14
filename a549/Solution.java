package a549;

import java.util.Arrays;
import java.util.List;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    int global = 0;

    public int longestConsecutive(TreeNode root) {
        List<Integer> ad = longestSubConsecutive(root);
        return global;
    }

    private List<Integer> longestSubConsecutive(TreeNode root) {
        if(root == null) {
            return Arrays.asList(0, 0);
        }
        else {
            List<Integer> left = longestSubConsecutive(root.left); // item 0, asc, item 1 desc
            List<Integer> right = longestSubConsecutive(root.right);
            int asc = 1, desc = 1;
            if(root.left == null && root.right == null) {
                global = Math.max(global, Math.max(asc, desc));
            }
            if(root.left != null) {
                if(root.val == root.left.val + 1) {
                    asc = Math.max(asc, 1 + left.get(0));
                }
                else if(root.val == root.left.val - 1) {
                    desc = Math.max(desc, 1 + left.get(1));
                }
                global = Math.max(global, Math.max(asc, desc));
            }
            if(root.right != null) {
                if(root.val == root.right.val + 1) {
                    asc = Math.max(asc, 1 + right.get(0));
                }
                else if(root.val == root.right.val - 1) {
                    desc = Math.max(desc, 1 + right.get(1));
                }
                global = Math.max(global, Math.max(asc, desc));
            }
            if(root.left != null && root.right != null) {
                if(root.val == root.left.val + 1&& root.val == root.right.val - 1) {
                    global = Math.max(global, left.get(0) + 1 + right.get(1));
                }
                else if(root.val == root.left.val - 1 && root.val == root.right.val + 1) {
                    global = Math.max(global, left.get(1) + 1 + right.get(0));
                }
            }
            return Arrays.asList(asc, desc);
        }
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