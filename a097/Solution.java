package a097;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private boolean isInterleave(String s1, int i1, String s2, int i2, String s3, int i3) {
        if(i3 >= s3.length()) {
            return true;
        }
        return i1 < s1.length() && s1.charAt(i1) == s3.charAt(i3) && isInterleave(s1, i1+1, s2, i2, s3, i3+1) ||
                i2 < s2.length() && s2.charAt(i2) == s3.charAt(i3) && isInterleave(s1, i1, s2, i2+1, s3, i3+1);
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][];
        for(int i = 0; i <= s1.length(); ++ i) {
            dp[i] = new boolean[s2.length() + 1];
        }
        for(int i = 0; i <= s1.length(); ++ i) {
            for(int j = 0; j <= s2.length(); ++ j) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                }
                else if(i == 0) {
                    dp[i][j] = dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
                }
                else if(j == 0) {
                    dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1);
                }
                else {
                    dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1) ||
                            dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public boolean isInterleave0(String s1, String s2, String s3) {
        return s3.length() == s1.length() + s2.length() &&
                isInterleave(s1, 0, s2, 0, s3, 0);
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("aadbbcbcac", "aabcc", "dbbca"),
                Arrays.asList("aadbbbaccc", "aabcc", "dbbca")
        );
        List<Boolean> results = Arrays.asList(true, false);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.isInterleave(tests.get(i).get(1), tests.get(i).get(2), tests.get(i).get(0)));
        }
    }
}