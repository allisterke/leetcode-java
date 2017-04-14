package a032;

import java.util.*;

public class Solution {
    public int longestValidParentheses(String s) {
        int longest = 0;
        int[] cache = new int[s.length() + 1];
        Deque<Character> stack = new LinkedList<>();
        Deque<Integer> index = new LinkedList<>();
        for(int i = 0; i < s.length(); ++ i) {
            if(stack.isEmpty() || s.charAt(i) == '(' || stack.peekLast() == s.charAt(i)) {
                stack.addLast(s.charAt(i));
                index.addLast(i);
            }
            else {
                cache[i+1] = i - index.peekLast() + 1 + cache[index.peekLast()];
                longest = Math.max(longest, cache[i+1]);

                stack.removeLast();
                index.removeLast();
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList("", "(()", ")()())", ")(");
        List<Integer> results = Arrays.asList(0, 2, 4, 0);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.longestValidParentheses(tests.get(i)) == results.get(i));
        }
    }
}