package a212;

import java.util.*;

public class Solution {
    class Trie {
        Trie next[] = new Trie[26];
        boolean finish = false;
    }

    private Trie buildTrie(String[] words) {
        Trie trie = new Trie();
        for(String word : words) {
            Trie t = trie;
            for(int i = 0; i < word.length(); ++ i) {
                int index = word.charAt(i) - 'a';
                if(t.next[index] == null) {
                    t.next[index] = new Trie();
                }
                t = t.next[index];
            }
            t.finish = true;
        }
        return trie;
    }

    private void findWords(char[][] board, boolean[][] visited, int i, int j, Trie trie, StringBuilder sb, Set<String> words) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[i].length || visited[i][j]) {
            return ;
        }
        if(trie == null) {
            return ;
        }
        int index = board[i][j] - 'a';
        if(trie.next[index] != null) {
            sb.append(board[i][j]);
            visited[i][j] = true;

            if(trie.next[index].finish == true) {
                words.add(sb.toString());
            }

            findWords(board, visited, i+1, j, trie.next[index], sb, words);
            findWords(board, visited, i-1, j, trie.next[index], sb, words);
            findWords(board, visited, i, j+1, trie.next[index], sb, words);
            findWords(board, visited, i, j-1, trie.next[index], sb, words);

            visited[i][j] = false;
            sb.setLength(sb.length() - 1);
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        boolean[][] visited = new boolean[board.length][];
        for(int i = 0; i < visited.length; ++ i) {
            visited[i] = new boolean[board[i].length];
        }
        Set<String> set = new HashSet<>();
        Trie trie = buildTrie(words);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < board.length; ++ i) {
            for(int j = 0; j < board[i].length; ++ j) {
                findWords(board, visited, i, j, trie, sb, set);
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        List<String[]> words = Arrays.asList(
                new String[][]{
                        new String[]{"oath", "pea", "eat", "rain"}
                }
        );
        List<char[][]> boards = Arrays.asList(
                new char[][][] {
                        new char[][] {
                                new char[] {'o','a','a','n'},
                                new char[] {'e','t','a','e'},
                                new char[] {'i','h','k','r'},
                                new char[] {'i','f','l','v'}
                        }
                }
        );
        List<Set<String>> results = Arrays.asList(new HashSet<>(Arrays.asList("eat","oath")));

        Solution s = new Solution();
        for(int i = 0; i < words.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(new HashSet<String>(s.findWords(boards.get(i), words.get(i))).equals(results.get(i)));
        }
    }
}