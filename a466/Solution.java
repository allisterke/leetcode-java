package a466;

import java.util.*;

public class Solution {
    private Set<Character> distinct(String s) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < s.length(); ++ i) {
            set.add(s.charAt(i));
        }
        return set;
    }
    private boolean constructable(String s1, String s2) {
        return distinct(s1).containsAll(distinct(s2));
    }

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if(!constructable(s1, s2)) {
            return 0;
        }
        int rep = 0;
        int i = 0, j = 0;
        List<Integer>[] map = new List[s1.length()];
        while (i < n1 * s1.length()) {
            if(s1.charAt(i % s1.length()) == s2.charAt(j)) {
                if(j == s2.length() - 1) {
                    ++ rep;
                    if(map[i % s1.length()] != null) {
                        List<Integer> old = map[i % s1.length()];
                        int index = old.get(0);
                        int r = old.get(1);
                        rep = (n1 * s1.length() - i - 1) / (i - index) * (rep - r) + rep;
                        i = (n1 * s1.length() - i - 1) / (i - index) * (i - index) + i;
                        Arrays.fill(map, null);
                    }
                    map[i % s1.length()] = Arrays.asList(i, rep);
                }
                j = ++ j % s2.length();
            }
            ++ i;
        }
        return rep / n2;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        System.out.println(s.getMaxRepetitions("acb", 6, "ab", 2));
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}