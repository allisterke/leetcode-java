package a538;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode convertBST(TreeNode root) {
        if(root == null || root.left == null && root.right == null) {
            return root;
        }

        List<Integer> elements = new ArrayList<>();
        inorderElement(root, elements);
        for(int i = elements.size() - 2; i >= 0; -- i) {
            elements.set(i, elements.get(i) + elements.get(i+1));
        }

        inorderAdd(root, elements.iterator());

        return root;
    }

    public void inorderAdd(TreeNode root, Iterator<Integer> it) {
        if(root != null) {
            inorderAdd(root.left, it);
            root.val = it.next();
            inorderAdd(root.right, it);
        }
    }

    public int count(TreeNode root) {
        if(root != null) {
            return count(root.left) + count(root.right) + 1;
        }
        else {
            return 0;
        }
    }

    public void inorderElement(TreeNode root, List<Integer> elements) {
        if(root != null) {
            inorderElement(root.left, elements);
            elements.add(root.val);
            inorderElement(root.right, elements);
        }
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        for(int i = 0; i < tests.size(); ++ i) {

        }
    }
}