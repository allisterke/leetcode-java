package a472;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    class Trie {
        Trie[] next = new Trie[26];
        boolean finished = false;
    }

    private void addWord(Trie trie, String word) {
        Trie t = trie;
        for (int i = 0; i < word.length(); ++ i) {
            char c = word.charAt(i);
            if(t.next[c - 'a'] == null) {
                t.next[c - 'a'] = new Trie();
            }
            t = t.next[c - 'a'];
        }
        t.finished = true;
    }

    private boolean checkConcatenatable(Trie trie, String word, int offset) {
        if(offset >= word.length()) {
            return true;
        }
        Trie t = trie;
        for(int i = offset; i < (offset == 0 ? word.length() - 1 : word.length()); ++ i) {
            char c = word.charAt(i);
            if(t.next[c - 'a'] != null) {
                t = t.next[c - 'a'];
                if(t.finished && checkConcatenatable(trie, word, i+1)) {
                    return true;
                }
            }
            else {
                break;
            }
        }
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = new Trie();
        for(String word : words) {
            if(!word.isEmpty()) {
                addWord(trie, word);
            }
        }

        List<String> result = new ArrayList<>();
        for (String word : words) {
            if(!word.isEmpty() &&checkConcatenatable(trie, word, 0)) {
                result.add(word);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
                        new String[] {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"}
                }
        );
        List<List<String>> results = Arrays.asList(
                Arrays.asList("catsdogcats","dogcatsdog","ratcatdogcat")
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findAllConcatenatedWordsInADict(tests.get(i)));
        }
    }
}