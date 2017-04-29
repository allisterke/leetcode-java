package a135;

import java.util.*;

public class Solution {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 0);
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < ratings.length; ++ i) {
            if(!(i-1 >= 0 && ratings[i] > ratings[i-1] || i+1 < ratings.length && ratings[i] > ratings[i+1])) {
                candies[i] = 1;
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if(index > 0 && ratings[index] < ratings[index-1]) {
                if(candies[index] + 1 > candies[index-1]) {
                    candies[index-1] = candies[index] + 1;
                    queue.add(index - 1);
                }
            }
            if(index < ratings.length - 1 && ratings[index] < ratings[index+1]) {
                if(candies[index] + 1 > candies[index+1]) {
                    candies[index+1] = candies[index] + 1;
                    queue.add(index + 1);
                }
            }
        }
        return Arrays.stream(candies).sum();
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1,1,4,8,1,7,0,0,5,8}
        );
        List<Integer> results = Arrays.asList(
                17
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.candy(tests.get(i)));
        }
    }
}