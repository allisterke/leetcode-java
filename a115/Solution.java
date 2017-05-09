package a115;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numDistinct(String s, String t) {
        int[][] count = new int[s.length()+1][];
        for(int i = 0; i < count.length; ++ i) {
            count[i] = new int[t.length()+1];
        }
        for(int i = 0; i <= s.length(); ++ i) {
            for(int j = 0; j <= t.length(); ++ j) {
                if(j == 0) {
                    count[i][j] = 1;
                }
                else if(i > 0) {
                    count[i][j] = count[i - 1][j] + (s.charAt(i - 1) == t.charAt(j - 1) ? count[i - 1][j - 1] : 0);
                }
            }
        }
        return count[s.length()][t.length()];
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("rabbbit", "rabbit"),
                Arrays.asList("aba", "a"),
                Arrays.asList("aadawehofwoaweoaefawefjaogvnakbawehofwoaweoaefoawejaawehofwoaweoaeeba", "awehofwoaweoae")
        );
        List<Integer> results = Arrays.asList(
                3,
                2,
                164144
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.numDistinct(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}