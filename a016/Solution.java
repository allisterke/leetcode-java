package a016;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = 0, diff = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 2; ++ i) {
            int j = i+1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum > target) {
                    if(sum - target < diff) {
                        closest = sum;
                        diff = sum - target;
                    }
                    -- k;
                }
                else if(sum < target) {
                    if(target - sum < diff) {
                        closest = sum;
                        diff = target - sum;
                    }
                    ++ j;
                }
                else {
                    return sum;
                }
            }
        }
        return closest;
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