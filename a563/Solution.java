package a563;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private int[] findTiltInternal(TreeNode root) {
        if(root != null) {
            int[] left = findTiltInternal(root.left);
            int[] right = findTiltInternal(root.right);
            return new int[] {left[0] + right[0] + Math.abs(left[1] - right[1]),
                    left[1] + root.val + right[1]};
        }
        else {
            return new int[] {0, 0};
        }
    }
    public int findTilt(TreeNode root) {
        return findTiltInternal(root)[0];
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