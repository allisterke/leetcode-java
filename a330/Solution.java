package a330;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minPatches(int[] nums, int n) {
        Arrays.sort(nums);
        long N = 0;
        int patch = 0;
        for(int i = 0; N < n; ) {
            if(i >= nums.length || nums[i] > N + 1) {
                ++ patch;
                N = (N << 1) + 1;
            }
            else {
                N = N + nums[i];
                ++ i;
            }
        }
        return patch;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1, 3},
                new int[] {1, 5, 10},
                new int[] {1, 2, 2},
                new int[] {}
        );
        List<Integer> ns = Arrays.asList(
                6,
                20,
                5,
                7
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minPatches(tests.get(i), ns.get(i)));
        }
    }
}