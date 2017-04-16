package a553;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String optimalDivision(int[] nums) {
        if(nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        if(nums.length == 2) {
            return String.valueOf(nums[0]) + "/" + String.valueOf(nums[1]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]);
        sb.append('/');
        sb.append('(');
        for(int i = 1; i < nums.length; ++ i) {
            if(i != 1) {
                sb.append('/');
            }
            sb.append(nums[i]);
        }
        sb.append(')');
        return sb.toString();
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