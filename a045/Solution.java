package a045;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int jump(int[] nums) {
        int[] steps = new int[nums.length];
        Arrays.fill(steps, 1, nums.length, Integer.MAX_VALUE);
        steps[0] = 0;
        int update = 1;
        for(int i = 0; i < nums.length; ++ i) {
            int j = update;
            for(; j <= Math.min(i + nums[i], nums.length); ++ j) {
                steps[j] = Math.min(steps[j], steps[i] + 1);
            }
            update = j;
        }
        return steps[steps.length - 1];
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