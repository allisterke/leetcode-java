package a548;
import java.util.*;

public class Solution {
    public boolean splitArray(int[] nums) {
        int length = nums.length;
        int[] sums = new int[length + 1];
        for(int i = 0; i < length; ++ i) {
            sums[i+1] = sums[i] + nums[i];
        }

        for(int j = 4; j <= length; ++ j) {
            Set<Integer> range = new HashSet<>();
            for(int i = 2; i < j-1; ++ i) {
                if(sums[i-1] == sums[j-1] - sums[i]) {
                    range.add(sums[i-1]);
                }
            }
            if(range.isEmpty()) {
                continue;
            }
            for(int k = j+2; k <= length; ++ k) {
                if(sums[k-1] - sums[j] == sums[length] - sums[k] && range.contains(sums[k-1] - sums[j])) {
                    return true;
                }
            }
        }

        return false;
    }
    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        System.out.println(s.splitArray(new int[] {1,2,1,3,0,0,2,2,1,3,3}));
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
        }
    }
}