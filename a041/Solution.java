package a041;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; ++ i) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != i+1 && nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[i];
                nums[i] = nums[tmp-1];
                nums[tmp-1] = tmp;
            }
        }
        for(int i = 0; i < nums.length; ++ i) {
            if(nums[i] != i+1) {
                return i+1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {3,4,-1,1}
        );
        List<Integer> results = Arrays.asList(
                2
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.firstMissingPositive(tests.get(i)));
        }
    }
}