package a552;

import java.util.Arrays;
import java.util.List;

public class Solution {
    static long MOD = 1000000007;

    public int checkRecord(int n) {
        if(n == 1) {
            return 3;
        }
        if(n == 2) {
            return 8;
        }

        long[][] dp = new long[n+1][];
        for(int i = 0; i <= n; ++ i) {
            dp[i] = new long[3];
        }

        dp[0][0] = 1;
        for(int i = 1; i <= n; ++ i) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = dp[i - 1][0];
            dp[i][2] = dp[i - 1][1];
        }
        long total = (dp[n][0] + dp[n][1] + dp[n][2]) % MOD;
        for(int i = 1; i <= n; ++ i) {
            total += (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) * (dp[n-i][0] + dp[n-i][1] + dp[n-i][2]);
            total %= MOD;
        }

//        dp[0][0] = dp[0][1] = 1;
//        for(int i = 1; i < n; ++ i) {
//            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
//            dp[i][1] = dp[i - 1][0];
//            dp[i][2] = dp[i - 1][1];
//        }
//        long total = (dp[n-1][0] + dp[n-1][1] + dp[n-1][2]) % MOD;
//        for(int i = 0; i < n; ++ i) {
//            if(i == 0 || i == n-1) {
//                total += (dp[n-2][0] + dp[n-2][1] + dp[n-2][2]);
//            }
//            else {
//                total += (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])*(dp[n-i-2][0] + dp[n-i-2][1] + dp[n-i-2][2]);
//            }
//        }

        return (int)(total % MOD);
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(2, 3, 4, 5, 6, 7);
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(tests.get(i));
            System.out.println(s.checkRecord(tests.get(i)));
        }
    }
}