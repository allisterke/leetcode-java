package a403;

import java.util.*;

public class Solution {
    public boolean canCross(int[] stones) {
        TreeMap<Long, Set<Integer>> map = new TreeMap<>();
        for(int stone : stones) {
            map.put((long)stone, new HashSet<>());
        }
        map.get(0L).add(0);
        for(Map.Entry<Long, Set<Integer>> entry : map.entrySet()) {
            if(entry.getValue() == null) {
                continue;
            }
            for(int k : entry.getValue()) {
                for(int i = -1; i <= 1; ++ i) {
                    if(i + k > 0&& map.get(entry.getKey() + i + k) != null) {
                        map.get(entry.getKey() + i + k).add(i + k);
                    }
                }
            }
        }
        return !map.get((long)stones[stones.length - 1]).isEmpty();
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {0,1,3,5,6,8,12,17},
                new int[] {0,1,2,3,4,8,9,11}
        );
        List<Boolean> results = Arrays.asList(
                true,
                false
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.canCross(tests.get(i)));
        }
    }
}