package a514;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
    public int findRotateSteps(String ring, String key) {
        int[] d1 = new int[ring.length()];
        int[] d2 = new int[ring.length()];
        Arrays.fill(d1, Integer.MAX_VALUE);
        Arrays.fill(d2, Integer.MAX_VALUE);
        d1[0] = 0;
        for(int i = 0; i < key.length(); ++ i) {
            for(int j = 0; j < d1.length; ++ j) {
                if(d1[j] != Integer.MAX_VALUE) {
                    for(int k = 0; k < ring.length(); ++ k) {
                        if(ring.charAt((j + k) % ring.length()) == key.charAt(i)) {
                            d2[(j + k) % ring.length()] = Math.min(d2[(j + k) % ring.length()], d1[j] + k);
                        }
                    }
                    for(int k = 0; k < ring.length(); ++ k) {
                        if(ring.charAt((j - k + ring.length()) % ring.length()) == key.charAt(i)) {
                            d2[(j - k + ring.length()) % ring.length()] = Math.min(d2[(j - k + ring.length()) % ring.length()], d1[j] + k);
                        }
                    }
                }
            }
            for(int j = 0; j < d1.length; ++ j) {
                d1[j] = d2[j];
            }
            Arrays.fill(d2, Integer.MAX_VALUE);
        }
        return Arrays.stream(d1).filter(d -> d != Integer.MAX_VALUE).min().getAsInt() + key.length();
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("godding", "gd")
        );
        List<Integer> results = Arrays.asList(
                4
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findRotateSteps(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}