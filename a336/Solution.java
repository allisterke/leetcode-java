package a336;

import java.util.*;

public class Solution {
    private boolean isPalindrome(String word, int left, int right) {
        while (left < right) {
            if(word.charAt(left) != word.charAt(right)) {
                return false;
            }
            ++ left;
            -- right;
        }
        return true;
    }
    private boolean isPalindrome(String s1, String s2) {
        int common = Math.min(s1.length(), s2.length());
        for(int i = 0; i < common; ++ i) {
            if(s1.charAt(i) != s2.charAt(s2.length() - 1 - i)) {
                return false;
            }
        }
        return common >= s1.length() ? isPalindrome(s2, 0, s2.length() - common - 1) :
                isPalindrome(s1, common, s1.length()-1);
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < words.length; ++ i) {
            for(int j = i+1; j < words.length; ++ j) {
                if(isPalindrome(words[i], words[j])) {
                    result.add(Arrays.asList(i, j));
                }
                if(isPalindrome(words[j], words[i])) {
                    result.add(Arrays.asList(j, i));
                }
            }
        }
        return result;
    }

    public List<List<Integer>> palindromePairs0(String[] words) {
        Map<String, List<Integer>> suffixMap = new HashMap<>();
        Map<String, List<Integer>> prefixMap = new HashMap<>();
        for(int i = 0; i < words.length; ++ i) {
            suffixMap.putIfAbsent(words[i], new ArrayList<>());
            suffixMap.get(words[i]).add(i);
            prefixMap.putIfAbsent(words[i], new ArrayList<>());
            prefixMap.get(words[i]).add(i);
            for (int j = 0; j < words[i].length(); ++j) {
                if (isPalindrome(words[i], 0, j)) {
                    String suffix = words[i].substring(j + 1);
                    suffixMap.putIfAbsent(suffix, new ArrayList<>());
                    suffixMap.get(suffix).add(i);
                }
            }
            for (int j = words[i].length() - 1; j >= 0; --j) {
                if (isPalindrome(words[i], j, words[i].length() - 1)) {
                    String prefix = words[i].substring(0, j);
                    prefixMap.putIfAbsent(prefix, new ArrayList<>());
                    prefixMap.get(prefix).add(i);
                }
            }
        }
        Set<List<Integer>> result = new HashSet<>();
        for(int i = 0; i < words.length; ++ i) {
            String reverse = new StringBuilder(words[i]).reverse().toString();
            List<Integer> list = suffixMap.get(reverse);
            if(list != null) {
                for(int j : list) {
                    if(i != j) {
                        result.add(Arrays.asList(i, j));
                    }
                }
            }
            list = prefixMap.get(reverse);
            if(list != null) {
                for(int j : list) {
                    if(i != j) {
                        result.add(Arrays.asList(j, i));
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
//                        new String[] {"bat", "tab", "cat"},
//                        new String[] {"abcd", "dcba", "lls", "s", "sssll", "", "ll"},
                        new String[] {"ab","ba","abc","cba"}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.palindromePairs(tests.get(i)));
        }
    }
}