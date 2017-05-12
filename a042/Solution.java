package a042;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, water = 0;
        while (left < right - 1) {
            if(height[left] < height[right]) {
                if(height[left+1] < height[left]) {
                    water += height[left] - height[left+1];
                    height[left+1] = height[left];
                }
                ++ left;
            }
            else {
                if(height[right-1] < height[right]) {
                    water += height[right] - height[right-1];
                    height[right-1] = height[right];
                }
                -- right;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {0,1,0,2,1,0,1,3,2,1,2,1}
        );
        List<Integer> results = Arrays.asList(
                6
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.trap(tests.get(i)));
        }
    }
}