package a188;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    private List<Integer> distinct(int[] prices) {
        List<Integer> list = new ArrayList<>();
        if(prices != null && prices.length > 0) {
            list.add(prices[0]);
            for (int i = 1; i < prices.length; ++i) {
                if (prices[i] != list.get(list.size() - 1)) {
                    list.add(prices[i]);
                }
            }
        }
        return list;
    }

    private List<Integer> mergeOrder(List<Integer> prices) {
        List<Integer> np = new ArrayList<>();
        for(int i = 0; i < prices.size(); ) {
            np.add(prices.get(i));
            int j = i+1;
            if(j < prices.size()) {
                if(prices.get(j) > prices.get(i)) {
                    for(; j < prices.size() && prices.get(j) > prices.get(j-1); ++ j) ;
                }
                else {
                    for(; j < prices.size() && prices.get(j) < prices.get(j-1); ++ j) ;
                }
            }
            if(j == i+1) {
                break;
            }
            i = j-1;
        }
        return np;
    }

    private boolean merge(List<Integer> prices, PriorityQueue<Integer> queue) {
        int size = prices.size();
        for(int i = 0; i + 3 < prices.size(); ++ i) {
            if (prices.get(i+1) > prices.get(i) &&
                    prices.get(i+2) < prices.get(i+1) && prices.get(i+2) >= prices.get(i) &&
                    prices.get(i+3) >= prices.get(i+1) ||
                prices.get(i+1) < prices.get(i) &&
                    prices.get(i+2) > prices.get(i+1) && prices.get(i+2) <= prices.get(i) &&
                    prices.get(i+3) <= prices.get(i+1)) {
                queue.add(- Math.abs(prices.get(i+1) - prices.get(i+2)));
                prices.remove(i+1);
                prices.remove(i+1);
                -- i;
            }
        }
        return prices.size() != size;
    }

    public int maxProfit(int k, int[] prices) {
        List<Integer> list = mergeOrder(distinct(prices));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        while (merge(list, queue)) ;
        for(int i = 0; i + 1 < list.size(); ++ i) {
            if(list.get(i+1) > list.get(i)) {
                queue.add(- (list.get(i+1) - list.get(i)));
            }
        }
        int mp = 0;
        while(k -- > 0 && !queue.isEmpty()) {
            mp -= queue.poll();
        }
        return mp;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1,3,4,5,3,2,5,6,1,7},
                new int[] {3,3,5,0,0,3,1,4},
                new int[] {2,6,8,7,8,7,9,4,1,2,4,5,8}
        );
        List<Integer> ks = Arrays.asList(
                4,
                2,
                2
        );
        List<Integer> results = Arrays.asList(
                8,
                6,
                14
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maxProfit(ks.get(i), tests.get(i)));
        }
    }
}