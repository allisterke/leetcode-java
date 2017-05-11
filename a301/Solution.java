package a301;

import java.util.*;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Deque<Character> parentheses = new ArrayDeque<>();
        Deque<Integer> indexes = new ArrayDeque<>();
        for(int i = 0; i < s.length(); ++ i) {
            char c = s.charAt(i);
            if(c == '(' || c == ')') {
                if (c == '(' || parentheses.isEmpty() || parentheses.peek() == ')') {
                    parentheses.push(c);
                    indexes.push(i);
                } else {
                    parentheses.pop();
                    indexes.pop();
                }
            }
        }
        if(parentheses.isEmpty()) {
            return Arrays.asList(s);
        }
        int lend = 0, rstart = s.length();
        while (!parentheses.isEmpty() && parentheses.peek() == '(') {
            parentheses.pop();
            rstart = indexes.pop();
        }
        if(!parentheses.isEmpty()) {
            lend = indexes.peek() + 1;
        }
        parentheses.clear();
        indexes.clear();

        List<String> ls = new ArrayList<>();
        int remain = 0;
        for(int i = 0; i < lend; ++ i) {
            if(s.charAt(i) == ')') {
                ++ remain;
            }
        }
        removeRightParentheses(s, 0, lend, 0, 0, remain, new StringBuilder(), ls);
        List<String> rs = new ArrayList<>();
        remain = 0;
        for(int i = rstart; i < s.length(); ++ i) {
            if(s.charAt(i) == '(') {
                ++ remain;
            }
        }
        removeLeftParentheses(s, rstart, s.length()-1, 0, 0, remain, new StringBuilder(), rs);

        String mid = s.substring(lend, rstart);
        List<String> result = new ArrayList<>();
        for(int i = 0; i < ls.size(); ++ i) {
            for (int j = 0; j < rs.size(); ++ j) {
                result.add(ls.get(i) + mid + rs.get(j));
            }
        }
        return result;
    }

    private void removeRightParentheses(String s, int offset, int end, int left, int right, int remain, StringBuilder sb, List<String> list) {
        if(offset >= end) {
            list.add(sb.toString());
        }
        else if(s.charAt(offset) != ')') {
            sb.append(s.charAt(offset));
            if(s.charAt(offset) == '(') {
                ++ left;
            }
            removeRightParentheses(s, offset + 1, end, left, right, remain, sb, list);
            sb.setLength(sb.length() - 1);
        }
        else {
            int rc = 0;
            int next = offset;
            for(; next < end && s.charAt(next) == ')'; ++ next, ++ rc) ;
            remain -= rc;
            for (int i = Math.max(0, left - right - remain); i <= Math.min(left - right, rc); ++ i) {
                for(int j = 0; j < i; ++ j) {
                    sb.append(')');
                }
                removeRightParentheses(s, next, end, left, right + i, remain, sb, list);
                sb.setLength(sb.length() - i);
            }
        }
    }

    private void removeLeftParentheses(String s, int start, int offset, int right, int left, int remain, StringBuilder sb, List<String> list) {
        if(offset < start) {
            list.add(new StringBuilder(sb).reverse().toString());
        }
        else if(s.charAt(offset) != '(') {
            sb.append(s.charAt(offset));
            if(s.charAt(offset) == ')') {
                ++ right;
            }
            removeLeftParentheses(s, start, offset - 1, right, left, remain, sb, list);
            sb.setLength(sb.length() - 1);
        }
        else {
            int lc = 0;
            int next = offset;
            for(; next >= start && s.charAt(next) == '('; -- next, ++ lc) ;
            remain -= lc;
            for (int i = Math.max(0, right - left - remain); i <= Math.min(right - left, lc); ++ i) {
                for(int j = 0; j < i; ++ j) {
                    sb.append('(');
                }
                removeLeftParentheses(s, start, next, right, left + i, remain, sb, list);
                sb.setLength(sb.length() - i);
            }
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                ")(()c))(",
                "(()",
                "()())()",
                "(a)())()",
                ")("
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.removeInvalidParentheses(tests.get(i)));
        }
    }
}