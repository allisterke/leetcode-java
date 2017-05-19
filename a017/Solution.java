package a017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static List<String> board = Arrays.asList(
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    );

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        traverse(digits, 0, sb, result);
        return result;
    }

    void traverse(String digits, int index, StringBuilder sb, List<String> result) {
        if(index < digits.length()) {
            int p = digits.charAt(index) - '0';
            for(int i = 0; i < board.get(p).length(); ++ i) {
                sb.append(board.get(p).charAt(i));
                if(index + 1 == digits.length()) {
                    result.add(sb.toString());
                }
                else {
                    traverse(digits, index + 1, sb, result);
                }
                sb.setLength(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "23"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.letterCombinations(tests.get(i)));
        }
    }
}