package a168;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int x = n % 26;
            if(x == 0) {
                x = 26;
            }
            sb.append((char)('A' + x - 1));
            n = (n - x) / 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                1,
                26,
                27
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.convertToTitle(tests.get(i)));
        }
    }
}