package a557;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String reverseWords(String s) {
        String[] splitted = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < splitted.length; ++ i) {
            if(i > 0) {
                sb.append(' ');
            }
            StringBuilder tmp = new StringBuilder(splitted[i]);
            tmp.reverse();
            sb.append(tmp);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "Let's take LeetCode contest"
        );
        List<String> results = Arrays.asList(
                "s'teL ekat edoCteeL tsetnoc"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.reverseWords(tests.get(i)).equals(results.get(i)));
        }
    }
}