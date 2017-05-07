package a576;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private long[][] buildBoard(int m, int n) {
        long[][] board = new long[m][];
        for(int i = 0; i < m; ++ i) {
            board[i] = new long[n];
        }
        return board;
    }

    public int findPaths(int m, int n, int N, int i, int j) {
        if(N <= 0) {
            return 0;
        }
        if(m == 1 && n == 1) {
            return 4;
        }
        long[][] b1 = buildBoard(m, n);
        long[][] b2 = buildBoard(m, n);
        b1[i][j] = 1;
        long total = 0;
        long MOD = (long)(1e9+7);
        while (N -- > 0) {
            for(int x = 0; x < m; ++ x) {
                for(int y = 0; y < n; ++ y) {
                    if(x == 0 && y == 0 || x == 0 && y == n-1 || x == m-1 && y == 0 || x == m-1 && y == n-1) {
                        total += b1[x][y] * ((m == 1 || n == 1) ? 3 : 2);
                    }
                    else if(x == 0 || y == 0 || x == m-1 || y == n-1){
                        total += b1[x][y] * ((m == 1 || n == 1) ? 2 : 1);
                    }
                    total %= MOD;
                }
            }
            for(int x = 0; x < m; ++ x) {
                for(int y = 0; y < n; ++ y) {
                    int[][] directions = new int[][] {
                            new int[] {-1, 0},
                            new int[] {1, 0},
                            new int[] {0, -1},
                            new int[] {0, 1},
                    };
                    for(int[] d : directions) {
                        int x1 = x + d[0];
                        int y1 = y + d[1];
                        if(x1 >= 0 && x1 < m && y1 >= 0 && y1 < n) {
                            b2[x][y] += b1[x1][y1];
                            b2[x][y] %= MOD;
                        }
                    }
                }
            }
            long[][] tmp = b1;
            b1 = b2;
            b2 = tmp;
            for (int x = 0; x < m; ++ x) {
                for(int y = 0; y < n; ++ y) {
                    b2[x][y] = 0;
                }
            }
        }
        return (int)(total % MOD);
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
//        System.out.println(s.findPaths(2, 2, 2, 0, 0));
//        System.out.println(s.findPaths(1, 3, 3, 0, 1));
//        System.out.println(s.findPaths(4, 5, 8, 3, 2));
//        System.out.println(s.findPaths(8, 50, 23, 5, 26));
        System.out.println(s.findPaths(36, 5, 50, 15, 3));
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}