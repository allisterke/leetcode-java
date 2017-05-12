package a407;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int trapRainWater(int[][] heightMap) {
        if(heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int R = heightMap.length;
        int C = heightMap[0].length;
        boolean[][] visited = new boolean[R][];
        for(int i = 0; i < R; ++ i) {
            visited[i] = new boolean[C];
        }
        int water = 0;
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.get(0), b.get(0)));
        for(int i = 0; i < R; ++ i) {
            queue.add(Arrays.asList(heightMap[i][0], i, 0));
            visited[i][0] = true;
            queue.add(Arrays.asList(heightMap[i][C - 1], i, C - 1));
            visited[i][C-1] = true;
        }
        for(int j = 1; j < C-1; ++ j) {
            queue.add(Arrays.asList(heightMap[0][j], 0, j));
            visited[0][j] = true;
            queue.add(Arrays.asList(heightMap[R-1][j], R-1, j));
            visited[R-1][j] = true;
        }
        while (!queue.isEmpty()) {
            List<Integer> cell = queue.poll();
            int h = cell.get(0), x = cell.get(1), y = cell.get(2);
            int[][] offset = new int[][] {
                    new int[] {-1, 0},
                    new int[] {1, 0},
                    new int[] {0, -1},
                    new int[] {0, 1}
            };
            for(int i = 0; i < 4; ++ i) {
                int x1 = x + offset[i][0];
                int y1 = y + offset[i][1];
                if(x1 >= 0 && y1 >= 0 && x1 < R && y1 < C && !visited[x1][y1]) {
                    if (heightMap[x1][y1] < h) {
                        water += h - heightMap[x1][y1];
                        heightMap[x1][y1] = h;
                    }
                    visited[x1][y1] = true;
                    queue.add(Arrays.asList(heightMap[x1][y1], x1, y1));
                }
            }
        }
        return water;
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