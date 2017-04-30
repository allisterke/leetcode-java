package a568;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int N = days.length;
        int K = days[0].length;
        int[][] dp = new int[K+1][];
        for(int i = 0; i < K+1; ++ i) {
            dp[i] = new int[N];
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;
        for(int i = 1; i <= K; ++ i) {
            for(int j = 0; j < N; ++ j) {
                for(int k = 0; k < N; ++ k) {
                    if(dp[i-1][k] >= 0 && (j == k || flights[k][j] == 1)) {
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + days[j][i-1]);
                    }
                }
            }
        }
        int mv = 0;
        for(int i = 0; i < N; ++ i) {
            mv = Math.max(mv, dp[K][i]);
        }
        return mv;
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