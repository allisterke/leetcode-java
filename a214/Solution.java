package a214;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private int[] buildPrefix(String s) {
        int[] prefix = new int[s.length() + 1];
        prefix[0] = -1;
        int previous = -1;
        for(int i = 0; i < s.length(); ++ i) {
            while (previous >= 0 && s.charAt(previous) != s.charAt(i)) {
                previous = prefix[previous];
            }
            prefix[i + 1] = ++ previous;

        }
        return prefix;
    }

    public String shortestPalindrome(String s) {
        int[] prefix = buildPrefix(s);
        String rs = new StringBuilder(s).reverse().toString();
        int previous = 0;
        for(int i = 0; i < rs.length(); ++ i) {
            while (previous >= 0 && s.charAt(previous) != rs.charAt(i)) {
                previous = prefix[previous];
            }
            ++ previous;
        }
        return new StringBuilder(s.substring(previous)).reverse().toString() + s;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "aacecaaa",
                "abcd"
        );
        List<String> results = Arrays.asList(
                "aaacecaaa",
                "dcbabcd"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.shortestPalindrome(tests.get(i)));
        }
    }
}