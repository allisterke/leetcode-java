package a011;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
    public int maxArea(int[] height) {
        int ma = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            ma = Math.max(ma, (right - left) * Math.min(height[left], height[right]));
            if(height[left] <= height[right]) {
                int i = left + 1;
                for(; i < right && height[i] <= height[left]; ++ i) ;
                left = i;
            }
            else if(height[left] > height[right]) {
                int i = right - 1;
                for(; i > left && height[i] <= height[right]; -- i) ;
                right = i;
            }
        }
        return ma;
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