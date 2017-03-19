package a044;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean isMatch(String s, String p) {
        StringBuilder sp = new StringBuilder();
        for(int i = 0; i < p.length(); ++ i) {
            if(sp.length() == 0 || p.charAt(i) != '*' || sp.charAt(sp.length() - 1) != '*') {
                sp.append(p.charAt(i));
            }
        }
        boolean[] matchable0 = new boolean[s.length() + 1];
        boolean[] matchable1 = new boolean[s.length() + 1];

        matchable0[0] = true;
        for(int i = 0; i < sp.length(); ++ i) {
            boolean matched = false;
            for(int j = 0; j < matchable0.length; ++ j) {
                if(matchable0[j]) {
                    if(sp.charAt(i) == '*') {
                        for(int k = j; k < matchable1.length; ++ k) {
                            matchable1[k] = true;
                            matched = true;
                        }
                        break;
                    }
                    else {
                        if(j + 1 < matchable1.length && (sp.charAt(i) == '?' || sp.charAt(i) == s.charAt(j))) {
                            matchable1[j + 1] = true;
                            matched = true;
                        }
                    }
                }
            }
            if(!matched) {
                return false;
            }

            Arrays.fill(matchable0, false);

            boolean[] tmp = matchable0;
            matchable0 = matchable1;
            matchable1 = tmp;
        }

        return matchable0[matchable0.length - 1];
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("aa", "aa", "aaa", "aa", "aa", "ab", "aab");
        List<String> patterns = Arrays.asList("a", "aa", "aa", "*", "a*", "?*", "c*a*b");
        List<Boolean> results = Arrays.asList(false, true, false, true, true, true, false);

        Solution s = new Solution();
        for(int i = 0; i < strings.size(); ++ i) {
            System.out.println(i);
            System.out.println(s.isMatch(strings.get(i), patterns.get(i)) == results.get(i));
        }
    }
}