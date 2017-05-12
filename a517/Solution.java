package a517;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findMinMoves(int[] machines) {
        int sum = Arrays.stream(machines).sum();
        int avg = sum / machines.length;
        if(avg * machines.length != sum) {
            return -1;
        }
        int r = 0;
        int moves = 0;
        for(int i = 0; i < machines.length; ++ i) {
            if(machines[i] > avg) {
                if(r <= 0) {
                    moves = Math.max(moves, Math.max(-r, machines[i] - avg));
                }
                else if(r > 0){
                    moves = Math.max(moves, r + machines[i] - avg);
                }
            }
            r += machines[i] - avg;
        }
        return moves;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {100000,0,100000,0,100000,0,100000,0,100000,0,100000,0}
        );
        List<Integer> results = Arrays.asList(
                50000
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findMinMoves(tests.get(i)));
        }
    }
}