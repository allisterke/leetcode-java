package a087;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private void addChar(Map<Character, Integer> charCount, char c) {
        if(charCount.get(c) == null) {
            charCount.put(c, 1);
        }
        else {
            charCount.put(c, charCount.get(c) + 1);
        }
    }
    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }
        if(s1.equals(s2)) {
            return true;
        }
        Map<Character, Integer> cc1 = new HashMap<>();
        Map<Character, Integer> cc2 = new HashMap<>();
        Map<Character, Integer> cc3 = new HashMap<>();
        for(int i = 0; i < s1.length() - 1; ++ i) {
            addChar(cc1, s1.charAt(i));
            addChar(cc2, s2.charAt(i));
            addChar(cc3, s2.charAt(s1.length() - 1 - i));
            if(cc1.equals(cc2) &&
                    isScramble(s1.substring(0, i+1), s2.substring(0, i+1)) &&
                    isScramble(s1.substring(i+1), s2.substring(i+1)) ||
               cc1.equals(cc3) &&
                    isScramble(s1.substring(0, i+1), s2.substring(s2.length() - (i+1))) &&
                    isScramble(s1.substring(i+1), s2.substring(0, s2.length() - (i+1)))
            ) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("great", "rgtae")
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.isScramble(tests.get(0).get(0), tests.get(0).get(1)));
        }
    }
}