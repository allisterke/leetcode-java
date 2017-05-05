package a123;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length < 2) {
            return 0;
        }
        int[] t1 = new int[prices.length];
        int[] t2 = new int[prices.length];
        int min = prices[0];
        int max = -prices[0];

        for(int i = 1; i < prices.length; ++ i) {
            t1[i] = Math.max(t1[i-1], prices[i] - min);
            min = Math.min(min, prices[i]);

            t2[i] = Math.max(t2[i-1], prices[i] + max);
            max = Math.max(max, t1[i-1] - prices[i]);
        }
        return t2[prices.length - 1];
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