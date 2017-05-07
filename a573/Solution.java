package a573;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nuts.length; ++ i) {
            min = Math.min(min, distance(squirrel[0], squirrel[1], nuts[i][0], nuts[i][1]) - distance(tree[0], tree[1], nuts[i][0], nuts[i][1]));
        }
        for(int i = 0; i < nuts.length; ++ i) {
            min += 2 * distance(tree[0], tree[1], nuts[i][0], nuts[i][1]);
        }
        return min;
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