package a316;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Solution {
    private boolean next(char c, String s, int start, int[] count, boolean[] visited) {
        int end = start;
        boolean valid = true;
        for(; end < s.length(); ++ end) {
            if(s.charAt(end) == c) {
                break;
            }
            else if(-- count[s.charAt(end) - 'a'] == 0 && !visited[s.charAt(end) - 'a']) {
                valid = false;
                break;
            }
        }
        for(; start < end + (valid ? 0 : 1); ++ start) { // revert count
            ++ count[s.charAt(start) - 'a'];
        }
        return valid;
    }
    public String removeDuplicateLetters0(String s) {
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        for(int i = 0; i < s.length(); ++ i) {
            ++ count[s.charAt(i) - 'a'];
        }
        StringBuilder result = new StringBuilder();
        int distinct = Arrays.stream(count).map(c -> c > 0 ? 1 : 0).sum();
        int offset = 0;
        while (distinct -- > 0) {
            for (int i = 0; i < 26; ++i) {
                if (count[i] > 0 && !visited[i] && next((char) (i + 'a'), s, offset, count, visited)) {
                    result.append((char) (i + 'a'));
                    visited[i] = true;
                    for(; s.charAt(offset) != (char) (i + 'a'); ++ offset) {
                        -- count[s.charAt(offset) - 'a'];
                    }
                    -- count[s.charAt(offset) - 'a'];
                    break;
                }
            }
        }
        return result.toString();
    }

    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++count[s.charAt(i) - 'a'];
        }
        boolean[] visited = new boolean[26];
        int distinct = Arrays.stream(count).map(c -> c > 0 ? 1 : 0).sum();
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); ++ i) {
            if(!visited[s.charAt(i) - 'a']) {
                while (0 != result.length() && s.charAt(i) <= result.charAt(result.length() - 1) &&
                        count[result.charAt(result.length() - 1) - 'a'] > 0) {
                    visited[result.charAt(result.length() - 1) - 'a'] = false;
                    result.setLength(result.length() - 1);
                }
                visited[s.charAt(i) - 'a'] = true;
                result.append(s.charAt(i));
            }
            -- count[s.charAt(i) - 'a'];
        }
        return result.toString();
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "bcabc",
                "cbacdcbc"
        );
        List<String> results = Arrays.asList(
                "abc",
                "acdb"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.removeDuplicateLetters(tests.get(i)));
        }
    }
}