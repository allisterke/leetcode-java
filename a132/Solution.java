package a132;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private boolean checkPalindrome(int[][] dp, int i, int j, String s) {
        if(dp[i][j] == 0) {
            if (i == j) {
                dp[i][j] = 1;
            } else if (i + 1 == j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) ? 1 : -1;
            } else {
                dp[i][j] = s.charAt(i) == s.charAt(j) && checkPalindrome(dp, i + 1, j - 1, s) ? 1 : -1;
            }
        }
        return dp[i][j] == 1;
    }

    private int checkMinCut(int[][] dp, int index, int[] minCut, String s) {
        if(minCut[index] < 0) {
            if(checkPalindrome(dp, 0, index, s)) {
                minCut[index] = 0;
            }
            else {
                minCut[index] = 1 + checkMinCut(dp, index-1, minCut, s);
                for(int i = 1; i < index; ++ i) {
                    if(checkPalindrome(dp, i, index, s)) {
                        minCut[index] = Math.min(minCut[index], checkMinCut(dp, i - 1, minCut, s) + 1);
                    }
                }
            }
        }
        return minCut[index];
    }

    public int minCut(String s) {
        int[][] dp = new int[s.length()][];
        for(int i = 0; i < dp.length; ++ i) {
            dp[i] = new int[s.length()];
        }
//        for(int k = 0; k < s.length(); ++ k) {
//            for(int i = 0; i + k < s.length(); ++ i) {
//                if(k == 0) {
//                    dp[i][i+k] = true;
//                }
//                else if(k == 1) {
//                    dp[i][i+k] = s.charAt(i) == s.charAt(i+k);
//                }
//                else {
//                    dp[i][i+k] = dp[i+1][i+k-1] && s.charAt(i) == s.charAt(i+k);
//                }
//            }
//        }
//        int[] mc = new int[s.length()];
//        for(int i = 1; i < s.length(); ++ i) {
//            if(dp[0][i]) {
//                mc[i] = 0;
//            }
//            else {
//                mc[i] = mc[i-1] + 1;
//                for (int j = i - 1; j > 0; --j) {
//                    if (dp[j][i]) {
//                        mc[i] = Math.min(mc[i], mc[j - 1] + 1);
//                    }
//                }
//            }
//        }
        int[] mc = new int[s.length()];
        Arrays.fill(mc, -1);
        return checkMinCut(dp, s.length() - 1, mc, s);
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "aab",
                "aabb",
                "abab"
        );
        List<Integer> results = Arrays.asList(
                1,
                1,
                1
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minCut(tests.get(i)) == results.get(i));
        }
    }
}