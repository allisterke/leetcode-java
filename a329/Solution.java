package a329;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private int longestIncreasingPath(int[][] matrix, int[][] longestPath, int x, int y) {
        if(longestPath[x][y] == 0) {
            longestPath[x][y] = 1;
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if (Math.abs(i) == Math.abs(j)) {
                        continue;
                    }
                    int x1 = x + i;
                    int y1 = y + j;
                    if (x1 >= 0 && x1 < matrix.length && y1 >= 0 && y1 < matrix[x].length && matrix[x1][y1] > matrix[x][y]) {
                        longestPath[x][y] = Math.max(longestPath[x][y], longestIncreasingPath(matrix, longestPath, x1, y1) + 1);
                    }
                }
            }
        }
        return longestPath[x][y];
    }

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] longestPath = new int[matrix.length][];
        for(int i = 0; i < longestPath.length; ++ i) {
            longestPath[i] = new int[matrix[0].length];
        }
        int opt = 0;
        for(int i = 0; i < matrix.length; ++ i) {
            for(int j = 0; j < matrix[i].length; ++ j) {
                opt = Math.max(opt, longestIncreasingPath(matrix, longestPath, i, j));
            }
        }
        return opt;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        new int[][] {
                                new int[] {9, 9, 4},
                                new int[] {6, 6, 8},
                                new int[] {2, 1, 1}
                        }
                }
        );
        List<Integer> results = Arrays.asList(
                4
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.longestIncreasingPath(tests.get(i)));
        }
    }
}