package a297;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root != null) {
            sb.append(root.val);
            sb.append(',');
            String s = serialize(root.left);
            sb.append(s.length());
            sb.append(',');
            sb.append(s);
            s = serialize(root.right);
            sb.append(s.length());
            sb.append(',');
            sb.append(s);
        }
        else {
            sb.append("null");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode root = null;
        if(!data.equals("null")) {
            int offset = data.indexOf(',');
            root = new TreeNode(Integer.valueOf(data.substring(0, offset)));
            int next = data.indexOf(',', ++ offset);
            int length = Integer.valueOf(data.substring(offset, next));
            offset = next + 1;
            next = offset + length;
            root.left = deserialize(data.substring(offset, next));
            offset = next;
            next = data.indexOf(',', offset);
            length = Integer.valueOf(data.substring(offset, next));
            offset = next + 1;
            next = offset + length;
            root.right = deserialize(data.substring(offset, next));
        }
        return root;
    }
}

public class Solution {


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