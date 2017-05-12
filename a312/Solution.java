package a312;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxCoins(int[] nums0) {
        int[] nums = new int[nums0.length + 2];
        nums[0] = 1;
        for(int i = 0; i < nums0.length; ++ i) {
            nums[i+1] = nums0[i];
        }
        nums[nums.length-1] = 1;
        int[][] dp = new int[nums.length][];
        for(int i = 0; i < dp.length; ++ i) {
            dp[i] = new int[nums.length];
        }
        for(int k = 0; k < nums.length; ++ k) {
            if(k >= 2) {
                for(int i = 0; i + k < nums.length; ++ i) {
                    for(int j = i+1; j < i+k; ++ j) {
                        dp[i][i+k] = Math.max(dp[i][i+k], dp[i][j] + dp[j][i+k] + nums[i]*nums[j]*nums[i+k]);
                    }
                }
            }
        }
        return dp[0][nums.length-1];
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {3, 1, 5, 8}
        );
        List<Integer> results = Arrays.asList(
                167
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maxCoins(tests.get(i)));
        }
    }
}