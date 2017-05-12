package a128;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    class Node {
        int left, mid, right;
        public Node(int n) {
            left = mid =  right = n;
        }
    }
    public int longestConsecutive(int[] nums) {
        Map<Integer, Node> map = new HashMap<>();
        for(int n : nums) {
            if(map.containsKey(n)) {
                continue;
            }
            Node mid = new Node(n);
            map.put(n, mid);
            Node left = map.get(n-1);
            Node right = map.get(n+1);
            if(left != null) {
                left.right = n;
                mid.left = n-1;
            }
            if(right != null) {
                right.left = n;
                mid.right = n+1;
            }
        }
        int longest = 0;
        for(Map.Entry<Integer, Node> entry : map.entrySet()) {
            if(entry.getValue().left == entry.getValue().mid) {
                int current = 0;
                for(int i = entry.getKey(); map.containsKey(i); ++ i, ++ current);
                longest = Math.max(longest, current);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] { 100, 4, 200, 3, 1, 2}
        );
        List<Integer> results = Arrays.asList(
                4
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.longestConsecutive(tests.get(i)));
        }
    }
}