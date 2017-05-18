package a012;

import java.util.Arrays;
import java.util.List;

public class Solution {
    static char[][] rc = new char[][] {
            new char[] {'I', 'V'},
            new char[] {'X', 'L'},
            new char[] {'C', 'D'},
            new char[] {'M', 'M'}
    };
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rc.length && num > 0; ++ i, num /= 10) {
            int k = num % 10;
            if(k > 0) {
                if (k <= 3) {
                    while (k-- > 0) sb.append(rc[i][0]);
                } else if (k == 4) {
                    sb.append(rc[i][1]);
                    sb.append(rc[i][0]);
                } else if (k <= 8) {
                    while (k-- > 5) {
                        sb.append(rc[i][0]);
                    }
                    sb.append(rc[i][1]);
                } else {
                    sb.append(rc[i + 1][0]);
                    sb.append(rc[i][0]);
                }
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                1,
                3,
                4,
                5,
                6,
                9,
                10,
                15,
                29,
                101,
                999,
                1001
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(tests.get(i));
            System.out.println(s.intToRoman(tests.get(i)));
        }
    }
}