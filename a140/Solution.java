package a140;

import java.util.*;

public class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<Integer> ls = new TreeSet<>();
        for(String word : wordDict) {
            ls.add(word.length());
        }
        Map<String, Integer> imap = new HashMap<>();
        for(int i = 0; i < wordDict.size(); ++ i) {
            imap.putIfAbsent(wordDict.get(i), i);
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 0; i < s.length(); ++ i) {
            if (dp[i] == false) {
                continue;
            }
            for (Iterator<Integer> it = ls.iterator(); it.hasNext(); ) {
                int length = it.next();
                int end = i + length;
                if (end <= s.length()) { // iterate by word length
                    String sub = s.substring(i, end);
                    Integer index = imap.get(sub);
                    if (index != null) {
                        dp[end] = true;
                    }
                }
                else {
                    break;
                }
            }
        }
        if(dp[dp.length - 1] == false) {
            return new ArrayList<>();
        }

        List<List<List<Integer>>> wbs = new ArrayList<>();
        for(int i = 0; i <= s.length(); ++ i) {
            wbs.add(new ArrayList<>());
        }
        wbs.get(0).add(new ArrayList<>());
        for(int i = 0; i < s.length(); ++ i) {
            if(wbs.get(i).isEmpty()) {
                continue;
            }
            for(Iterator<Integer> it = ls.iterator(); it.hasNext(); ) {
                int length = it.next();
                int end = i + length;
                if(end <= s.length()) { // iterate by word length
                    String sub = s.substring(i, end);
                    Integer index = imap.get(sub);
                    if(index != null) {
                        for(List list : wbs.get(i)) {
                            List list1 = new ArrayList<>(list);
                            list1.add(index);
                            wbs.get(end).add(list1);
                        }
                    }
                }
                else {
                    break;
                }
            }
        }

        List<String> result = new ArrayList<>();
        for (List<Integer> list : wbs.get(wbs.size() - 1)) {
            if(!list.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); ++i) {
                    if (i > 0) {
                        sb.append(' ');
                    }
                    sb.append(wordDict.get(list.get(i)));
                }
                result.add(sb.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "catsanddog",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//                "aaaaaaaaaaaa"
        );
        List<List<String>> dicts = Arrays.asList(
                Arrays.asList("cat", "cats", "and", "sand", "dog"),
                Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.wordBreak(tests.get(i), dicts.get(i)));
        }
    }
}