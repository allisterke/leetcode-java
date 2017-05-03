package a224;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    int next = 0;

    public int calculate(String s) {
        next = 0;
        return calculate0(s);
    }

    private int calculate0(String s) {
        int result = 0;
        boolean positive = true;
        while (true) {
            for(; next < s.length() && Character.isSpaceChar(s.charAt(next)); ++ next);
            if(next >= s.length() || s.charAt(next) == ')') {
                break;
            }
            if(s.charAt(next) == '+' || s.charAt(next) == '-') {
                positive = s.charAt(next ++) == '+';
            }
            else if(s.charAt(next) == '(') {
                ++ next;
                result += (positive ? 1 : -1) * calculate0(s);
            }
            else { // digit
                int num = 0;
                for (; next < s.length() && Character.isDigit(s.charAt(next)); num = num * 10 + (s.charAt(next) - '0'), ++next)
                    ;
                result += positive ? num : -num;
            }
        }
        ++ next;
        return result;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "1 + 1",
                " 2-1 + 2 ",
                "(1+(4+5+2)-3)+(6+8)",
                "2147483647"
        );
        List<Integer> results = Arrays.asList(
                2,
                3,
                23,
                2147483647
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.calculate(tests.get(i)));
        }
    }
}