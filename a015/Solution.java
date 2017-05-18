package a015;

import java.util.*;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        for(int i = 0; i < nums.length - 2; ++ i) {
            int j = i+1, k = nums.length - 1;
            while (j < k) {
                if(nums[i] + nums[j] + nums[k] < 0) {
                    ++ j;
                }
                else if(nums[i] + nums[j] + nums[k] > 0) {
                    -- k;
                }
                else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    ++j;
                    --k;
                }
            }
        }
        return new ArrayList<>(result);
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