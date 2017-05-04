package a218;

import java.util.*;

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        PriorityQueue<List<Integer>> vertical = new PriorityQueue<>(
                (a, b) -> {
                    int c = Integer.compare(a.get(0), b.get(0)); // order by x
                    if(c != 0) {
                        return c;
                    }
                    c = Integer.compare(a.get(2), b.get(2)); // left first
                    if(c != 0) {
                        return c;
                    }
                    return - Integer.compare(a.get(1), b.get(1)); // higher first
                }
        );
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < buildings.length; ++ i) {
            int[] b = buildings[i];
            vertical.add(Arrays.asList(b[0], b[2], 0, i));
            vertical.add(Arrays.asList(b[1], b[2], 1, i));
        }

        List<int[]> result = new ArrayList<>();
        while (!vertical.isEmpty()) {
            List<Integer> v = vertical.poll();
            if(v.get(2) == 0) { // left side
                Map.Entry<Integer, Integer> higher = map.higherEntry(v.get(0));
                if (higher == null || v.get(1) > higher.getValue()) {
                    if (result.isEmpty() || v.get(1) != result.get(result.size() - 1)[1]) {
                        result.add(new int[]{v.get(0), v.get(1)});
                    }
                }
                int index = v.get(3);
                int right = buildings[index][1];
                higher = map.higherEntry(right);
                if (higher == null || buildings[index][2] > higher.getValue()) {
                    if (!map.containsKey(right) || map.get(right) < buildings[index][2]) {
                        map.put(right, buildings[index][2]);
                        Map.Entry<Integer, Integer> lower = null;
                        while ((lower = map.lowerEntry(right)) != null && lower.getValue() <= buildings[index][2]) {
                            map.remove(lower.getKey());
                        }
                    }
                }
            }
            else { // right side
                if(!map.containsKey(v.get(0)) || !map.get(v.get(0)).equals(v.get(1))) {
                    continue;
                }
                Map.Entry<Integer, Integer> higher = map.higherEntry(v.get(0));
                if(higher == null) {
                    result.add(new int[] {v.get(0), 0});
                }
                else {
                    if(higher.getValue() != result.get(result.size() - 1)[1]) {
                        result.add(new int[]{v.get(0), higher.getValue()});
                    }
                }
                map.remove(v.get(0));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        new int[][] {
                                new int[] {2, 9, 10},
                                new int[] {3, 7, 15},
                                new int[] {5, 12, 12},
                                new int[] {15, 20, 10},
                                new int[] {19, 24, 8}
                        },
                        new int[][] {
                                new int[] {0,2147483647,2147483647}
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            for(int[] p : s.getSkyline(tests.get(i))) {
                System.out.println(p[0] + "\t" + p[1]);
            }
        }
    }
}