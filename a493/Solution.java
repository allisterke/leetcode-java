package a493;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NavigableSet;

public class Solution {

    private int[] merge(int[] nums, int b1, int e1, int b2, int e2) {
        int[] result = new int[e1 - b1 + e2 - b2];
        for(int i = 0; i < result.length; ++ i) {
            if(b1 >= e1) {
                result[i] = nums[b2 ++];
            }
            else if(b2 >= e2) {
                result[i] = nums[b1 ++];
            }
            else if(nums[b1] < nums[b2]) {
                result[i] = nums[b1 ++];
            }
            else {
                result[i] = nums[b2 ++];
            }
        }
        return result;
    }

    private int reversePairs(int[] nums, int begin, int end) {
        if(begin + 1 >= end) {
            return 0;
        }
        int mid = (begin + end) / 2;
        int lp = reversePairs(nums, begin, mid);
        int rp = reversePairs(nums, mid, end);

        int lb = begin, pairs = 0;
        for(int i = mid; i < end; ++ i) {
            for(; lb < mid && nums[lb] <= 2L*nums[i]; ++ lb) ;
            pairs += mid - lb;
        }

//        int[] merged = merge(nums, begin, mid, mid, end);
//        for(int i = 0; i < merged.length; ++ i) {
//            nums[begin + i] = merged[i];
//        }
        System.arraycopy(merge(nums, begin, mid, mid, end), 0, nums, begin, end - begin);

        return lp + rp + pairs;
    }

    public int reversePairs(int[] nums) {
        return reversePairs(nums, 0, nums.length);
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {1,3,2,3,1},
                new int[] {2,4,3,5,1},
                new int[] {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647}
        );
        List<Integer> results = Arrays.asList(2, 3, 0);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            assert s.reversePairs(tests.get(i)) == results.get(i);
        }
    }
}