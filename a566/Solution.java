package a566;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int R = nums.length;
        int C = nums[0].length;
        if(R*C != r*c) {
            return nums;
        }
        int[][] nm = new int[r][];
        for(int i = 0; i < r; ++ i) {
            nm[i] = new int[c];
        }
        for(int i = 0; i < r; ++ i) {
            for(int j = 0; j < c; ++ j) {
                nm[i][j] = nums[(i*c + j) / C][(i*c + j) % C];
            }
        }
        return nm;
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