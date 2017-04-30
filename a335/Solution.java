package a335;

import java.util.*;

public class Solution {

    public boolean isSelfCrossing(int[] x) {
        for(int i = 3; i < x.length; ++ i) {
            if(x[i] >= x[i-2] && x[i-1] <= x[i-3] ||
                    i == 4 && x[i] + x[i-4] >= x[i-2] && x[i-1] == x[i-3] ||
                    i >= 5 && x[i-1] <= x[i-3] && x[i-5] <= x[i-3] && x[i-1] + x[i-5] >= x[i-3] &&
                            x[i] <= x[i-2] && x[i-4] <= x[i-2] && x[i] + x[i-4] >= x[i-2]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {2, 1, 1, 2},
                new int[] {1, 2, 3, 4},
                new int[] {1, 1, 1, 1},
                new int[] {3, 3, 3, 2, 1, 1}
        );
        List<Boolean> results = Arrays.asList(
                true,
                false,
                true,
                false
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.isSelfCrossing(tests.get(i)) == results.get(i));
        }
    }
}