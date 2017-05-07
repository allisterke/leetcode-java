package a572;

import java.util.Arrays;
import java.util.List;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    private boolean isSameTree(TreeNode s, TreeNode t) {
        return s == null && t == null ||
            s != null && t != null && s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return isSameTree(s, t) || s.left != null && isSubtree(s.left, t) || s.right != null && isSubtree(s.right, t);
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