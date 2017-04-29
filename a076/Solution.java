package a076;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public String minWindow(String s, String t) {
        int window = 0;
        Map<Character, Integer> tm = new HashMap<>();
        for(int i = 0; i < t.length(); ++ i) {
            tm.putIfAbsent(t.charAt(i), 0);
            tm.put(t.charAt(i), tm.get(t.charAt(i)) + 1);
        }
        for(Map.Entry<Character, Integer> entry : tm.entrySet()) {
            int i = 0, count = 0;
            for(; i < s.length() && count < entry.getValue(); count += s.charAt(i ++) == entry.getKey() ? 1 : 0) ;
            if(count < entry.getValue()) {
                return "";
            }
            window = Math.max(window, i);
        }
        Map<Character, Integer> sm = new HashMap<>();
        for(int i = 0; i < window; ++ i) {
            sm.putIfAbsent(s.charAt(i), 0);
            sm.put(s.charAt(i), sm.get(s.charAt(i)) + 1);
        }
        int start = 0, end = window;
        int i = 0, j = window;
        while (j <= s.length()) {
            boolean cont = true;
            for(; i < j && cont; ++ i) {
                sm.put(s.charAt(i), sm.get(s.charAt(i)) - 1);
                if(tm.containsKey(s.charAt(i)) && sm.get(s.charAt(i)) < tm.get(s.charAt(i))) {
                    if(j - i < end - start) {
                        start = i;
                        end = j;
                    }
                    for(++ j; j <= s.length() && s.charAt(j-1) != s.charAt(i); ++ j) {
                        sm.putIfAbsent(s.charAt(j-1), 0);
                        sm.put(s.charAt(j-1), sm.get(s.charAt(j-1)) + 1);
                    }
                    if(j <= s.length()) {
                        sm.put(s.charAt(i), sm.get(s.charAt(i)) + 1);
                    }
                    cont = false;
                }
            }
        }
        return s.substring(start, end);
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("ADOBECODEBANC", "ABC")
        );
        List<String> results = Arrays.asList("BANC");

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minWindow(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}