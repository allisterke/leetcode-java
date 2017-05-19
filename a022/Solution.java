package a022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(n > 0) {
            traverse(result, sb, 0, n, 0, n);
        }
        return result;
    }

    void traverse(List<String> result, StringBuilder sb, int lu, int lr, int ru, int rr) {
        if(lr == 0 && rr == 0) {
            result.add(sb.toString());
        }
        else {
            if(lr > 0) {
                sb.append('(');
                traverse(result, sb, lu+1, lr-1, ru, rr);
                sb.setLength(sb.length() - 1);
            }
            if(lu > ru) {
                sb.append(')');
                traverse(result, sb, lu, lr, ru+1, rr-1);
                sb.setLength(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                3
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.generateParenthesis(tests.get(i)));
        }
    }
}