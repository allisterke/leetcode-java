package a545;
import java.lang.reflect.Array;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private Collection<TreeNode> leftBoundary(TreeNode root) {
        List<TreeNode> b = new ArrayList<>();
        for(TreeNode node = root; node != null;) {
            b.add(node);
            if(node.left != null) {
                node = node.left;
            }
            else if(node.right != null) {
                node = node.right;
            }
            else {
                node = null;
            }
        }
        return b;
    }

    private Collection<TreeNode> rightBoundary(TreeNode root) {
        Deque<TreeNode> b = new ArrayDeque<>();
        for(TreeNode node = root; node != null; ) {
            b.addFirst(node);
            if(node.right != null) {
                node = node.right;
            }
            else if(node.left != null) {
                node = node.left;
            }
            else {
                node = null;
            }
        }
        return b;
    }

    private void bottomBoundary(TreeNode root, Collection<TreeNode> b) {
        if(root != null) {
            if(root.left == null && root.right == null) {
                b.add(root);
            }
            else {
                bottomBoundary(root.left, b);
                bottomBoundary(root.right, b);
            }
        }
    }

    private List<Integer> mergeBoundary(Collection<TreeNode> ...b) {
        List<Integer> boundary = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        for(Collection<TreeNode> ct : b) {
            for(TreeNode node : ct) {
                if(!visited.contains(node)) {
                    visited.add(node);
                    boundary.add(node.val);
                }
            }
        }
        return boundary;
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<TreeNode> bottom = new ArrayList<>();
        bottomBoundary(root, bottom);

        return mergeBoundary(Arrays.asList(root), leftBoundary(root.left), bottom, rightBoundary(root.right));
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
        }
    }
}