package a030;

import java.util.*;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if(words.length == 0) {
            return list;
        }

        int length = words[0].length();

        int[] count = new int[words.length];
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            Integer index = map.putIfAbsent(word, map.size());
            ++ count[index != null ? index : map.size() - 1];
        }

        int[] index = new int[s.length()];
        Arrays.fill(index, -1);

        for(int i = 0; i + length <= s.length(); ++ i) {
            index[i] = map.getOrDefault(s.substring(i, i + length), -1);
        }

        int[] occurrence = new int[words.length];
        for(int i = 0; i < length && i + length * words.length <= s.length(); ++ i) {
            int begin = i;
            int end = i;
            for(; end + length <= s.length(); end += length) {
                if(index[end] == -1) { // move `begin` & `end` to current position
                    while (begin < end) {
                        -- occurrence[index[begin]];
                        begin += length;
                    }
//                    end += length;
                    begin = end + length; // end need not to add, as for-loop will do this
                } else {
                    while (occurrence[index[end]] >= count[index[end]]) { // move `begin` forward to meet count requirement
                        -- occurrence[index[begin]];
                        begin += length;
                    }
                    ++ occurrence[index[end]];

                    if(end + length - begin == words.length * length) {
                        list.add(begin);
                        -- occurrence[index[begin]];
                        begin += length;
                    }
                }
            }
            Arrays.fill(occurrence, 0);
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "barfoothefoobarman",
                "wordgoodgoodgoodbestword",
                "mississippi",
                "a"
        );
        List<String[]> words = Arrays.asList(
                new String[][] {
                        new String[] {"foo","bar"},
                        new String[] {"word","good","best","good"},
                        new String[] {"is"},
                        new String[] {"a"}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();;
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findSubstring(tests.get(i), words.get(i)));
        }
    }
}