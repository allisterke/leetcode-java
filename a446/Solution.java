package a446;

import sun.rmi.runtime.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int total = 0;
        Map<Long, Integer>[] dp = new Map[A.length];
        for(int i = 0; i < A.length; ++ i) {
            dp[i] = new HashMap<>();
            for(int j = 0; j < i; ++ j) {
                long d = (long)A[i] - A[j];
                dp[i].putIfAbsent(d, 0);
                int count = dp[j].getOrDefault(d, 0);
                total += count;
                dp[i].put(d, dp[i].get(d) + count + 1);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {2,4,6,8,10},
                new int[] {1,1,1,1}
        );
        List<Integer> results = Arrays.asList(
                7,
                5
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.numberOfArithmeticSlices(tests.get(i)));
        }
    }
}