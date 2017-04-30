package a560;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int total = 0;
        for(int i = 0; i < nums.length; ++ i) {
            int sum = 0;
            for(int j = i; j < nums.length; ++ j) {
                sum += nums[j];
                if(sum == k) {
                    ++ total;
                }
            }
        }
        return total;
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