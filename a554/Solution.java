package a554;

import java.util.*;

public class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> edges = new HashMap<>();
        for(List<Integer> bricks : wall) {
            int offset = 0;
            for(Integer brick : bricks) {
                offset += brick;
                edges.putIfAbsent(offset, 0);
                edges.put(offset, edges.get(offset) + 1);
            }
            edges.remove(offset);
        }
        int maxe = 0;
        for(Iterator<Integer> it = edges.values().iterator(); it.hasNext(); ) {
            maxe = Math.max(maxe, it.next());
        }
        return wall.size() - maxe;
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