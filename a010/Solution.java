package a010;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] matched = new boolean[2][];
        matched[0] = new boolean[s.length()+1];
        matched[1] = new boolean[s.length()+1];

        matched[0][0] = true;
        for(int i = 0; i < p.length(); ++ i) {
            if(i < p.length() - 1 && p.charAt(i+1) == '*') {
                for (int j = 0; j <= s.length(); ++j) {
                    if (matched[0][j]) {
                        matched[1][j] = true;
                        if (p.charAt(i) == '.') {
                            for (int k = j+1; k <= s.length(); ++ k) {
                                matched[1][k] = true;
                            }
                            break;
                        } else {
                            for (int k = j+1; k <= s.length() && s.charAt(k - 1) == p.charAt(i); ++ k) {
                                matched[1][k] = true;
                            }
                        }
                    }
                }
                ++ i;
            }
            else {
                for (int j = 1; j <= s.length(); ++ j) {
                    if(matched[0][j - 1]) {
                        if (p.charAt(i) == '.') {
                            matched[1][j] = true;
                        } else {
                            matched[1][j] = s.charAt(j - 1) == p.charAt(i);
                        }
                    }
                }
            }
            boolean[] tmp = matched[0];
            matched[0] = matched[1];
            matched[1] = tmp;
            Arrays.fill(matched[1], false);
        }
        return matched[0][s.length()];
    }

    public static void main(String[] args) {
        List<String> strs = Arrays.asList(
                "aa",
                "aa",
                "aaa",
                "aa",
                "aa",
                "ab",
                "aab",
                "a"
        );
        List<String> patterns = Arrays.asList(
                "a",
                "aa",
                "aa",
                "a*",
                ".*",
                ".*",
                "c*a*b",
                "ab*"
        );
        List<Boolean> results = Arrays.asList(
                false,
                true,
                false,
                true,
                true,
                true,
                true,
                true
        );

        Solution s = new Solution();
        for(int i = 0; i < strs.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            assert s.isMatch(strs.get(i), patterns.get(i)) == results.get(i);
        }
    }
}