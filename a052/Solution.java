package a052;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    private int count = 0;

    private void checkValid(int n, int level, boolean[] vertical, boolean[] diagnal, boolean[] rdiagnal) {
        if(level >= n) {
            ++ count;
        }
        else {
            for(int i = 0; i < n; ++ i) {
                if(vertical[i] || diagnal[i - level + n - 1] || rdiagnal[i + level]) {
                    continue;
                }
                vertical[i] = true;
                diagnal[i - level + n - 1] = true;
                rdiagnal[i + level] = true;
                checkValid(n, level + 1, vertical, diagnal, rdiagnal);
                vertical[i] = false;
                diagnal[i - level + n - 1] = false;
                rdiagnal[i + level] = false;
            }
        }
    }

    public int totalNQueens(int n) {
        boolean[] vertical = new boolean[n];
        boolean[] diagnal = new boolean[n * 2 - 1];
        boolean[] rdiagnal = new boolean[n * 2 - 1];

        count = 0;
        checkValid(n, 0, vertical, diagnal, rdiagnal);
        return count;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                13
        );
        List<Integer> results = Arrays.asList(

        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.totalNQueens(tests.get(i)));
        }
    }
}