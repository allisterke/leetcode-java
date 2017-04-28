package a562;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int longestLine(int[][] M) {
        if(M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int R = M.length;
        int C = M[0].length;

        int longest = 0;
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ) {
                if(M[i][j] == 1) {
                    int k = j+1;
                    for(; k < C && M[i][k] == 1; ++ k);
                    longest = Math.max(longest, k - j);
                    j = k;
                }
                else {
                    ++ j;
                }
            }
        }

        for(int j = 0; j < C; ++ j) {
            for(int i = 0; i < R; ) {
                if(M[i][j] == 1) {
                    int k = i+1;
                    for(; k < R && M[k][j] == 1; ++ k);
                    longest = Math.max(longest, k - i);
                    i = k;
                }
                else {
                    ++ i;
                }
            }
        }

        for(int k = 0; k < R + C - 1; ++ k) {
            for (int i = 0; i < R; ) {
                if (k - i >= 0 && k - i < C && M[i][k - i] == 1) {
                    int j = i + 1;
                    for (; j < R && k - j >= 0 && k - j < C && M[j][k - j] == 1; ++j) ;
                    longest = Math.max(longest, j - i);
                    i = j;
                } else {
                    ++i;
                }
            }
        }

        for(int k = -C + 1; k < R; ++ k) {
            for(int i = 0; i < R; ) {
                if(i - k >= 0 && i - k < C && M[i][i-k] == 1) {
                    int j = i + 1;
                    for(; j < R && j - k >= 0 && j - k < C && M[j][j-k] == 1; ++ j) ;
                    longest = Math.max(longest, j - i);
                    i = j;
                } else {
                    ++i;
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        new int[][]{
                                new int[]{1,1,1,0,1,0,1,1,1,1,0,0,0,1,1,0,0,0,1,1},
                                new int[]{0,1,1,0,1,0,0,0,1,1,1,1,1,1,0,1,0,0,1,0},
                                new int[]{0,0,0,1,1,0,1,1,0,0,1,0,1,1,1,0,1,1,0,0},
                                new int[]{0,0,1,0,0,0,0,0,1,0,1,1,0,1,0,1,0,0,0,1},
                                new int[]{1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
                                new int[]{1,0,1,0,1,1,0,1,1,0,1,0,1,0,1,0,0,1,0,1},
                                new int[]{1,1,1,0,1,0,0,1,1,1,1,1,0,1,1,0,1,1,1,1},
                                new int[]{0,1,1,1,0,1,1,1,0,0,0,1,1,1,0,1,0,1,1,1},
                                new int[]{1,1,0,1,1,0,0,1,0,0,0,1,1,1,0,0,0,0,1,0},
                                new int[]{0,0,1,0,1,1,1,1,0,1,0,0,0,1,0,0,0,0,0,1},
                                new int[]{0,0,1,1,1,1,0,0,1,0,1,0,0,1,0,0,0,1,0,0},
                                new int[]{1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,1,0,1,1},
                                new int[]{0,0,1,1,0,0,0,0,1,0,1,0,0,1,0,1,0,0,1,0},
                                new int[]{1,0,0,0,1,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0},
                                new int[]{1,1,0,1,0,1,0,0,1,1,0,1,0,0,0,0,1,1,1,1},
                                new int[]{1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,0,0,0},
                                new int[]{0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,1,0,1,0,0},
                                new int[]{0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,1,0,0},
                                new int[]{0,1,1,1,0,0,1,1,1,0,0,1,0,0,1,1,0,1,0,1},
                                new int[]{1,0,1,0,1,1,0,0,0,1,0,0,0,0,0,1,1,0,0,1}
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.longestLine(tests.get(i)));
        }
    }
}