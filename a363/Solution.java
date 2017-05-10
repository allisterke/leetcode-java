package a363;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;
        if(R == 0 || C == 0) {
            return 0;
        }
        int sub = Integer.MIN_VALUE;
        int[] rs = new int[R];
        TreeSet<Integer> set = new TreeSet<>();
        for(int p = 0; p < C; ++ p) {
            Arrays.fill(rs, 0);
            for(int j = p; j < C; ++ j) {
                set.clear();
                set.add(0);

                int sum = 0;
                for(int i = 0; i < R; ++ i) {
                    rs[i] += matrix[i][j];
                    sum += rs[i];
                    Integer s = set.ceiling(sum - k);
                    if(s != null) {
                        sub = Math.max(sub, sum - s);
                    }
                    set.add(sum);
                }
            }
        }
        return sub;
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