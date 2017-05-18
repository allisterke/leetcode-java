package a006;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String convert(String s, int numRows) {
        if(numRows <= 1) {
            return s;
        }
        StringBuilder[] sbs = new StringBuilder[numRows];
        for(int i = 0; i < sbs.length; ++ i) {
            sbs[i] = new StringBuilder();
        }
        int cell = 2 * numRows - 2;
        for(int i = 0; i < s.length(); ++ i) {
            int r = i % cell;
            if(r >= numRows) {
                r = cell - r;
            }
            sbs[r].append(s.charAt(i));
        }
        for(int i = 1; i < sbs.length; ++ i) {
            sbs[0].append(sbs[i]);
        }
        return sbs[0].toString();
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "PAYPALISHIRING",
                "PAYPALISHIRING"
        );
        List<Integer> ns = Arrays.asList(
                3,
                5
        );
        List<String> results = Arrays.asList(
                "PAHNAPLSIIGYIR",
                "PHASIYIRPLIGAN"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
//            System.out.println(s.convert(tests.get(i), ns.get(i)));
//            System.out.println(results.get(i));
            System.out.println(s.convert(tests.get(i), ns.get(i)).equals(results.get(i)));
        }
    }
}