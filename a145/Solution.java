package a145;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> nodes = new LinkedList<>();
        Deque<Integer> statuses = new LinkedList<>();
        nodes.push(root);
        statuses.push(0);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.peek();
            int status = statuses.peek();
            if(node == null || status == 2) {
                if(node != null) {
                    result.add(node.val);
                }
                nodes.pop();
                statuses.pop();
                if(!nodes.isEmpty()) {
                    statuses.push(statuses.pop() + 1);
                }
            }
            else if(status == 0) {
                nodes.push(node.left);
                statuses.push(0);
            }
            else if(status == 1) {
                nodes.push(node.right);
                statuses.push(0);
            }
        }
        return result;
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