package a099;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    List<List<TreeNode>> suspicous;
    TreeNode previous;

    private void inorder(TreeNode root) {
        if(root != null) {
            inorder(root.left);
            if(previous != null && previous.val > root.val) {
                suspicous.add(Arrays.asList(previous, root));
            }
            previous = root;
            inorder(root.right);
        }
    }

    public void recoverTree(TreeNode root) {
        suspicous = new ArrayList<>();
        previous = null;
        inorder(root);
        if(suspicous.size() == 1) {
            suspicous.add(suspicous.get(0));
        }
        int tmp = suspicous.get(0).get(0).val;
        suspicous.get(0).get(0).val = suspicous.get(1).get(1).val;
        suspicous.get(1).get(1).val = tmp;
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