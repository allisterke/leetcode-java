package a583;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][];
        for(int i = 0; i < dp.length; ++ i) {
            dp[i] = new int[word2.length() + 1];
        }
        for(int i = 0; i <= word1.length(); ++ i) {
            for(int j = 0; j <= word2.length(); ++ j) {
                if(i == 0 || j == 0) {
                    dp[i][j] = Math.max(i, j);
                }
                else {
                    dp[i][j] = word1.charAt(i-1) == word2.charAt(j-1) ? dp[i-1][j-1] : Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
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