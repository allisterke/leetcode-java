package a084;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> - Integer.compare(a.get(0), b.get(0)));
        for(int i = 0; i < heights.length; ++ i) {
            queue.add(Arrays.asList(heights[i], i));
        }
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        int lra = 0;
        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            int height = list.get(0);
            int index = list.get(1);

            int lb = index;
            for(int i = index - 1; i >= 0 && left[i] != -1; lb = left[i], i = left[i] - 1) ;
            int rb = index;
            for(int i = index + 1; i < heights.length && right[i] != -1; rb = right[i], i = right[i] + 1) ;

            left[lb] = left[rb] = left[index] = lb;
            right[lb] = right[rb] = right[index] = rb;
            lra = Math.max(lra, height * (rb - lb + 1));
        }
        return lra;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {2,1,5,6,2,3},
                new int[] {3,6,5,7,4,8,1,0}
        );
        List<Integer> results = Arrays.asList(
                10,
                20
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.largestRectangleArea(tests.get(i)));
        }
    }
}