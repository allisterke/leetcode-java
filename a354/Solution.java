package a354;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> Integer.compare(a[0], b[0]));
        int[] fold = new int[envelopes.length];
        int max = 0;
        for(int i = 0; i < envelopes.length; ++ i) {
            fold[i] = 1;
            for(int j = 0; j < i && envelopes[j][0] < envelopes[i][0]; ++ j) {
                if(envelopes[j][1] < envelopes[i][1]) {
                    fold[i] = Math.max(fold[i], fold[j] + 1);
                }
            }
            max = Math.max(max, fold[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}