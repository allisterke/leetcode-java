package a018;

import java.util.*;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        for(int i = 0; i < nums.length - 3; ++ i) {
            for(int j = i+1; j < nums.length - 2; ++ j) {
                int part = nums[i] + nums[j];
                int p = j+1, q = nums.length - 1;
                while (p < q) {
                    int total = part + nums[p] + nums[q];
                    if(total < target) {
                        ++ p;
                    }
                    else if(total > target) {
                        -- q;
                    }
                    else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[p], nums[q]));
                        ++ p;
                        -- q;
                    }
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