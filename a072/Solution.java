package a072;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] distance = new int[word1.length() + 1][];
        for(int i = 0; i < distance.length; ++ i) {
            distance[i] = new int[word2.length() + 1];
        }
        for(int i = 0; i <= word1.length(); ++ i) {
            for(int j = 0; j <= word2.length(); ++ j) {
                if(i == 0 || j == 0) {
                    distance[i][j] = Math.max(i, j);
                }
                else {
                    distance[i][j] = Math.min(
                            Math.min(distance[i][j - 1]/* insert one */, distance[i - 1][j]/* delete one */) + 1,
                            distance[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1) // replace one
                    );
                }
            }
        }
        return distance[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("acbd", "dcba")
        );
        List<Integer> results = Arrays.asList(

        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minDistance(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}