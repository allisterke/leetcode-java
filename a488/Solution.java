package a488;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    class MutableInteger {
        int n;
        public MutableInteger(int n) {
            this.n = n;
        }
    }
    private void remove(String board, Map<Character, MutableInteger> map, int used, MutableInteger opt) {
        if(board.isEmpty()) {
            opt.n = Math.min(opt.n, used);
            return ;
        }
        for(int i = 0; i < board.length(); ++ i) {
            int j = i+1;
            for(; j < board.length() && board.charAt(j) == board.charAt(i); ++ j) ;
            if(j - i >= 3) {
                remove(board.substring(0, i) + board.substring(j), map, used, opt);
            }
            else {
                int needed = 3 - (j-i);
                if(used + needed < opt.n) {
                    MutableInteger mi = map.get(board.charAt(i));
                    if (mi != null && mi.n >= needed) {
                        mi.n -= needed;
                        remove(board.substring(0, i) + board.substring(j), map, used + needed, opt);
                        mi.n += needed;
                    }
                }
            }
        }
    }

    public int findMinStep(String board, String hand) {
        Map<Character, MutableInteger> map = new HashMap<>();
        for (int i = 0; i < hand.length(); ++ i) {
            map.putIfAbsent(hand.charAt(i), new MutableInteger(0));
            ++ map.get(hand.charAt(i)).n;
        }
        MutableInteger opt = new MutableInteger(Integer.MAX_VALUE);
        remove(board, map, 0, opt);
        return opt.n == Integer.MAX_VALUE ? -1 : opt.n;
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("WRRBBW", "RB"),
                Arrays.asList("WWRRBBWW", "WRBRW"),
                Arrays.asList("G", "GGGGG"),
                Arrays.asList("RBYYBBRRB", "YRBGB")
        );
        List<Integer> results = Arrays.asList(
                -1,
                2,
                2,
                3
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findMinStep(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}