package a543;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] dd = depthAndDiameterOfBinaryTree(root);
        return Math.max(dd[0], dd[1]) - 1;
    }

    public int[] depthAndDiameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return new int[] {0, 0};
        }
        int[] left = depthAndDiameterOfBinaryTree(root.left);
        int[] right = depthAndDiameterOfBinaryTree(root.right);

        // max subtree depth & subtree diameter
        return new int[] {Math.max(left[0], right[0]) + 1,
                Math.max(left[0] + right[0] + 1,
                        Math.max(left[1], right[1]))};
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        for(int i = 0; i < tests.size(); ++ i) {

        }
    }
}