package a581;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        int left = 0;
        for(; left < nums.length && nums[left] == sorted[left]; ++ left) ;
        int right = nums.length - 1;
        for(; right > left && nums[right] == sorted[right]; -- right) ;
        return right - left + 1;
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