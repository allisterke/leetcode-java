package a174;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] minhp = new int[dungeon.length][];
        for(int i = 0; i < minhp.length; ++ i) {
            minhp[i] = new int[dungeon[i].length];
        }
        for(int i = dungeon.length - 1; i >= 0; -- i) {
            for(int j = dungeon[i].length - 1; j >= 0; -- j) {
                if(i == dungeon.length - 1 && j == dungeon[i].length - 1) {
                    minhp[i][j] = Math.max(1, -dungeon[i][j] + 1);
                }
                else if(i == dungeon.length - 1) {
                    minhp[i][j] = Math.max(1, -dungeon[i][j] + minhp[i][j+1]);
                }
                else if(j == dungeon[i].length - 1) {
                    minhp[i][j] = Math.max(1, -dungeon[i][j] + minhp[i+1][j]);
                }
                else {
                    minhp[i][j] = Math.max(1,
                            Math.min(-dungeon[i][j] + minhp[i][j+1], -dungeon[i][j] + minhp[i+1][j]));
                }
            }
        }
        return minhp[0][0];
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        new int[][] {
                                new int[] {-2, -3, 3},
                                new int[] {-5, -10, 1},
                                new int[] {10, 30, -5}
                        }
                }
        );
        List<Integer> results = Arrays.asList(
                7
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.calculateMinimumHP(tests.get(i)));
        }
    }
}