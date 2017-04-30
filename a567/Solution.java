package a567;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()) {
            return false;
        }
        int[] c1 = new int[26];
        for(int i = 0; i < s1.length(); ++ i) {
            ++ c1[s1.charAt(i) - 'a'];
        }
        int[] c2 = new int[26];
        for(int i = 0; i < s1.length(); ++ i) {
            ++ c2[s2.charAt(i) - 'a'];
        }
        if(Arrays.equals(c1, c2)) {
            return true;
        }
        for(int i = s1.length(); i < s2.length(); ++ i) {
            -- c2[s2.charAt(i - s1.length()) - 'a'];
            ++ c2[s2.charAt(i) - 'a'];
            if(Arrays.equals(c1, c2)) {
                return true;
            }
        }
        return false;
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