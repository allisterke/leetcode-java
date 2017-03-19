package a065;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if(s.isEmpty()) { // empty
            return false;
        }

        int epos = detectNumber(s, 0, s.length());
        if(epos < 0) {
            return false;
        }

        return epos < s.length() ? detectInt(s, epos+1, s.length()) : true;
    }

    private boolean detectInt(String s, int begin, int end) {
        if(begin >= end) {
            return false;
        }
        if(s.charAt(begin) == '-' || s.charAt(begin) == '+') {
            ++ begin;
        }
        if(begin >= end) {
            return false;
        }
        for(int i = begin; i < end; ++ i) {
            if(!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private int detectNumber(String s, int begin, int end) {
        if(s.charAt(begin) == '-' || s.charAt(begin) == '+') {
            ++ begin;
        }
        boolean pointDetected = false;
        boolean digitDetected = false;
        for(int i = begin; i < end; ++ i) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                digitDetected = true;
                continue;
            }
            else if(c == '.') {
                if(pointDetected) {
                    return -1;
                }
                pointDetected = true;
            }
            else if(c == 'e') {
                return digitDetected ? i : -1;
            }
            else {
                return -1;
            }
        }
        return digitDetected ? end : -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> tests = Arrays.asList("0", " 0.1 ", "abc", "1 a", "2e10", "0e", ".");
        List<Boolean> results = Arrays.asList(new Boolean[] { true, true, false, false, true, false, false});
        for(int i = 0; i < tests.size(); ++ i) {
            boolean r = s.isNumber(tests.get(i));
            System.out.println(r);
            assert r == results.get(i);
        }
    }
}
